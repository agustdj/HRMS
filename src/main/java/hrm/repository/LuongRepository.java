package hrm.repository;

import hrm.model.Luong;
import hrm.model.LuongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuongRepository extends JpaRepository<Luong, LuongId> {

    @Query("SELECT l FROM Luong l WHERE YEAR(l.thangNam) = :year AND MONTH(l.thangNam) = :month")
    List<Luong> findByMonth(int year, int month);
}
