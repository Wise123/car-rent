package org.domru.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.domru.model.CarModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CarModelDto {
  private Long id;
  private String name;
  private List<CarManufacturerDto> carManufacturers;

  /**
   * TODO.
   * @param carModel TODO
   * @return TODO
   */
  public static CarModelDto toDto(CarModel carModel) {
    return new CarModelDto(
        carModel.getId(),
        carModel.getName(),
        carModel.getCarManufacturers()
            .stream()
            .map(CarManufacturerDto::toDto)
            .collect(Collectors.toList())
    );
  }

  /**
   * TODO.
   * @return TODO
   */
  public CarModel fromDto() {
    return new CarModel(
        id,
        name,
        carManufacturers
            .stream()
            .map(CarManufacturerDto::fromDto)
            .collect(Collectors.toList()));
  }
}
