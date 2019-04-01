package org.domru.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.domru.model.RentPoint;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RentPointDto {
  private Long id;
  private String address;

  public static RentPointDto toDto(RentPoint rentPoint) {
    return new RentPointDto(rentPoint.getId(), rentPoint.getAddress());
  }

  public RentPoint fromDto() {
    return new RentPoint(id, address);
  }
}
