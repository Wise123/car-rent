package org.domru.repository;

import org.domru.model.CarManufactorer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarManufactorerRepository extends JpaRepository<CarManufactorer, Long> {
}
