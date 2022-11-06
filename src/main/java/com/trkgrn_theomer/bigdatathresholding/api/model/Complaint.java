package com.trkgrn_theomer.bigdatathresholding.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "public",name = "complaint")
public class Complaint {
    @Id
    @Column(name = "complaint_id")
    private String complaintId;

    @Column(name = "product")
    private String product;

    @Column(name = "issue")
    private String issue;

    @Column(name = "company")
    private String company;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;
}
