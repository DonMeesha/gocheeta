package com.gocheeta.api.repository;

import com.gocheeta.api.entity.StreetAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StreetAddressRepository extends JpaRepository<StreetAddress, Long> {

    @Query(value = "SELECT * FROM StreetAddress WHERE branch_details_id = ?1", nativeQuery = true)
    List<StreetAddress> getStreetAddressForGivenBranchId(Long id);
}
