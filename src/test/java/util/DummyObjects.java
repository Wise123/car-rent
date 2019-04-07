package util;

import org.domru.dto.CarDto;
import org.domru.dto.CarManufacturerDto;
import org.domru.dto.CarModelDto;
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

  public static CarModel getDummyCarModel(){
    return new CarModel(
        1L,
        "test",
        getDummyCarmanufacturerList()
    );
  }

  public static List<CarModel> getDummyCarModelList() {
    return Collections.singletonList(getDummyCarModel());
  }

  public static CarModelDto getDummyCarModelDto() {
    return CarModelDto.toDto(getDummyCarModel());
  }

  public static List<CarModelDto> getDummyCarModelDtoList() {
    return Collections.singletonList(getDummyCarModelDto());
  }

  public static Car getDummyCar(){
    return new Car(
        1L,
        "А111ААRUS11",
        getDummyCarModel()
    );
  }

  public static List<Car> getDummyCarList() {
    return Collections.singletonList(getDummyCar());
  }

  public static CarDto getDummyCarDto() {
    return CarDto.toDto(getDummyCar());
  }

  public static List<CarDto> getDummyCarDtoList() {
    return Collections.singletonList(getDummyCarDto());
  }

  public static String getLongString() {
    String longString = "";
    for (int i = 0; i < 27; i++) {
      longString += "aaaaaaaaaa";
    }
    return longString;
  }
}
