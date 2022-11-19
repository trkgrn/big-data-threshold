package com.trkgrn_theomer.bigdatathresholding.api.repository;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {

    @Query(value = " SELECT DISTINCT c.product FROM Complaint as c")
    List<String> getDistinctProducts();

    @Transactional
    @Query(value = " SELECT DISTINCT c.company FROM Complaint as c")
    List<String> getDistinctCompanies();

    @Query(value = " SELECT DISTINCT c.product FROM Complaint as c")
    List<String> getDistinctProductsByPage(Pageable pageable);

    @Transactional
    @Query(value = " SELECT DISTINCT c.company FROM Complaint as c")
    List<String> getDistinctCompaniesByPage(Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM complaint as c WHERE  c.product=:#{#product}")
    List<Complaint> getAllByProductName(String product);

    @Transactional
    @Query(nativeQuery = true,value = "SELECT * FROM complaint as c WHERE  c.company=:#{#company}")
    List<Complaint> getAllByCompanyName(String company);

    Complaint getByComplaintId(String complaintId);

}