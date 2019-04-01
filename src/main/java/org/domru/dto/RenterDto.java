package org.domru.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.domru.model.Renter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RenterDto {
  private Long id;
  @NotNull(message = "Имя не должно быть пустым")
  @Length(
      max = 255,
      message = "Имя слишком длинное"
  )
  private String firstName;
  @NotNull(message = "Фамилия не должна быть пустой")
  @Length(
      max = 255,
      message = "Фамилия слишком длинная"
  )
  private String surname;
  @NotNull(message = "Отчество не должно быть пустым")
  @Length(
      max = 255,
      message = "Отчество слишком длинное"
  )
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
