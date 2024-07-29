package hrm.service;

import hrm.model.DaoTaoNhanVien;
import hrm.model.KhoaDaoTao;
import hrm.model.NhanVien;
import hrm.repository.DaoTaoNhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DaoTaoNhanVienService {
    @Autowired
    private DaoTaoNhanVienRepository daoTaoNhanVienRepository;

    public void followKhoaDaoTao(NhanVien nhanVien, KhoaDaoTao khoaDaoTao) {
        if (!isFollowing(nhanVien.getId(), khoaDaoTao.getId())) {
            DaoTaoNhanVien daoTaoNhanVien = new DaoTaoNhanVien();
            daoTaoNhanVien.setNhanVien(nhanVien);
            daoTaoNhanVien.setKhoaDaoTao(khoaDaoTao);
            daoTaoNhanVien.setNgayHoanThanh(khoaDaoTao.getNgayKetThuc());
            daoTaoNhanVienRepository.save(daoTaoNhanVien);
        }
    }

    @Transactional
    public void unfollowKhoaDaoTao(String nhanVienId, int khoaDaoTaoId) {
        daoTaoNhanVienRepository.deleteByNhanVienIdAndKhoaDaoTaoId(nhanVienId, khoaDaoTaoId);
    }

    public boolean isFollowing(String nhanVienId, int khoaDaoTaoId) {
        return daoTaoNhanVienRepository.existsByNhanVienIdAndKhoaDaoTaoId(nhanVienId, khoaDaoTaoId);
    }

    public Set<Integer> getFollowedKhoaDaoTaoIds(String nhanVienId) {
        return daoTaoNhanVienRepository.findByNhanVienId(nhanVienId).stream()
                .map(daoTaoNhanVien -> daoTaoNhanVien.getKhoaDaoTao().getId())
                .collect(Collectors.toSet());
    }
}