package com.trkgrn_theomer.bigdatathresholding.api.repository;

import com.trkgrn_theomer.bigdatathresholding.api.model.concretes.Complaint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {

    @Query(value = " SELECT DISTINCT c.product FROM Complaint as c")
    List<String> getDistinctProducts();

    @Query(value = " SELECT DISTINCT c.company FROM Complaint as c")
    List<String> getDistinctCompanies();

    @Query(value = " SELECT DISTINCT c.product FROM Complaint as c")
    List<String> getDistinctProductsByPage(Pageable pageable);

    @Query(value = " SELECT DISTINCT c.company FROM Complaint as c")
    List<String> getDistinctCompaniesByPage(Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM complaint as c WHERE  c.product=:#{#product}")
    List<Complaint> getAllByProductName(String product);

}