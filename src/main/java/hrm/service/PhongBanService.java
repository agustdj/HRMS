package hrm.service;

import hrm.model.PhongBan;
import hrm.repository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongBanService {
    @Autowired
    private PhongBanRepository phongBanRepository;

    public Page<PhongBan> findAll(Pageable pageable) {
        return phongBanRepository.findAll(pageable);
    }

    public List<PhongBan> findAll() {
        return phongBanRepository.findAll();
    }

    public PhongBan findByTenPhongBan(String tenPhongBan) {
        return phongBanRepository.findByTenPhongBan(tenPhongBan);
    }

    public PhongBan save(PhongBan phongBan) {
        return phongBanRepository.save(phongBan);
    }

    public void delete(int idPhongBan) {
        phongBanRepository.deleteById(idPhongBan);
    }

    public Page<PhongBan> searchPhongBan(String keyword, Pageable pageable) {
        return phongBanRepository.findPhongBanByTenPhongBanIgnoreCaseContaining(keyword, pageable);
    }

    public PhongBan getPhongBanById(int id) {
        return phongBanRepository.findById(id).orElse(null);
    }

    public void deletePhongBanById(int id) {
        phongBanRepository.deleteById(id);
    }
}
