package pl.desz.feedbackme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.desz.feedbackme.domain.Feedback;
import pl.desz.feedbackme.domain.User;
import pl.desz.feedbackme.domain.UserDetails;
import pl.desz.feedbackme.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;

/*
 * Loads initial data programmatically
 */
@Component
public class DataProcessor {

    @Autowired
    private UserRepository ur;

    @PostConstruct
    private void init() {
        ur.save(createOneUser());
    }

    private User createOneUser() {
        final User user1 = new User();
        final UserDetails userDetails1 = new UserDetails("john", "doe");
        user1.setUserDetails(userDetails1);
        ur.save(user1);

        final User user = new User();

        final UserDetails userDetails = new UserDetails("Den", "Jan");
        user.setUserDetails(userDetails);

        final Feedback feed0 = new Feedback();
        feed0.setText(textualFeedback());
        feed0.setUser(user);
        feed0.setGivenBy(user1);
        feed0.setGivenOn(new Date());
        final Feedback feed1 = new Feedback();
        feed1.setText(textualFeedback());
        feed1.setUser(user);
        feed1.setGivenBy(user1);
        user.setFeedbackList(Arrays.asList(feed0, feed1));

        return user;
    }

    private String textualFeedback() {
        return "Very nice person, always willing to help others. " +
                "I'd be more than happy to cooperate with him more, " +
                "we are quite efficient together.";
    }
}
