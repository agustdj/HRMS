package hrm.repository;

import hrm.model.ViTriCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViTriCVRepository extends JpaRepository<ViTriCV, Integer> {
}
