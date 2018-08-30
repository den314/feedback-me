package pl.desz.feedbackme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.desz.feedbackme.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
