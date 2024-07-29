package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "YEUCAUNGHIPHEP")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class YeuCauNghiPhep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "LoaiNghiPhep", length = 100)
    private String loaiNghiPhep;

    @Column(name = "LyDo", length = 400)
    private String lyDo;

    @Column(name = "NgayBatDau")
    private Date ngayBatDau;

    @Column(name = "NgayKetThuc")
    private Date ngayKetThuc;

    @Column(name = "TrangThai")
    private boolean trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDNV")
    private NhanVien nhanVien;

    @Override
    public String toString() {
        return "YeuCauNghiPhep{" +
                "id=" + id +
                ", loaiNghiPhep='" + loaiNghiPhep + '\'' +
                ", lyDo='" + lyDo + '\'' +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", trangThai=" + trangThai +
                '}';
    }
}