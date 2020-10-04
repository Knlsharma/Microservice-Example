package moviecatalogservice.demo.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import moviecatalogservice.demo.models.Movie;
import moviecatalogservice.demo.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMovieItem")
    public Movie getMovieItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new Movie(movie.getName(), movie.getMovieId());
    }

    public Movie getFallbackMovieItem(Rating rating) {
        return new Movie("No movie found", "no result");
    }

}
