package pl.desz.feedbackme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.desz.feedbackme.domain.Feedback;
import pl.desz.feedbackme.domain.User;
import pl.desz.feedbackme.exception.Message;
import pl.desz.feedbackme.exception.ResourceNotFound;
import pl.desz.feedbackme.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final Supplier<ResourceNotFound> RESOURCE_NOT_FOUND = () ->
            new ResourceNotFound("Given resource not found.");

    private UserRepository ur;

    public UserController(UserRepository ur) {
        this.ur = ur;
    }

    @GetMapping
    public List<User> all() {
        return ur.findAll();
    }

    @GetMapping("{id}")
    public User byId(@PathVariable("id") Long id) {
        return ur.findById(id).orElseThrow(RESOURCE_NOT_FOUND);
    }

    @GetMapping("{id}/feedback")
    public List<Feedback> userFeedbacks(@PathVariable("id") Long id) {
        return byId(id).getFeedbackList();
    }

    @GetMapping("{id}/feedback/{fid}")
    public Feedback userFeedbackById(@PathVariable("id") Long id,
                                     @PathVariable("fid") Long fid) {
        return byId(id).getFeedbackList()
                .stream()
                .filter(f -> f.getId().equals(fid))
                .findFirst().orElseThrow(ResourceNotFound::new);
    }

    @PostMapping
    public void insert(@Valid @RequestBody User user, HttpServletResponse response) throws IOException {
        ur.save(user);
        response.sendRedirect("/user/" + user.getId());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            ur.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.debug("User with id {} was not found in the database", id);
            throw new ResourceNotFound();
        }
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity handleResourceNotFound(ResourceNotFound ex) {
        return ResponseEntity.notFound().build();
    }
}
