package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "BAOHIEM")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class BaoHiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSoBaoHiem", nullable = false)
    private int idSoBaoHiem;
    @Column(name = "NgayHienHanh")
    private Date ngayHienHanh;
    @Column(name = "TrangThai", length = 50)
    private String trangThai;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDLoai")
    private LoaiBH loaiBH;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDNV")
    private NhanVien nhanVien;
    @Override
    public String toString() {
        return "BaoHiem{" +
                "idSoBaoHiem=" + idSoBaoHiem +
                ", ngayHienHanh=" + ngayHienHanh +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}