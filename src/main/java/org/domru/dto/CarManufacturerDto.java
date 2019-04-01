package org.domru.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.domru.model.CarManufacturer;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CarManufacturerDto {
  private Long id;
  private String name;

  public static CarManufacturerDto toDto(CarManufacturer carManufacturer) {
    return new CarManufacturerDto(carManufacturer.getId(), carManufacturer.getName());
  }

  public CarManufacturer fromDto() {
    return new CarManufacturer(id, name);
  }
}
