package com.gocheeta.api.repository;

import com.gocheeta.api.entity.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistanceRepository extends JpaRepository<Distance, Long> {
    List<Distance> findByStartCityId(Long id);
}
