package org.domru.repository;

import java.util.List;

import org.domru.model.RentalSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalSessionRepository extends JpaRepository<RentalSession, Long> {
  @Query("select r from RentalSession r where r.car.id = :carId")
  public List<RentalSession> findByFilters(
      @Param("carId") Long carId
  );

  @Query("select Avg(cast ((r.sessionEnd - r.sessionStart) as integer)) "
      + "from RentalSession r join r.car.carModel.carManufacturers mf "
      + "where :carManufacturerId in mf.id and "
      + "r.startRentPoint.id = :rentPointId "
      + "group by mf.id ,r.startRentPoint.id")
  public Long getAverageSessionLength(
      @Param("carManufacturerId") Long carManufacturerId,
      @Param("rentPointId") Long rentPointId);
}
