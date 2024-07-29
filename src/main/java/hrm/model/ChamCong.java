package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "CHAMCONG")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChamCong {
    @EmbeddedId
    private ChamCongId id;
    @Column(name = "ThangNam")
    private Date thangNam;
    @Column(name = "SoNgayLam")
    private int soNgayLam;
    @Column(name = "SoNgayNghi")
    private int soNgayNghi;
    @Column(name = "SoLanTre")
    private int soLanTre;


    @MapsId("idNV")
    @JoinColumn(name = "IDNV")
    @ManyToOne(fetch = FetchType.LAZY)
    private NhanVien nhanVien;
   /* @Override
    public String toString() {
        return "ChamCong{" +
                "id=" + id +
                ", thangNam=" + thangNam +
                ", soNgayLam=" + soNgayLam +
                ", soNgayNghi=" + soNgayNghi +
                ", soLanTre=" + soLanTre +
                '}';
    }*/
}
