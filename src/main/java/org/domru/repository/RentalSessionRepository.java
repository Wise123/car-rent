package org.domru.repository;

import org.domru.model.RentalSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalSessionRepository extends JpaRepository<RentalSession, Long> {
}
