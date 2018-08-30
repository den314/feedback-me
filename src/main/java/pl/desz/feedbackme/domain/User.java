package pl.desz.feedbackme.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id @GeneratedValue
    private Long id;

    @NotNull
    @Embedded
    private UserDetails userDetails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbackList = new ArrayList<>();
}
