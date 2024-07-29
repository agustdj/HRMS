package hrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TUYENDUNG")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TuyenDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NgayDeNghi")
    private Date ngayDeNghi;

    @Column(name = "ThongTinChiTiet", length = 1000)
    private String thongTinChiTiet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDViTri")
    private ViTriCV viTriCV;

    @OneToMany(mappedBy = "tuyenDung", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UngVien> ungViens;

    @Override
    public String toString() {
        return "TuyenDung{" +
                "id=" + id +
                ", ngayDeNghi=" + ", ngayDeNghi=" + ngayDeNghi +
                ", thongTinChiTiet='" + thongTinChiTiet + '\'' +
                '}';
    }
}
