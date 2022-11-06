package com.trkgrn_theomer.bigdatathresholding.api.service;

import com.trkgrn_theomer.bigdatathresholding.api.model.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {
    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> saveAll(List<Complaint> complaints){
        return complaintRepository.saveAll(complaints);
    }
    public Complaint save(Complaint complaint){
        return complaintRepository.save(complaint);
    }

    public void deleteAll(){
        complaintRepository.deleteAll();
    }
}
