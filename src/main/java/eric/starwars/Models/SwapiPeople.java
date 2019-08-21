package eric.starwars.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SwapiPeople {
    Integer count;
    String next;
    String previous;
    List<SwapiPeopleResults> results;
}
