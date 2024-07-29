package hrm.repository;

import hrm.model.PhongBan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Integer>, PagingAndSortingRepository<PhongBan, Integer> {
    Page<PhongBan> findPhongBanByTenPhongBanIgnoreCaseContaining(String keyword, Pageable pageable);
    PhongBan findByTenPhongBan(String tenPhongBan);
}
