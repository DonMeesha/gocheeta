package com.gocheeta.api.repository;

import com.gocheeta.api.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT(*) from Vehicle WHERE branch_details_id = ?1", nativeQuery = true)
    List<Vehicle> getVehicleBelongsToBranch(Long branchId);

    @Query(value = "SELECT(*) from Vehicle WHERE category_details_id = ?1", nativeQuery = true)
    List<Vehicle> getVehicleBelongsToCategory(Long id);
}
