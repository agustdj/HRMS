package hrm.repository;

import hrm.model.DaoTaoNhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoTaoNhanVienRepository extends JpaRepository<DaoTaoNhanVien, Integer> {
    List<DaoTaoNhanVien> findByNhanVienId(String nhanVienId);
    void deleteByNhanVienIdAndKhoaDaoTaoId(String nhanVienId, int khoaDaoTaoId);
    boolean existsByNhanVienIdAndKhoaDaoTaoId(String nhanVienId, int khoaDaoTaoId);
}
