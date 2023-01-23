package ee.kaido.assignment.repository;

import ee.kaido.assignment.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Long> {
    Share findByIsinCodeEquals(String isinCode);

}
