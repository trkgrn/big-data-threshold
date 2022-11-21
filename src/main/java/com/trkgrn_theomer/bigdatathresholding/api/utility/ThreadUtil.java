package com.trkgrn_theomer.bigdatathresholding.api.utility;


import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.model.dtos.SimilarityAverage;
import com.trkgrn_theomer.bigdatathresholding.api.thread.TestThread;
import com.trkgrn_theomer.bigdatathresholding.api.thread.ThresholdThread;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ThreadUtil {

    public void waitAllThread(List<ThresholdThread> threads) {
        threads.stream().forEach(myThread -> {
            try {
                myThread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread is interrupted");
            }
        });
    }

    public void printProcessTimeByThreads(List<ThresholdThread> threads) {
        waitAllThread(threads);
        threads.stream().forEach(myThread -> {
            System.out.println(myThread.getName() + " adlı " + myThread.getId() + " ID'sine sahip threadin toplam çalışma" +
                    " süresi " + myThread.getTotalTime() + " ms");
        });
    }


    private void waitAllThread2(List<TestThread> threads) {
        threads.stream().forEach(myThread -> {
            try {
                myThread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread is interrupted");
            }
        });
    }


    public void printProcessTimeByThreads2(List<TestThread> threads) {
        waitAllThread2(threads);
        threads.stream().forEach(myThread -> {
            System.out.println(myThread.getName() + " adlı " + myThread.getId() + " ID'sine sahip threadin toplam çalışma" +
                    " süresi " + myThread.getTotalTime() + " ms");
        });
    }

    public List<List<Complaint>> splitComplaints(List<Complaint> complaints, int threadCount){
        int dataCount = complaints.size();
        int dividedJobCount = dataCount / threadCount;
        int remainingDataCount = dataCount % threadCount;
        List<List<Complaint>> jobs = new ArrayList<>();
        for (int i = 0; i < threadCount ; i++) {
            if (i==threadCount-1 && remainingDataCount != 0)
                jobs.add(complaints.subList(i*dividedJobCount,dataCount));
            else
                jobs.add(complaints.subList(i*dividedJobCount,(i+1)*dividedJobCount));
        }
        int total = jobs.stream().mapToInt(List::size).sum();
        System.out.println("Toplam İş: "+total);
        return jobs;
    }

    public List<List<SimilarityAverage>> splitSimilarities(List<SimilarityAverage> similarityAverages, int threadCount){
        int dataCount = similarityAverages.size();
        int dividedJobCount = dataCount / threadCount;
        int remainingDataCount = dataCount % threadCount;
        List<List<SimilarityAverage>> jobs = new ArrayList<>();
        for (int i = 0; i < threadCount ; i++) {
            if (i==threadCount-1 && remainingDataCount != 0)
                jobs.add(similarityAverages.subList(i*dividedJobCount,dataCount));
            else
                jobs.add(similarityAverages.subList(i*dividedJobCount,(i+1)*dividedJobCount));
        }
        int total = jobs.stream().mapToInt(List::size).sum();
      //  jobs.stream().forEach(job-> System.out.println(job.size()));
        System.out.println("Toplam İş: "+total);
        return jobs;
    }




}
