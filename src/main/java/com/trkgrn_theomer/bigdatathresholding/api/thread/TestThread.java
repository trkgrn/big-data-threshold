package com.trkgrn_theomer.bigdatathresholding.api.thread;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Threshold;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.SimilarityAverage;
import com.trkgrn_theomer.bigdatathresholding.api.service.ComplaintService;
import com.trkgrn_theomer.bigdatathresholding.api.service.ThresholdService;
import com.trkgrn_theomer.bigdatathresholding.api.utility.StringUtil;
import com.trkgrn_theomer.bigdatathresholding.api.utility.TableUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestThread extends Thread{
    private static long counter = 0;
    private long startTime;
    private long endTime;
    private long totalTime;

    private final StringUtil stringUtil;

    private final ThresholdService thresholdService;

    private final ComplaintService complaintService;

    private List<String> partData;
    private List<String> allData;
    private List<SimilarityAverage> similarityAverages;

    public TestThread(String name, StringUtil stringUtil, ThresholdService thresholdService, ComplaintService complaintService) {
        super(name);
        this.stringUtil = stringUtil;
        this.thresholdService = thresholdService;
        this.complaintService = complaintService;
    }

    public void run() {
        System.out.println(getName() + " is starting."+partData.size());
        this.startTime = System.currentTimeMillis();

        this.similarityAverages = test();
        System.out.println(getName() + " average list size:"+this.similarityAverages.size());
     //   similarityPrintToDatabase(similarityAverages);

        this.endTime = System.currentTimeMillis();
        this.totalTime = endTime - startTime;
        System.out.println(getName() + " is terminating."+similarityAverages.size());
    }

    public List<SimilarityAverage> test(){
        List<SimilarityAverage> averages = new ArrayList<>();
        partData.stream().forEach(data1->{
            allData.stream().forEach(data2->{
              double average = stringUtil.getSimilarityAverage(new String[]{data1,data2});
                if (average>=50.0){
                    averages.add(new SimilarityAverage(data1,data2,average));
                  //    System.out.println(counter+"- "+ data1+" & "+data2 +" Average:"+average);
                }

            });

        });
        return averages;
    }

    public void similarityPrintToDatabase(List<SimilarityAverage> similarityAverages){
        similarityAverages.stream().forEach(similarityAverage -> {
            System.out.println(similarityAverage);

            List<Complaint> origins = complaintService.getAllByProduct(similarityAverage.getData1());
            List<Complaint> destinations = complaintService.getAllByProduct(similarityAverage.getData2());

            origins.stream().forEach(origin->{
                List<Threshold> thresholds = new ArrayList<>();
                destinations.stream().forEach(destination->{
                    thresholds.add(new Threshold(0L,origin,destination,similarityAverage.getAverage()));
                });
                counter = counter + thresholds.size();

               // thresholdService.saveAll(thresholds);
            });
            System.out.println("İşlenen veri: " +counter);
        });

      //  thresholdService.saveAll(thresholds);



    }
}
