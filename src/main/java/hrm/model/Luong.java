package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "LUONG")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Luong {
    @EmbeddedId
    private LuongId id;
    @Column(name = "ThanhTien", precision = 18, scale = 2)
    private BigDecimal thanhTien;
    @Column(name = "ThangNam")
    private Date thangNam;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idNV")
    @JoinColumn(name = "IDNV")
    private NhanVien nhanVien;
    /*@Override
    public String toString() {
        return "Luong{" +
                "id=" + id +
                ", thanhTien=" + thanhTien +
                '}';
    }*/
}
