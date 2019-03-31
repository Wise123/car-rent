package org.domru.repository;

import org.domru.model.CarManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarManufacturerRepository extends JpaRepository<CarManufacturer, Long> {
}
