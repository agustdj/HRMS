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
@Table(name = "NHANVIEN")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class NhanVien {
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    private String id;

    @Column(name = "HoDem", length = 30)
    private String hoDem;

    @Column(name = "Ten", length = 20)
    private String ten;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "GioiTinh")
    private boolean gioiTinh;

    @Column(name = "CCCD", length = 20)
    private String cccd;

    @Column(name = "DiaChi", length = 300)
    private String diaChi;

    @Column(name = "SDT", length = 15)
    private String sdt;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "QueQuan", length = 300)
    private String queQuan;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "NgayTuyenDung")
    private Date ngayTuyenDung;

    @Column(name = "AnhProfile", length = 255)
    private String anhProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDViTri")
    private ViTriCV viTriCV;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDChucVu")
    private ChucVu chucVu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPhongBan")
    private PhongBan phongBan;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TaiKhoan> taiKhoans;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TKNganHang> tkNganHangs;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BangCap> bangCaps;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HopDong> hopDongs;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BaoHiem> baoHiems;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ChamCong> chamCongs;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DaoTaoNhanVien> daoTaoNhanViens;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Luong> luongs;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<YeuCauNghiPhep> yeuCauNghiPheps;

    @Override
    public String toString() {
        return "NhanVien{" +
                "id='" + id + '\'' +
                ", hoDem='" + hoDem + '\'' +
                ", ten='" + ten + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + gioiTinh +
                ", cccd='" + cccd + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", queQuan='" + queQuan + '\'' +
                ", ngayTuyenDung=" + ngayTuyenDung +
                ", anhProfile='" + anhProfile + '\'' +
                '}';
    }
}