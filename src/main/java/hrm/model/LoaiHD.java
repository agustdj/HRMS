package hrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "LOAIHD")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LoaiHD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "TenLoai", length = 50)
    private String tenLoai;

    @OneToMany(mappedBy = "loaiHD", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<HopDong> hopDongs;

    @Override
    public String toString() {
        return "LoaiHD{" +
                "id=" + id +
                ", tenLoai='" + tenLoai + '\'' +
                '}';
    }
}