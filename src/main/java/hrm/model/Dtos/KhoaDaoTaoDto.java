package hrm.model.Dtos;

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
public class KhoaDaoTaoDto {
    private int id;
    private String tenKhoa;
    private String moTa;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayBatDau;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayKetThuc;

    private String nguoiHuongDan;
    private String diaChi;
}
