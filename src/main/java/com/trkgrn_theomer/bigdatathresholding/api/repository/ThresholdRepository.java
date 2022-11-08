package com.trkgrn_theomer.bigdatathresholding.api.repository;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThresholdRepository extends JpaRepository<Threshold, Long> {
}