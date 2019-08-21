package eric.starwars.Daos;

import eric.starwars.Models.SwapiPeople;
import eric.starwars.Models.SwapiPerson;
import eric.starwars.Models.SwapiPlanets;
import org.apache.commons.collections4.ListUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StarwarsDao {
    private final String baseUrl = "https://swapi.co/api/";

    private RestTemplate createRestTemplate() {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

    public SwapiPeople getAllPeople() {
        String url = baseUrl + "people";
        RestTemplate restTemplate = createRestTemplate();
        SwapiPeople people = restTemplate.getForObject(url, SwapiPeople.class);
        while (null != people.getNext()){
            SwapiPeople tempPeople = restTemplate.getForObject(people.getNext(), SwapiPeople.class);
            people.setNext(tempPeople.getNext());
            people.setResults(ListUtils.union(people.getResults(), tempPeople.getResults()));
        }
        return people;
    }

    public SwapiPlanets getAllPlanets(){
        String url = baseUrl + "planets";
        RestTemplate restTemplate = createRestTemplate();
        SwapiPlanets planets = restTemplate.getForObject(url, SwapiPlanets.class);
        while (null != planets.getNext()){
            SwapiPlanets tempPlanets = restTemplate.getForObject(planets.getNext(), SwapiPlanets.class);
            planets.setNext(tempPlanets.getNext());
            planets.setResults(ListUtils.union(planets.getResults(), tempPlanets.getResults()));
        }
        return planets;
    }

    public SwapiPerson getPersonByUrl(String url){
        RestTemplate restTemplate = createRestTemplate();
        return restTemplate.getForObject(url, SwapiPerson.class);
    }
}
