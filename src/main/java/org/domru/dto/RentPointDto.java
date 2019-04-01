package org.domru.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.domru.model.RentPoint;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RentPointDto {
  private Long id;
  @NotNull(message = "Адрес не должен быть пустым")
  @Length(max = 255, message = "Адрес слишком длинный")
  private String address;

  public static RentPointDto toDto(RentPoint rentPoint) {
    return new RentPointDto(rentPoint.getId(), rentPoint.getAddress());
  }

  public RentPoint fromDto() {
    return new RentPoint(id, address);
  }
}
