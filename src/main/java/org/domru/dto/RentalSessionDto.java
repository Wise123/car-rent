package org.domru.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.domru.model.RentalSession;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RentalSessionDto {
  private Long id;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate sessionStart;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate sessionEnd;
  private RentPointDto startRentPoint;
  private RentPointDto endRentPoint;
  private RenterDto renter;
  private CarDto car;

  /**
   * TODO.
   * @param rentalSession TODO
   * @return TODO
   */
  public static RentalSessionDto toDto(RentalSession rentalSession) {
    return new RentalSessionDto(
        rentalSession.getId(),
        rentalSession.getSessionStart(),
        rentalSession.getSessionEnd(),
        RentPointDto.toDto(rentalSession.getStartRentPoint()),
        RentPointDto.toDto(rentalSession.getEndRentPoint()),
        RenterDto.toDto(rentalSession.getRenter()),
        CarDto.toDto(rentalSession.getCar())
    );
  }

  /**
   * TODO.
   * @return TODO
   */
  public RentalSession fromDto() {
    return new RentalSession(
        id,
        sessionStart,
        sessionEnd,
        startRentPoint.fromDto(),
        endRentPoint.fromDto(),
        renter.fromDto(),
        car.fromDto()
    );
  }
}
