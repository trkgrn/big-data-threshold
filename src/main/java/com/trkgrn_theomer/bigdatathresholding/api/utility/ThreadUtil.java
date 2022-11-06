package com.trkgrn_theomer.bigdatathresholding.api.utility;


import com.trkgrn_theomer.bigdatathresholding.api.thread.ThresholdThread;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThreadUtil {

    private void waitAllThread(List<ThresholdThread> threads) {
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

}
