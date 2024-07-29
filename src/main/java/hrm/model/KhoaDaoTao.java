package hrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "KHOADAOTAO")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class KhoaDaoTao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "TenKhoa", length = 50)
    private String tenKhoa;

    @Column(name = "MoTa", length = 1000)
    private String moTa;

    @Column(name = "NgayBatDau")
    private Date ngayBatDau;

    @Column(name = "NgayKetThuc")
    private Date ngayKetThuc;

    @Column(name = "NguoiHuongDan", length = 50)
    private String nguoiHuongDan;

    @Column(name = "DiaChi", length = 300)
    private String diaChi;

    @OneToMany(mappedBy = "khoaDaoTao", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<DaoTaoNhanVien> daoTaoNhanViens;

    @Override
    public String toString() {
        return "KhoaDaoTao{" +
                "id=" + id +
                ", tenKhoa='" + tenKhoa + '\'' +
                ", moTa='" + moTa + '\'' +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", nguoiHuongDan='" + nguoiHuongDan + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
