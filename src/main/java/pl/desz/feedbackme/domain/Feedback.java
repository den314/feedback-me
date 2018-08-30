package pl.desz.feedbackme.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@ToString(exclude = {"user"})
public class Feedback {

    @Id @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 50, max = 1000)
    private String text;

    @OneToOne
    @JsonIgnoreProperties(value = {"feedbackList"})
    @NotNull
    private User givenBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date givenOn = new Date();

    @ManyToOne
    @JsonIgnore
    private User user;
}
