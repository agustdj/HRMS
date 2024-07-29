package hrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "PHONGBAN")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PhongBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "TenPhongBan", length = 50)
    private String tenPhongBan;

    @Column(name = "DiaChi", length = 300)
    private String diaChi;

    @Column(name = "SDT", length = 10)
    private String sdt;

    @Column(name = "MoTa", length = 100)
    private String moTa;

    @Column(name = "IDNVQuanLy", length = 10)
    private String idnvQuanLy;

    @OneToMany(mappedBy = "phongBan", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ViTriCV> viTriCVs;

    @OneToMany(mappedBy = "phongBan", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<NhanVien> nhanViens;

    @Override
    public String toString() {
        return "PhongBan{" +
                "id=" + id +
                ", tenPhongBan='" + tenPhongBan + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", sdt='" + sdt + '\'' +
                ", moTa='" + moTa + '\'' +
                ", idnvQuanLy='" + idnvQuanLy + '\'' +
                '}';
    }
}