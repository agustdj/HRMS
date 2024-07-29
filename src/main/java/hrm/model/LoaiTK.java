package hrm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "LOAITK")
public class LoaiTK implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "TenLoai", length = 50, nullable = false)
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String tenLoai;

    @ManyToMany(mappedBy = "loaiTKs", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<TaiKhoan> taiKhoans = new HashSet<>();

    @Override
    public String getAuthority() {
        return tenLoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoaiTK loaiTK = (LoaiTK) o;
        return getId() != null && Objects.equals(getId(), loaiTK.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}