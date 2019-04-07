package util;

import org.domru.dto.RenterDto;
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

  public static String getLongString() {
    String longString = "";
    for (int i = 0; i < 27; i++) {
      longString += "aaaaaaaaaa";
    }
    return longString;
  }
}
