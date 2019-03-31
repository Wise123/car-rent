package org.domru.repository;

import org.domru.model.RentPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentPointRepository extends JpaRepository<RentPoint, Long> {
}
