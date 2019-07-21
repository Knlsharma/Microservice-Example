package movieinfoservice.demo.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import movieinfoservice.demo.models.*;


@RestController
@RequestMapping("/movies")

public class MovieInfoService {

		@RequestMapping("/{movieId}")	   
		    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		        return new Movie(movieId, "KGP");
		   }
	}

