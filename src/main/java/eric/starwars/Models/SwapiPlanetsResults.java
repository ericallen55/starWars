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
public class SwapiPlanetsResults {
    String name;
    @JsonAlias("rotation_period")
    String rotationPeriod;
    @JsonAlias("orbital_period")
    String orbitalPeriod;
    String diameter;
    String climate;
    String gravity;
    String terrain;
    @JsonAlias("surface_water")
    String surfaceWater;
    String population;
    List<String> residents;
    List<String> films;
    String created;
    String edited;
    String url;
}
