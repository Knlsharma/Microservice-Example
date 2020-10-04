package moviecatalogservice.demo.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import moviecatalogservice.demo.models.Rating;
import moviecatalogservice.demo.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRatingItem")
    public UserRating getUserRatingItem(@PathVariable("userId") String userId) {
        return restTemplate.getForObject("http://movie-rating-service/ratingsdata/user/" + userId, UserRating.class);
    }

    public UserRating getFallbackUserRatingItem(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(Arrays.asList(new Rating("0", 0)));
        return userRating;
    }

}
