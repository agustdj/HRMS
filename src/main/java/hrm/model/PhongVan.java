package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "PHONGVAN")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PhongVan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NgayPhongVan")
    private Date ngayPhongVan;

    @Column(name = "NguoiPhongVan", length = 10)
    private String nguoiPhongVan;

    @Column(name = "GhiChu", length = 400)
    private String ghiChu;

    @Column(name = "KetQua")
    private boolean ketQua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDUngVien")
    private UngVien ungVien;

    @Override
    public String toString() {
        return "PhongVan{" +
                "id=" + id +
                ", ngayPhongVan=" + ngayPhongVan +
                ", nguoiPhongVan='" + nguoiPhongVan + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                ", ketQua=" + ketQua +
                '}';
    }
}