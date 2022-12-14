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

    public static List<Complaint> job;
    private List<Complaint> partData;
    public static List<Complaint> allData;
    public static Complaint complaint;
    private List<Threshold> thresholds;
    private double similarity;
    private double similarity2;
    private String selectedColumn;
    private String selectedColumn2;
    private String selectedScenario;

    private final StringUtil stringUtil;

    public ThresholdThread(String name, StringUtil stringUtil) {
        super(name);
        this.stringUtil = stringUtil;
    }

    public void run() {
        System.out.println(getName() + " is starting.");
        this.startTime = System.currentTimeMillis();

        switch (selectedScenario){
            case "S1":
                this.thresholds = calculateSimilarityAveragesS1();
                break;
            case "S2":
                this.thresholds = calculateSimilarityAveragesS2();
                break;
            case "S3":
                this.thresholds = calculateSimilarityAveragesS3();
                break;
        }


        this.endTime = System.currentTimeMillis();
        this.totalTime = endTime - startTime;
        System.out.println(getName() + " is terminating");
    }

    private static long ctrl =0;
    public List<Threshold> calculateSimilarityAveragesS1(){
        List<Threshold> similarities = new ArrayList<>();

        switch (this.selectedColumn) {
            case "Company":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getCompany(), destination.getCompany()});
                        if (similarity >= this.similarity)
                            similarities.add(new Threshold(origin, destination, similarity));
                        counter++;
                    });
                });
                break;
            case "Product":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getProduct(), destination.getProduct()});
                        if (similarity >= this.similarity)
                            similarities.add(new Threshold(origin, destination, similarity));
                        counter++;
                    });
                });
                break;
            case "Issue":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getIssue(), destination.getIssue()});
                        if (similarity >= this.similarity)
                            similarities.add(new Threshold(origin, destination, similarity));
                        counter++;
                    });
                });
                break;
            case "State":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getState(), destination.getState()});
                        if (similarity >= this.similarity)
                            similarities.add(new Threshold(origin, destination, similarity));
                        counter++;
                    });
                });
                break;
            case "ZIP Code":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getZipCode(), destination.getZipCode()});
                        if (similarity >= this.similarity)
                            similarities.add(new Threshold(origin, destination, similarity));
                        counter++;
                    });
                });
                break;
        }

        return similarities;
    }

    public List<Threshold> calculateSimilarityAveragesS2(){
        List<Threshold> similarities = new ArrayList<>();

        switch (this.selectedColumn) {
            case "Company":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getCompany(), destination.getCompany()});
                        if (similarity >= this.similarity){
                            double similarity2 = getSimilarityAverage(origin,destination,selectedColumn2);
                            if (similarity2 >= this.similarity2){
                                similarities.add(new Threshold(origin, destination,similarity, similarity2));
                            }
                        }
                        counter++;
                    });
                });
                break;
            case "Product":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getProduct(), destination.getProduct()});
                        if (similarity >= this.similarity){
                            double similarity2 = getSimilarityAverage(origin,destination,selectedColumn2);
                            if (similarity2 >= this.similarity2){
                                similarities.add(new Threshold(origin, destination,similarity, similarity2));
                            }
                        }
                        counter++;
                    });
                });
                break;
            case "Issue":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getIssue(), destination.getIssue()});
                        if (similarity >= this.similarity){
                            double similarity2 = getSimilarityAverage(origin,destination,selectedColumn2);
                            if (similarity2 >= this.similarity2){
                                similarities.add(new Threshold(origin, destination,similarity, similarity2));
                            }
                        }
                        counter++;
                    });
                });
                break;
            case "State":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getState(), destination.getState()});
                        if (similarity >= this.similarity){
                            double similarity2 = getSimilarityAverage(origin,destination,selectedColumn2);
                            if (similarity2 >= this.similarity2){
                                similarities.add(new Threshold(origin, destination,similarity, similarity2));
                            }
                        }
                        counter++;
                    });
                });
                break;
            case "ZIP Code":
                job.forEach(origin->{
                    partData.forEach(destination -> {
                        double similarity = stringUtil.getSimilarityAverage(new String[]{origin.getZipCode(), destination.getZipCode()});
                        if (similarity >= this.similarity){
                            double similarity2 = getSimilarityAverage(origin,destination,selectedColumn2);
                            if (similarity2 >= this.similarity2){
                                similarities.add(new Threshold(origin, destination,similarity ,similarity2));
                            }
                        }
                        counter++;
                    });
                });
                break;
        }

        return similarities;
    }
    public double getSimilarityAverage(Complaint data1, Complaint data2, String column){
        double similarityAverage = 0;
        switch (column){
            case "Company":
                similarityAverage = stringUtil.getSimilarityAverage(new String[]{data1.getCompany(),data2.getCompany()});
                break;
            case "Product":
                similarityAverage = stringUtil.getSimilarityAverage(new String[]{data1.getProduct(),data2.getProduct()});
                break;
            case "Issue":
                similarityAverage = stringUtil.getSimilarityAverage(new String[]{data1.getIssue(),data2.getIssue()});
                break;
            case "State":
                similarityAverage = stringUtil.getSimilarityAverage(new String[]{data1.getState(),data2.getState()});
                break;
            case "ZIP Code":
                similarityAverage = stringUtil.getSimilarityAverage(new String[]{data1.getZipCode(),data2.getZipCode()});
                break;
        }
        return similarityAverage;
    }

    public List<Threshold> calculateSimilarityAveragesS3() {
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
