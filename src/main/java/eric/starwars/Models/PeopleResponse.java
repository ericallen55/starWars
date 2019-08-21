package eric.starwars.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeopleResponse {
    Integer count;
    List<PeopleResults> results;

    public static PeopleResponse toPeopleResponese(SwapiPeople swapiPeople){
        List<PeopleResults> peopleResults = new ArrayList<>();
        swapiPeople.results.forEach(swapiResults -> peopleResults.add(PeopleResults.toPeopleResults(swapiResults)));
        return PeopleResponse.builder()
                .count(swapiPeople.count)
                .results(peopleResults)
                .build();
    }
}
