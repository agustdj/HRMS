package hrm.service;

import hrm.model.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import hrm.model.Dtos.NhanVienDto;
import hrm.repository.NhanVienRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private ViTriCVService viTriCVService;

    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private PhongBanService phongBanService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<NhanVien> getNhanVienById(String id) {
        return nhanVienRepository.findById(id);
    }

    public Page<NhanVien> getAllNhanViens(Pageable pageable) {
        return nhanVienRepository.findAll(pageable);
    }

    public void save(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    public void deleteNhanVienById(String id) {
        nhanVienRepository.deleteById(id);
    }

    public void updateNhanVien(String id, NhanVienDto nhanVienDTO, MultipartFile anhProfile) throws IOException {
        Optional<NhanVien> optionalNhanVien = nhanVienRepository.findById(id);

        if (optionalNhanVien.isPresent()) {
            NhanVien existingNhanVien = optionalNhanVien.get();

            // Cập nhật các trường dữ liệu
            existingNhanVien.setHoDem(nhanVienDTO.getHoDem());
            existingNhanVien.setTen(nhanVienDTO.getTen());
            existingNhanVien.setNgaySinh(nhanVienDTO.getNgaySinh());
            existingNhanVien.setGioiTinh(nhanVienDTO.isGioiTinh());
            existingNhanVien.setCccd(nhanVienDTO.getCccd());
            existingNhanVien.setDiaChi(nhanVienDTO.getDiaChi());
            existingNhanVien.setSdt(nhanVienDTO.getSdt());
            existingNhanVien.setEmail(nhanVienDTO.getEmail());
            existingNhanVien.setQueQuan(nhanVienDTO.getQueQuan());
            existingNhanVien.setNgayTuyenDung(nhanVienDTO.getNgayTuyenDung());
            existingNhanVien.setViTriCV(nhanVienDTO.getViTriCV());
            existingNhanVien.setChucVu(nhanVienDTO.getChucVu());
            existingNhanVien.setPhongBan(nhanVienDTO.getPhongBan());

            // Cập nhật ảnh nếu có file mới được tải lên
            if (!anhProfile.isEmpty()) {
                String imagePath = saveImage(anhProfile);
                existingNhanVien.setAnhProfile(imagePath);
            }

            // Lưu NhanVien đã cập nhật vào database
            nhanVienRepository.save(existingNhanVien);
        } else {
            throw new IllegalArgumentException("NhanVien với ID " + id + " không tồn tại.");
        }
    }

    public NhanVien convertToNhanVien(NhanVienDto nhanVienDTO, MultipartFile imageFile) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(nhanVienDTO.getId());
        nhanVien.setHoDem(nhanVienDTO.getHoDem());
        nhanVien.setTen(nhanVienDTO.getTen());
        nhanVien.setNgaySinh(nhanVienDTO.getNgaySinh());
        nhanVien.setGioiTinh(nhanVienDTO.isGioiTinh());
        nhanVien.setCccd(nhanVienDTO.getCccd());
        nhanVien.setDiaChi(nhanVienDTO.getDiaChi());
        nhanVien.setSdt(nhanVienDTO.getSdt());
        nhanVien.setEmail(nhanVienDTO.getEmail());
        nhanVien.setQueQuan(nhanVienDTO.getQueQuan());
        nhanVien.setNgayTuyenDung(nhanVienDTO.getNgayTuyenDung());

        // Chỉ set anhProfile nếu có file tải lên
        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile);
            nhanVien.setAnhProfile(imagePath);
        }

        nhanVien.setViTriCV(nhanVienDTO.getViTriCV());
        nhanVien.setChucVu(nhanVienDTO.getChucVu());
        nhanVien.setPhongBan(nhanVienDTO.getPhongBan());

        return nhanVien;
    }

    private String saveImage(MultipartFile imageFile) {
        try {
            Path dirImages = Paths.get("static/images");
            if (!Files.exists(dirImages)) {
                Files.createDirectories(dirImages);
            }
            String newFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path pathFileUpload = dirImages.resolve(newFileName);
            Files.copy(imageFile.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null;
        }
    }

    public Page<NhanVien> searchNhanViens(String keyword, Pageable pageable) {
        return nhanVienRepository.searchNhanVienByIdIgnoreCaseContaining(keyword, pageable);
    }

    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }
}
