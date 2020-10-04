package moviecatalogservice.demo.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import moviecatalogservice.demo.models.CatalogItem;
import moviecatalogservice.demo.models.Movie;
import moviecatalogservice.demo.models.Rating;
import moviecatalogservice.demo.models.UserRating;

@RestController
@RequestMapping("/catalog")

public class MovieCatalogService {

    @Autowired
    private RestTemplate resttemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private UserRatingInfo userRatingInfo;

    @Autowired
    private MovieInfo movieInfo;

    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        WebClient.Builder builder = WebClient.builder();
        //	return  Collections.singletonList(new CatalogItem("kunal" , 19));

        RestTemplate restTemplate = new RestTemplate();

        UserRating ratings = userRatingInfo.getUserRatingItem(userId);

        //	 return ratings.stream()
        //    .map(rating -> new CatalogItem("Name", "Desc", rating.getRating()))

        // Hardcored URL Bad Approach
        return ratings.getRatings().stream().map(rating -> {
            Movie movie = movieInfo.getMovieItem(rating);

// Using Web Client
	                   
 /*	Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/ " +rating.getMovieId()).retrieve()
		 .bodyToMono(Movie.class).block(); 
*/

            return new CatalogItem(movie.getName(), rating.getRating());
        })
                .collect(Collectors.toList());
    }


    //   added fallback mechanism
    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        return Arrays.asList(new CatalogItem("No movie", 0));
    }

}
