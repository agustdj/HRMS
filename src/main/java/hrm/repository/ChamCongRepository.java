package hrm.repository;

import hrm.model.ChamCong;
import hrm.model.ChamCongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChamCongRepository extends JpaRepository<ChamCong, ChamCongId> {
    @Query("SELECT c FROM ChamCong c WHERE YEAR(c.thangNam) = :year AND MONTH(c.thangNam) = :month")
    List<ChamCong> findByMonth(@Param("year") int year, @Param("month") int month);
}
