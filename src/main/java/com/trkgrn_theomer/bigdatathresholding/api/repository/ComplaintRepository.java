package com.trkgrn_theomer.bigdatathresholding.api.repository;

import com.trkgrn_theomer.bigdatathresholding.api.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {
}