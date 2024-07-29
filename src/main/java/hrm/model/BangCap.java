package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "BANGCAP")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class BangCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "TenBang", length = 50)
    private String tenBang;

    @Column(name = "NoiCap", length = 300)
    private String noiCap;

    @Column(name = "ChuyenNganh", length = 50)
    private String chuyenNganh;

    @Column(name = "NgayCap")
    private Date ngayCap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDNV")
    private NhanVien nhanVien;

    @Override
    public String toString() {
        return "BangCap{" +
                "id=" + id +
                ", tenBang='" + tenBang + '\'' +
                ", noiCap='" + noiCap + '\'' +
                ", chuyenNganh='" + chuyenNganh + '\'' +
                ", ngayCap=" + ngayCap +
                '}';
    }
}

