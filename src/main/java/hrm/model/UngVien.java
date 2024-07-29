package hrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "UNGVIEN")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UngVien {
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    private String id;
    @Column(name = "HoDem", length = 30)
    private String hoDem;
    @Column(name = "Ten", length = 20)
    private String ten;
    @Column(name = "NgaySinh")
    private Date ngaySinh;
    @Column(name = "GioiTinh")
    private boolean gioiTinh;
    @Column(name = "CCCD", length = 12)
    private String cccd;
    @Column(name = "DiaChi", length = 300)
    private String diaChi;
    @Column(name = "SDT", length = 100)
    private String sdt;
    @Column(name = "Email", length = 300)
    private String email;
    @Column(name = "QueQuan", length = 300)
    private String queQuan;
    @Column(name = "NgayUngTuyen")
    private Date ngayUngTuyen;
    @Column(name = "LinkHoSo", length = 300)
    private String linkHoSo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDThongTinTuyenDung")
    private TuyenDung tuyenDung;
    @OneToMany(mappedBy = "ungVien", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<PhongVan> phongVans;
    @Override
    public String toString() {
        return "UngVien{" +
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
                ", ngayUngTuyen=" + ngayUngTuyen +
                ", linkHoSo='" + linkHoSo + '\'' +
                '}';
    }
}
