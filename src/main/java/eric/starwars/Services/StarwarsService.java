package eric.starwars.Services;

import eric.starwars.Daos.StarwarsDao;
import eric.starwars.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StarwarsService {
    @Autowired
    StarwarsDao starwarsDao;

    public SwapiPlanets getAllPlanets(){
        SwapiPlanets planets = starwarsDao.getAllPlanets();
        //So instead of calling the person api for each person lets get all of them and then we can replace them
        //this will be faster most of the time.
        SwapiPeople people = starwarsDao.getAllPeople();
        Map<String, SwapiPeopleResults> personMap = new HashMap<>();
        people.getResults().forEach(swapiPeopleResults -> {
            personMap.put(swapiPeopleResults.getUrl(), swapiPeopleResults);
        });

        planets.getResults().forEach(planet -> {
            for(int i = 0; i < planet.getResidents().size(); i++){
                SwapiPeopleResults person = personMap.get(planet.getResidents().get(i));
//                SwapiPerson person = starwarsDao.getPersonByUrl(planet.getResidents().get(i));    //So I did this first using the philosophy get it working then optimise.
               planet.getResidents().set(i, person.getName());
            }
        });
        return planets;
    }

    public PeopleResponse getAllPeople(Optional<String> sortBy){
        return sortPeople(sortBy, PeopleResponse.toPeopleResponese(starwarsDao.getAllPeople()));
    }

    private PeopleResponse sortPeople(Optional<String> sortBy, PeopleResponse peopleResponse){
        if(sortBy.isPresent()){
            switch (sortBy.get().toLowerCase()){
                case "name":{
                    peopleResponse.getResults().sort(Comparator.comparing(PeopleResults::getName));
                    break;
                }
                case "height":{
                    Comparator<PeopleResults> peopleResultsHeightComparatorNullLast = Comparator.comparing(PeopleResults::getHeight,Comparator.nullsLast(Double::compareTo));
                    Collections.sort(peopleResponse.getResults(), peopleResultsHeightComparatorNullLast);
                    break;
                }
                case "mass":{
                    Comparator<PeopleResults> peopleResultsMassComparatorNullLast = Comparator.comparing(PeopleResults::getMass,Comparator.nullsLast(Double::compareTo));
                    Collections.sort(peopleResponse.getResults(), peopleResultsMassComparatorNullLast);
                    break;
                }
            }
        }
        return peopleResponse;
    }

    public static Double toDouble(String string){
        //I didn't want to introduce another external library just for this.
        try{
            return Double.parseDouble(string);
        } catch( NumberFormatException e){
            return null;
        }
    }
}
