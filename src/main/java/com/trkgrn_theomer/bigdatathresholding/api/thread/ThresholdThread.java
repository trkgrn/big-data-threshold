package com.trkgrn_theomer.bigdatathresholding.api.thread;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.Threshold;
import com.trkgrn_theomer.bigdatathresholding.api.utility.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ThresholdThread extends Thread {
    private static long counter = 0;
    private long startTime;
    private long endTime;
    private long totalTime;

    private List<Complaint> partData;
    public static List<Complaint> allData;
    public static Complaint complaint;
    private List<Threshold> thresholds;

    private final StringUtil stringUtil;

    public ThresholdThread(String name, StringUtil stringUtil) {
        super(name);
        this.stringUtil = stringUtil;
    }

    public void run() {
        System.out.println(getName() + " is starting.");
        this.startTime = System.currentTimeMillis();

        this.thresholds = calculateSimilarityAverages();

        this.endTime = System.currentTimeMillis();
        this.totalTime = endTime - startTime;
        System.out.println(getName() + " is terminating");
    }

    public List<Threshold> calculateSimilarityAverages() {
        List<Threshold> similarities = new ArrayList<>();

        partData.forEach(destination -> {
                double similarity = stringUtil.getSimilarityAverage(new String[]{complaint.getCompany(), destination.getCompany()});
                if (similarity >= 0)
                    similarities.add(new Threshold(complaint, destination, similarity));
                counter++;
                  // System.out.println(counter);
        });
        return similarities;
    }

}
