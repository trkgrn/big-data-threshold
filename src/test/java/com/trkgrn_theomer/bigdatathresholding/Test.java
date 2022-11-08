package com.trkgrn_theomer.bigdatathresholding;

import java.util.stream.IntStream;

public class Test {
    static long x = 0;
    public static void main(String[] args) {

        IntStream.range(0, 8).parallel().forEach(i->{
            long m = 0;
            while (x<=10000000){
                x++;
                System.out.println(x + " THREAD"+i);
            }
        });
    }
}
