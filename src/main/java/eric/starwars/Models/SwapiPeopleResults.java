package eric.starwars.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SwapiPeopleResults {
    String name;
    String height;
    String mass;
    @JsonAlias("hair_color")
    String hairColor;
    @JsonAlias("skin_color")
    String skinColor;
    @JsonAlias("eye_color")
    String eyeColor;
    @JsonAlias("birth_year")
    String birthYear;
    String gender;
    String homeworld;
    List<String> films;
    List<String> species;
    List<String> vehicles;
    List<String> starships;
    String created;
    String edited;
    String url;
}
