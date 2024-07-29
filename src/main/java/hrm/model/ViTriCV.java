package hrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "VITRICV")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ViTriCV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "TenVitri", length = 50)
    private String tenVitri;

    @Column(name = "MoTaCV", length = 100)
    private String moTaCV;

    @Column(name = "MucLuongCoBan", precision = 18, scale = 2)
    private BigDecimal mucLuongCoBan;

    @Column(name = "NgayDang")
    private Date ngayDang;

    @Column(name = "HetHan")
    private Date hetHan;

    @Column(name = "TrangThai")
    private boolean trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPhongBan")
    private PhongBan phongBan;

    @OneToMany(mappedBy = "viTriCV", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<TuyenDung> tuyenDungs;

    @OneToMany(mappedBy = "viTriCV", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<NhanVien> nhanViens;

    @Override
    public String toString() {
        return "ViTriCV{" +
                "id=" + id +
                ", tenVitri='" + tenVitri + '\'' +
                ", moTaCV='" + moTaCV + '\'' +
                ", mucLuongCoBan=" + mucLuongCoBan +
                ", ngayDang=" + ngayDang +
                ", hetHan=" + hetHan +
                ", trangThai=" + trangThai +
                '}';
    }
}