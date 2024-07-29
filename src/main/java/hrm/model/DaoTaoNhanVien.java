package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "DAOTAONHANVIEN")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class DaoTaoNhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "NgayHoanThanh")
    private Date ngayHoanThanh;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDNV")
    private NhanVien nhanVien;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDKhoaDT")
    private KhoaDaoTao khoaDaoTao;
    @Override
    public String toString() {
        return "DaoTaoNhanVien{" +
                "id=" + id +
                ", ngayHoanThanh=" + ngayHoanThanh +
                '}';
    }
}