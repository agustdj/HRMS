package hrm.repository;

import hrm.model.KhoaDaoTao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KhoaDaoTaoRepository extends JpaRepository<KhoaDaoTao, Integer>, PagingAndSortingRepository<KhoaDaoTao, Integer> {
    void deleteById(int id);
    Page<KhoaDaoTao> findByTenKhoaContaining(String keyword, Pageable pageable);
    Page<KhoaDaoTao> findByTenKhoaContainingIgnoreCaseOrMoTaContainingIgnoreCase(String tenKhoa, String moTa, Pageable pageable);
}
