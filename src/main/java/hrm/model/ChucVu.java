package hrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "CHUCVU")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "TenChucVu", length = 50)
    private String tenChucVu;

    @Column(name = "LuongCoBan", precision = 18, scale = 2)
    private BigDecimal luongCoBan;

    @Column(name = "PhuCapChucVu", precision = 18, scale = 2)
    private BigDecimal phuCapChucVu;

    @Column(name = "HeSoLuong", precision = 10, scale = 2)
    private BigDecimal heSoLuong;

    @OneToMany(mappedBy = "chucVu", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<NhanVien> nhanViens;

    @Override
    public String toString() {
        return "ChucVu{" +
                "id=" + id +
                ", tenChucVu='" + tenChucVu + '\'' +
                ", phuCapChucVu=" + phuCapChucVu +
                ", heSoLuong=" + heSoLuong +
                '}';
    }
}