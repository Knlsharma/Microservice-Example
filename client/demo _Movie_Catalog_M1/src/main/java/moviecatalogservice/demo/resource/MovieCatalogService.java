package moviecatalogservice.demo.resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    
	@RequestMapping("/{userId}")	   
	    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) 
	   
	   {
		
		WebClient.Builder builder = WebClient.builder();
	//	return  Collections.singletonList(new CatalogItem("kunal" , 19));  
	   
    RestTemplate restTemplate = new RestTemplate();
		
		 UserRating ratings = resttemplate.getForObject("http://movie-rating-service/ratingsdata/user/" + userId, UserRating.class);
	                

		
	//	 return ratings.stream()
	            //    .map(rating -> new CatalogItem("Name", "Desc", rating.getRating()))
			
				 
				 // Hardcored URL Bad Approach
		 return ratings.getRatings().stream().map(rating -> {
	                   Movie movie = resttemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
	                  
	         // Using Web Client
	                   
 /*	Movie movie = 	 webClientBuilder.build().get().uri("http://localhost:8082/movies/ " +rating.getMovieId()).retrieve()
		 .bodyToMono(Movie.class).block(); 
	*/	

		 
					 return new CatalogItem(movie.getName(), rating.getRating());
	                })
	                .collect(Collectors.toList());
		
	   }
}
