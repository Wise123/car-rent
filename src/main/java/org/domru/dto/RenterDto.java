package org.domru.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.domru.model.Renter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RenterDto {
  private Long id;
  private String firstName;
  private String surname;
  private String patronymic;

  /**
   * TODO.
   * @param renter TODO
   * @return TODO
   */
  public static RenterDto toDto(Renter renter) {
    return new RenterDto(
        renter.getId(),
        renter.getFirstName(),
        renter.getSurname(),
        renter.getPatronymic()
    );
  }

  /**
   * TODO.
   * @return TODO
   */
  public Renter fromDto() {
    return new Renter(id, firstName, surname, patronymic);
  }
}
