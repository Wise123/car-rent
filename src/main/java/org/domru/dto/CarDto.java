package org.domru.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.domru.model.Car;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CarDto {
  private Long id;
  private String regNum;
  private CarModelDto carModel;

  public static CarDto toDto(Car car) {
    return new CarDto(car.getId(), car.getRegNum(), CarModelDto.toDto(car.getCarModel()));
  }

  public Car fromDto() {
    return new Car(id, regNum, carModel.fromDto());
  }
}
