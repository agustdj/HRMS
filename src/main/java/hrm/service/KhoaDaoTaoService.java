package hrm.service;

import hrm.model.Dtos.KhoaDaoTaoDto;
import hrm.model.KhoaDaoTao;
import hrm.repository.KhoaDaoTaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class KhoaDaoTaoService {

    @Autowired
    private KhoaDaoTaoRepository khoaDaoTaoRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // Lấy danh sách tất cả khóa đào tạo
//    public List<KhoaDaoTao> getAllKhoaDaoTaos() {
//        return khoaDaoTaoRepository.findAll();
//    }
    public Page<KhoaDaoTao> getAllKhoaDaoTaos(Pageable pageable) {
        return khoaDaoTaoRepository.findAll(pageable);
    }

    // Lấy thông tin một khóa đào tạo theo ID
    public Optional<KhoaDaoTao> getKhoaDaoTaoById(int id) {
        return khoaDaoTaoRepository.findById(id);
    }

    // Thêm mới hoặc cập nhật thông tin khóa đào tạo
    public KhoaDaoTao saveKhoaDaoTao(KhoaDaoTao khoaDaoTao) {
        return khoaDaoTaoRepository.save(khoaDaoTao);
    }

    public KhoaDaoTao updateKhoaDaoTao(KhoaDaoTao khoaDaoTao) {
        KhoaDaoTao existingKhoaDaoTao = khoaDaoTaoRepository.findById(khoaDaoTao.getId()).orElse(null);
        if (existingKhoaDaoTao != null) {
            existingKhoaDaoTao.setTenKhoa(khoaDaoTao.getTenKhoa());
            existingKhoaDaoTao.setMoTa(khoaDaoTao.getMoTa());
            existingKhoaDaoTao.setDiaChi(khoaDaoTao.getDiaChi());
            existingKhoaDaoTao.setNgayBatDau(khoaDaoTao.getNgayBatDau());
            existingKhoaDaoTao.setNgayKetThuc(khoaDaoTao.getNgayKetThuc());
            existingKhoaDaoTao.setNguoiHuongDan(khoaDaoTao.getNguoiHuongDan());
            return khoaDaoTaoRepository.save(existingKhoaDaoTao);
        }
        return null;
    }

    // Xóa một khóa đào tạo theo ID
    public void deleteKhoaDaoTaoById(int id) {
        khoaDaoTaoRepository.deleteById(id);
    }

    // Kiểm tra nếu khóa đào tạo có tồn tại theo ID
    public boolean existsById(int id) {
        return khoaDaoTaoRepository.existsById(id);
    }

    // Tìm kiếm khóa đào tạo theo từ khóa
    public Page<KhoaDaoTao> searchKhoaDaoTaos(String keyword, Pageable pageable) {
        return khoaDaoTaoRepository.findByTenKhoaContainingIgnoreCaseOrMoTaContainingIgnoreCase(keyword, keyword, pageable);
    }

    public KhoaDaoTao convertDtoToEntity(KhoaDaoTaoDto dto) {
        try {
            KhoaDaoTao khoaDaoTao = new KhoaDaoTao();
            khoaDaoTao.setId(dto.getId());
            khoaDaoTao.setTenKhoa(dto.getTenKhoa());
            khoaDaoTao.setMoTa(dto.getMoTa());
            khoaDaoTao.setNgayBatDau(dto.getNgayBatDau());
            khoaDaoTao.setNgayKetThuc(dto.getNgayKetThuc());
            khoaDaoTao.setNguoiHuongDan(dto.getNguoiHuongDan());
            khoaDaoTao.setDiaChi(dto.getDiaChi());
            return khoaDaoTao;
        } catch (Exception e) {
            System.err.println("Error converting KhoaDaoTaoDto to KhoaDaoTao: " + e.getMessage());
            e.printStackTrace();
            return null; // Hoặc bạn có thể ném lại ngoại lệ hoặc xử lý nó theo cách khác
        }
    }

    public KhoaDaoTao saveKhoaDaoTao(KhoaDaoTaoDto dto) {
        KhoaDaoTao khoaDaoTao = convertDtoToEntity(dto);
        return khoaDaoTaoRepository.save(khoaDaoTao);
    }
}