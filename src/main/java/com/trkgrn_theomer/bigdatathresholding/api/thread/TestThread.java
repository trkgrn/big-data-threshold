package com.trkgrn_theomer.bigdatathresholding.api.thread;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.Threshold;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.SimilarityAverage;
import com.trkgrn_theomer.bigdatathresholding.api.service.ComplaintService;
import com.trkgrn_theomer.bigdatathresholding.api.utility.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestThread extends Thread {
    private static long counter = 0;
    private long startTime;
    private long endTime;
    private long totalTime;

    private final StringUtil stringUtil;

    private List<Complaint> partData;
    public static List<Complaint> allData;
    private List<Threshold> thresholds;

    public TestThread(String name, StringUtil stringUtil) {
        super(name);
        this.stringUtil = stringUtil;
    }

    public void run() {
        System.out.println(getName() + " is starting. Part Data Size: " + partData.size());
        this.startTime = System.currentTimeMillis();

        this.thresholds = calculateSimilarityAverages();

        this.endTime = System.currentTimeMillis();
        this.totalTime = endTime - startTime;
        System.out.println(getName() + " is terminating." + thresholds.size());
    }

    public List<Threshold> calculateSimilarityAverages() {
        List<Threshold> similarities = new ArrayList<>();

        partData.forEach(origin -> {
            this.allData.forEach(destination -> {
                double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getCompany(), destination.getCompany()});
                if (similarity > 60)
                    similarities.add(new Threshold(origin, destination, similarity));
                counter++;
             //   System.out.println(counter);
            });

        });
        return similarities;
    }

}
