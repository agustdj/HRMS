package hrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "LOAIBH")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LoaiBH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "TenLoai", length = 50)
    private String tenLoai;
    @OneToMany(mappedBy = "loaiBH", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<BaoHiem> baoHiems;
    @Override
    public String toString() {
        return "LoaiBH{" +
                "id=" + id +
                ", tenLoai='" + tenLoai + '\'' +
                '}';
    }
}