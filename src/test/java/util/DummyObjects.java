package util;

import org.domru.dto.CarManufacturerDto;
import org.domru.dto.RenterDto;
import org.domru.model.Car;
import org.domru.model.CarManufacturer;
import org.domru.model.CarModel;
import org.domru.model.Renter;

import java.util.Collections;
import java.util.List;

public class DummyObjects {
  public static Renter getDummyRenter(){
    return new Renter(
        1L,
        "test",
        "test",
        "test"
    );
  }

  public static List<Renter> getDummyRenterList() {
    return Collections.singletonList(getDummyRenter());
  }

  public static RenterDto getDummyRenterDto() {
    return RenterDto.toDto(getDummyRenter());
  }

  public static List<RenterDto> getDummyRenterDtoList() {
    return Collections.singletonList(getDummyRenterDto());
  }

  public static CarManufacturer getDummyCarManufacturer(){
    return new CarManufacturer(
        1L,
        "test"
    );
  }

  public static List<CarManufacturer> getDummyCarmanufacturerList() {
    return Collections.singletonList(getDummyCarManufacturer());
  }

  public static CarManufacturerDto getDummyCarManufacturerDto() {
    return CarManufacturerDto.toDto(getDummyCarManufacturer());
  }

  public static List<CarManufacturerDto> getDummyCarManufacturerDtoList() {
    return Collections.singletonList(getDummyCarManufacturerDto());
  }

  public static String getLongString() {
    String longString = "";
    for (int i = 0; i < 27; i++) {
      longString += "aaaaaaaaaa";
    }
    return longString;
  }
}
