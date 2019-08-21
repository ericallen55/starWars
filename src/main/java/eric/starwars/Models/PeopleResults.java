package eric.starwars.Models;

import eric.starwars.Services.StarwarsService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeopleResults {
    String name;
    Double height;
    Double mass;
    String hairColor;
    String skinColor;
    String eyeColor;
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

    public static PeopleResults toPeopleResults(SwapiPeopleResults swapiPeopleResults){
        return PeopleResults.builder()
                .name(swapiPeopleResults.name)
                .height(StarwarsService.toDouble(swapiPeopleResults.height))
                .mass(StarwarsService.toDouble(swapiPeopleResults.mass))
                .hairColor(swapiPeopleResults.hairColor)
                .skinColor(swapiPeopleResults.skinColor)
                .eyeColor(swapiPeopleResults.eyeColor)
                .birthYear(swapiPeopleResults.birthYear)
                .gender(swapiPeopleResults.gender)
                .homeworld(swapiPeopleResults.homeworld)
                .films(swapiPeopleResults.films)
                .species(swapiPeopleResults.species)
                .vehicles(swapiPeopleResults.vehicles)
                .starships(swapiPeopleResults.starships)
                .created(swapiPeopleResults.created)
                .edited(swapiPeopleResults.edited)
                .url(swapiPeopleResults.url)
                .build();
    }
}
