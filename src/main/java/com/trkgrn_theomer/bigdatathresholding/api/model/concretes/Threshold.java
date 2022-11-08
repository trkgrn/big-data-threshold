package com.trkgrn_theomer.bigdatathresholding.api.model.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public",name = "threshold")
public class Threshold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "threshold_id")
    private Long thresholdId;

    @ManyToOne
    @JoinColumn(name = "origin_complaint_id",referencedColumnName = "complaint_id")
    private Complaint originComplaint;

    @ManyToOne
    @JoinColumn(name = "destination_complaint_id",referencedColumnName = "complaint_id")
    private Complaint destinationComplaint;

    @Column(name = "similarity_average")
    private double similarityAverage;
}
