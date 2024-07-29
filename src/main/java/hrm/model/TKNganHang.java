package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TKNGANHANG")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TKNganHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "SoTaiKhoan", length = 20)
    private String soTaiKhoan;

    @Column(name = "TenNganHang", length = 50)
    private String tenNganHang;

    @Column(name = "SoThe", length = 20)
    private String soThe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDNV")
    private NhanVien nhanVien;

    @Override
    public String toString() {
        return "TKNgNganHang{" +
                "id=" + id +
                ", soTaiKhoan='" + soTaiKhoan + '\'' +
                ", tenNganHang='" + tenNganHang + '\'' +
                ", soThe='" + soThe + '\'' +
                '}';
    }
}