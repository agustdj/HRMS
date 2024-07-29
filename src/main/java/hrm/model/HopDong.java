package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "HOPDONG")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class HopDong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NgayBatDau")
    private Date ngayBatDau;

    @Column(name = "NgayKetThuc")
    private Date ngayKetThuc;

    @Column(name = "NoiDung", length = 1000)
    private String noiDung;
    @Column(name = "ThoiHan")
    private String thoiHan;
    @Column(name = "TrangThai")
    private boolean trangThai;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDNV")
    private NhanVien nhanVien;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDLoaiHopDong")
    private LoaiHD loaiHD;
    @Override
    public String toString() {
        return "HopDong{" +
                "id=" + id +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", noiDung='" + noiDung + '\'' +
                ", thoiHan=" + thoiHan +
                ", trangThai=" + trangThai +
                '}';
    }
}