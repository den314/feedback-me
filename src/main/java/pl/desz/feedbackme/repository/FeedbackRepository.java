package pl.desz.feedbackme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.desz.feedbackme.domain.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
