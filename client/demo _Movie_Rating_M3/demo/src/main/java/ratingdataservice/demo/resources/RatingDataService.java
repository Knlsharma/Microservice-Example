package ratingdataservice.demo.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ratingdataservice.demo.models.Rating;
import ratingdataservice.demo.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataService 
{
	

	  @RequestMapping("/movies/{movieId}")
	  
	   public Rating getMovieRating(@PathVariable("movieId") String movieId) {
	        return new Rating(movieId, 4);
	  }
	    
	    
	    @RequestMapping("/user/{userId}")
	    public UserRating getUserRatings(@PathVariable("userId") String userId) {
	        UserRating userRating = new UserRating();
	        userRating.initData(userId);
	        return userRating;
	    
	}
}
