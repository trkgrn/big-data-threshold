package com.trkgrn_theomer.bigdatathresholding.api.thread;

import lombok.Data;

import java.io.File;

@Data
public class ThresholdThread extends Thread {
    private long startTime;
    private long endTime;
    private long totalTime;

    private File file;

    public ThresholdThread(String name) {
        super(name);
    }

    public void run() {
        System.out.println(getName() + " is starting.");
        this.startTime = System.currentTimeMillis();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(file.getPath());
        this.endTime = System.currentTimeMillis();
        this.totalTime = endTime - startTime;
        System.out.println(getName() + " is terminating");
    }
}
