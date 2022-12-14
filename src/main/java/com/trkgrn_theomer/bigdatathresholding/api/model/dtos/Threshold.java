package com.trkgrn_theomer.bigdatathresholding.api.model.dtos;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Threshold {

    private Complaint originComplaint;

    private Complaint destinationComplaint;

    private double similarityAverage;

    private double similarityAverage2;

    public Threshold(Complaint originComplaint, Complaint destinationComplaint, double similarityAverage) {
        this.originComplaint = originComplaint;
        this.destinationComplaint = destinationComplaint;
        this.similarityAverage = similarityAverage;
    }
}
