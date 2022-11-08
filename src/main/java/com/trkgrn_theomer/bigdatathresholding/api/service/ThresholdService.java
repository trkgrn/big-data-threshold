package com.trkgrn_theomer.bigdatathresholding.api.service;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Threshold;
import com.trkgrn_theomer.bigdatathresholding.api.repository.ThresholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThresholdService {

    private final ThresholdRepository thresholdRepository;

    public ThresholdService(ThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    public List<Threshold> saveAll(List<Threshold> thresholds){
        return thresholdRepository.saveAll(thresholds);
    }
}
