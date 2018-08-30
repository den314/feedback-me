package pl.desz.feedbackme.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
public class UserDetails {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @JsonCreator
    public UserDetails(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
