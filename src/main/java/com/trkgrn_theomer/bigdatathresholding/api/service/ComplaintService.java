package com.trkgrn_theomer.bigdatathresholding.api.service;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import com.trkgrn_theomer.bigdatathresholding.api.repository.ComplaintRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Complaint> getAll(){
        return complaintRepository.findAll();
    }

    public List<Complaint> getAllByPage(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return this.complaintRepository.findAll(pageable).getContent();
    }

    public long count(){
        return this.complaintRepository.count();
    }

    public List<String> getProducts(){
        return this.complaintRepository.getDistinctProducts();
    }

    public List<String> getCompanies(){
        return this.complaintRepository.getDistinctCompanies();
    }

    public List<String> getProductsByPage(int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return this.complaintRepository.getDistinctProductsByPage(pageable);
    }

    public List<String> getCompaniesByPage(int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return this.complaintRepository.getDistinctCompaniesByPage(pageable);
    }

    public List<Complaint> getAllByProduct(String product){
        return this.complaintRepository.getAllByProductName(product);
    }



}
