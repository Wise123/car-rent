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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate sessionStart;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate sessionEnd;

  @ManyToOne
  @JoinColumn(name = "startRentPointId")
  RentPoint startRentPoint;

  @ManyToOne
  @JoinColumn(name = "endRentPointId")
  RentPoint endRentPoint;

  @ManyToOne
  @JoinColumn(name = "renterId")
  Renter renter;

  @ManyToOne
  @JoinColumn(name = "carId")
  Car car;
}
