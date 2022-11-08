package com.trkgrn_theomer.bigdatathresholding.api.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimilarityAverage {
    private String data1;
    private String data2;
    private double average;
}
