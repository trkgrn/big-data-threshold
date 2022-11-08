package com.trkgrn_theomer.bigdatathresholding.api.thread;

import com.trkgrn_theomer.bigdatathresholding.api.utility.CSVUtil;
import lombok.Data;

import java.io.IOException;

@Data
public class ThresholdThread extends Thread {
    private long startTime;
    private long endTime;
    private long totalTime;

    private final CSVUtil csvUtil;

    private int pageNo;
    private int pageSize;

    public ThresholdThread(String name, CSVUtil csvUtil) {
        super(name);
        this.csvUtil = csvUtil;
    }

    public void run() {
        System.out.println(getName() + " is starting.");
        this.startTime = System.currentTimeMillis();

        try {
            csvUtil.findThreshold(pageNo,pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.endTime = System.currentTimeMillis();
        this.totalTime = endTime - startTime;
        System.out.println(getName() + " is terminating");
    }

}
