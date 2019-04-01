package org.domru.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.domru.model.CarManufacturer;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CarManufacturerDto {
  private Long id;
  @NotNull(message = "Наименование не может быть пустым")
  @Length(
      max = 255,
      message = "Слишком длинное наименование"
  )
  private String name;

  public static CarManufacturerDto toDto(CarManufacturer carManufacturer) {
    return new CarManufacturerDto(carManufacturer.getId(), carManufacturer.getName());
  }

  public CarManufacturer fromDto() {
    return new CarManufacturer(id, name);
  }
}
