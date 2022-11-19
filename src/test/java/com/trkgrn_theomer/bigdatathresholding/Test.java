package com.trkgrn_theomer.bigdatathresholding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Test {
    static long x = 0;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (Integer i = 1; i <= 12144; i++) {
            list.add(i);
        }
        int dataCount = list.size();

        int thread = 9;
        int count = dataCount / thread;
        int remainingDataCount = dataCount % thread;
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < thread ; i++) {
            if (i==thread-1 && remainingDataCount != 0)
                lists.add(list.subList(i*count,dataCount));
            else
                lists.add(list.subList(i*count,(i+1)*count));
        }

        lists.forEach(l-> System.out.println(l.size()));

    }
}
