package org.domru.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
  @NotNull(message = "Регистрационный номер не может быть пустым")
  @Pattern(
      message = "Регистрационный номер не соответствует шаблону",
      regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}RUS\\d{2,3}$")
  private String regNum;
  @NotNull(message = "Модель автомобиля не может быть пустой")
  private CarModelDto carModel;

  public static CarDto toDto(Car car) {
    return new CarDto(car.getId(), car.getRegNum(), CarModelDto.toDto(car.getCarModel()));
  }

  public Car fromDto() {
    return new Car(id, regNum, carModel.fromDto());
  }
}
