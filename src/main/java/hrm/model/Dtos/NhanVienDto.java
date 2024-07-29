package hrm.model.Dtos;

import hrm.model.ChucVu;
import hrm.model.PhongBan;
import hrm.model.ViTriCV;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienDto {
    private String id;
    private String hoDem;
    private String ten;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;
    private boolean gioiTinh;
    private String cccd;
    private String diaChi;
    private String sdt;
    private String email;
    private String queQuan;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTuyenDung;
    private MultipartFile anhProfile;
    private ChucVu chucVu;
    private ViTriCV viTriCV;
    private PhongBan phongBan;
}
