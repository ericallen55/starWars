package eric.starwars.Controllers;

import eric.starwars.Models.PeopleResponse;
import eric.starwars.Models.SwapiPlanets;
import eric.starwars.Services.StarwarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class StarwarsController {
    @Autowired
    StarwarsService starwarsService;

    @GetMapping("people")
    PeopleResponse getAllPeople(@RequestParam("sortBy") Optional<String> sortBy){
       return starwarsService.getAllPeople(sortBy);
    }

    @GetMapping("planets")
    SwapiPlanets getAllPlanets(){
        return starwarsService.getAllPlanets();
    }
}
