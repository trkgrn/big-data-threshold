package com.trkgrn_theomer.bigdatathresholding.api.utility;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Threshold;
import com.trkgrn_theomer.bigdatathresholding.api.service.ComplaintService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.LongStream;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class CSVUtil {
    private static int control = 0;
    private static long counter = 0;
    private static final String RAW_DATA_FILE = "src/main/java/com/trkgrn_theomer/bigdatathresholding/api/data/raw/rows.csv";
    private static final String RAW_SPLIT_DIRECTORY = "src/main/java/com/trkgrn_theomer/bigdatathresholding/api/data/raw/split";
    private static final String REGULAR_DATA_FILE = "src/main/java/com/trkgrn_theomer/bigdatathresholding/api/data/regular/regular-data.csv";
    private static final String REGULAR_SPLIT_DIRECTORY = "src/main/java/com/trkgrn_theomer/bigdatathresholding/api/data/regular/split";

    private final StringUtil stringUtil;
    private final ComplaintService complaintService;

    public CSVUtil(StringUtil stringUtil, ComplaintService complaintService) {
        this.stringUtil = stringUtil;
        this.complaintService = complaintService;
    }


    public void printRegularDataInDatabase() throws IOException {
        List<File> files = splitRegularDataFile(100000);
        files.parallelStream().forEach(file -> {
            try {
                List<Complaint> complaints = getRegularData(file);
                System.out.println(file.getName() + " adlı dosyadan çekilen veri sayısı: " + complaints.size());
//                     IntStream.range(0,complaints.size()).parallel().forEach(index -> {
//                         complaintService.save(complaints.get(index));
//                     });
                complaintService.saveAll(complaints);
                System.out.println(file.getName() + " adlı dosyadan çekilen veriler kaydedildi");
            } catch (IOException e) {
                e.printStackTrace();

            }

        });
    }


    public List<File> splitRegularDataFile(int fileSize) throws IOException {
        File recordsFile = new File(REGULAR_DATA_FILE);
        File splitDirectory = new File(REGULAR_SPLIT_DIRECTORY);

        FileUtils.deleteDirectory(splitDirectory);
        FileUtils.forceMkdir(splitDirectory);


        StopWatch splittedFilesStopwatch = StopWatch.createStarted();
        List<File> splittedFiles = new ArrayList<File>();

        try (
                FileReader fileReader = new FileReader(recordsFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            long currentFileRecordCount = 0;
            File currentFile = new File(format(REGULAR_SPLIT_DIRECTORY + "/regular-data-%d.csv", splittedFiles.size()));
            FileWriter currentFileWriter = new FileWriter(currentFile);
            BufferedWriter currentBufferedWriter = new BufferedWriter(currentFileWriter);
            String line = bufferedReader.readLine();

            while (nonNull(line)) {
                currentBufferedWriter.write(line);
                currentBufferedWriter.newLine();

                currentFileRecordCount++;

                line = bufferedReader.readLine();


                if (nonNull(line) && currentFileRecordCount > fileSize - 1) {

                    currentBufferedWriter.close();
                    currentFileWriter.close();

                    currentBufferedWriter.close();
                    currentFileWriter.close();

                    splittedFiles.add(currentFile);

                    currentFileRecordCount = 0;
                    currentFile = new File(format(REGULAR_SPLIT_DIRECTORY + "/regular-data-%d.csv", splittedFiles.size()));
                    currentFileWriter = new FileWriter(currentFile);
                    currentBufferedWriter = new BufferedWriter(currentFileWriter);
                } else if (isNull(line)) {
                    splittedFiles.add(currentFile);
                }
            }
        }

        splittedFilesStopwatch.stop();

        System.out.println(
                format(
                        "'%s' adlı dosya '%d' farklı dosyaya %d ms sürede parçalandı.",
                        recordsFile.getName(),
                        splittedFiles.size(),
                        splittedFilesStopwatch.getTime()
                )
        );

        return splittedFiles;
    }

    public void findThreshold(int pageNo, int pageSize) throws IOException {
        List<Threshold> thresholds = new ArrayList<>();
        List<Complaint> complaints = complaintService.getAllByPage(pageNo, pageSize);
        System.out.println("Data1 geldi:"+pageNo+":"+pageSize);
        long count = complaintService.count();
        long size = count / 10;
        complaints.stream().forEach(origin -> {
            LongStream.range(0,11).forEach(page ->{
                complaintService.getAllByPage((int)page,(int)size)
                        .stream().forEach(destination->{
                          double average =  stringUtil.getSimilarityAverage(new String[]{origin.getCompany(),destination.getCompany()});
                            counter++;
                          //  System.out.println(counter +" " + average + " " +origin.getCompany() + "### " + destination.getCompany());
                        });
                System.out.println(counter + " C.ID: "  );

            });
        });

    }

    public List<Complaint> getRegularData(File file) throws IOException {
        List<Complaint> complaints = new ArrayList<>();
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
        CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);

        for (CSVRecord csvRecord : csvParser) {
            complaints.add(getComplaintByRegularData(csvRecord));
        }
        csvParser.close();
        complaints.removeIf(Objects::isNull);
        return complaints;
    }


    public void createRegularData() throws IOException {
        File csvFile = new File(RAW_DATA_FILE);

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(csvFile));
        CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(REGULAR_DATA_FILE));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("Product", "Issue", "Company", "State", "ZIP Code", "Complaint ID"));


        long start = System.currentTimeMillis();

        for (CSVRecord csvRecord : csvParser) {
            control++;
            if (control == 1)
                continue;

            printRecord(csvPrinter, getComplaintByRawData(csvRecord));

//            if (control > 1000)
//                break;
        }
        long end = System.currentTimeMillis();
        long total = end - start;
        csvPrinter.flush();
        csvParser.close();

        System.out.println(total + "ms");
        control = 0;
    }

    private void printRecord(CSVPrinter csvPrinter, Complaint complaint) throws IOException {
        if (!isContainsNullValue(complaint))
            csvPrinter.printRecord(complaint.getProduct(), complaint.getIssue(), complaint.getCompany(),
                    complaint.getState(), complaint.getZipCode(), complaint.getComplaintId());
    }


    private boolean isContainsNullValue(Complaint complaint) {
        if (!complaint.getProduct().equals("") && !complaint.getIssue().equals("") && !complaint.getCompany().equals("") &&
                !complaint.getState().equals("") && !complaint.getZipCode().equals("") && !complaint.getComplaintId().equals(""))
            return false;
        return true;
    }

    private Complaint getComplaintByRawData(CSVRecord csvRecord) {

        Complaint complaint = new Complaint();
        complaint.setProduct(stringUtil.extractStopwords(stringUtil.extractPunctuations(csvRecord.get(1))));
        complaint.setIssue(stringUtil.extractStopwords(stringUtil.extractPunctuations(csvRecord.get(3))));
        complaint.setCompany(stringUtil.extractStopwords(stringUtil.extractPunctuations(csvRecord.get(7))));
        complaint.setState(stringUtil.extractStopwords(stringUtil.extractPunctuations(csvRecord.get(8))));
        complaint.setZipCode(stringUtil.extractStopwords(stringUtil.extractPunctuations(csvRecord.get(9))));
        complaint.setComplaintId(stringUtil.extractStopwords(stringUtil.extractPunctuations(csvRecord.get(17))));

        System.out.println("-------" + control + "----------");
        System.out.println(complaint);

        return complaint;
    }

    private Complaint getComplaintByRegularData(CSVRecord csvRecord) {
        Complaint complaint = new Complaint();
        if (csvRecord.size() == 6) {
            complaint.setProduct(csvRecord.get(0));
            complaint.setIssue(csvRecord.get(1));
            complaint.setCompany(csvRecord.get(2));
            complaint.setState(csvRecord.get(3));
            complaint.setZipCode(csvRecord.get(4));
            complaint.setComplaintId(csvRecord.get(5));
        } else
            return null;

        return complaint;
    }
}
