package com.trkgrn_theomer.bigdatathresholding.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "public",name = "threshold")
public class Threshold {
    @Id
    @Column(name = "threshold_id")
    private Long thresholdId;

    @ManyToOne
    @JoinColumn(name = "origin_complaint_id",referencedColumnName = "complaint_id")
    private Complaint originComplaint;

    @ManyToOne
    @JoinColumn(name = "destination_complaint_id",referencedColumnName = "complaint_id")
    private Complaint destinationComplaint;
}
