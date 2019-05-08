package client;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import web.dto.CustomerDto;
import web.dto.FilmDto;
import web.dto.FilmsDto;

/**
 * @author diananoveanu
 */

public class ClientApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "client.config"
                );

        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        CustomerDto newCustomer = CustomerDto.builder()
                                          .name("cNEW")
                                          .build();
        CustomerDto newNewCustomer = CustomerDto.builder()
                .name("Cezzicul")
                .build();

        CustomerDto savedCustomer = restTemplate.postForObject(
                "http://localhost:8080/customers",
                newCustomer,
                CustomerDto.class
        );

        CustomerDto savedaCustomer = restTemplate.postForObject(
                "http://localhost:8080/customers",
                newNewCustomer,
                CustomerDto.class
        );

        restTemplate.put(
                "http://localhost:8080/customers/{id}",
                savedCustomer,
                savedCustomer.getId()
        );
        restTemplate.put(
                "http://localhost:8080/customers/{id}",
                savedaCustomer,
                savedaCustomer.getId()
        );

        restTemplate.delete(
                "http://localhost:8080/customers/{id}",
                savedCustomer.getId()
        );

        FilmsDto newFilm = FilmsDto.builder()
                .build();

        FilmsDto filmsDto = restTemplate.getForObject(
                "http://localhost:8080/films",
                FilmsDto.class
        );
        FilmDto savedFilm = restTemplate.postForObject(
                "http://localhost:8080/films",
                newFilm,
                FilmDto.class
        );

        restTemplate.put(
                "http://localhost:8080/films/{id}",
                savedFilm,
                savedFilm.getId()
        );

        restTemplate.delete(
                "http://localhost:8080/films/{id}",
                savedFilm.getId()
        );


        System.out.println(filmsDto);
    }
}
