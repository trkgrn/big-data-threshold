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
    private double similarity;
    private String selectedColumn;

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

        switch (this.selectedColumn) {
            case "Company":
                partData.forEach(destination -> {
                    double similarity = stringUtil.getSimilarityAverage(new String[]{complaint.getCompany(), destination.getCompany()});
                    if (similarity >= this.similarity)
                        similarities.add(new Threshold(complaint, destination, similarity));
                    counter++;
                });
                break;
            case "Product":
                partData.forEach(destination -> {
                    double similarity = stringUtil.getSimilarityAverage(new String[]{complaint.getProduct(), destination.getProduct()});
                    if (similarity >= this.similarity)
                        similarities.add(new Threshold(complaint, destination, similarity));
                    counter++;
                });
                break;
            case "Issue":
                partData.forEach(destination -> {
                    double similarity = stringUtil.getSimilarityAverage(new String[]{complaint.getIssue(), destination.getIssue()});
                    if (similarity >= this.similarity)
                        similarities.add(new Threshold(complaint, destination, similarity));
                    counter++;
                });
                break;
            case "State":
                partData.forEach(destination -> {
                    double similarity = stringUtil.getSimilarityAverage(new String[]{complaint.getState(), destination.getState()});
                    if (similarity >= this.similarity)
                        similarities.add(new Threshold(complaint, destination, similarity));
                    counter++;
                });
                break;
            case "ZIP Code":
                partData.forEach(destination -> {
                    double similarity = stringUtil.getSimilarityAverage(new String[]{complaint.getZipCode(), destination.getZipCode()});
                    if (similarity >= this.similarity)
                        similarities.add(new Threshold(complaint, destination, similarity));
                    counter++;
                });
                break;
        }

        return similarities;
    }

}
