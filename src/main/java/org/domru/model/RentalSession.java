package org.domru.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RentalSession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate sessionStart;
  private LocalDate sessionEnd;

  @ManyToOne
  @JoinColumn(name = "startRentPointId")
  private RentPoint startRentPoint;

  @ManyToOne
  @JoinColumn(name = "endRentPointId")
  private RentPoint endRentPoint;

  @ManyToOne
  @JoinColumn(name = "renterId")
  private Renter renter;

  @ManyToOne
  @JoinColumn(name = "carId")
  private Car car;
}
