package hrm.repository;

import hrm.model.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String>, PagingAndSortingRepository<NhanVien, String> {
    Optional<NhanVien> findById(String id);
    Page<NhanVien> searchNhanVienByIdIgnoreCaseContaining(String keyword, Pageable pageable);
}
