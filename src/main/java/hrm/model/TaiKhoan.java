package hrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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
@Table(name = "TAIKHOAN")
public class TaiKhoan implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TenDangNhap", length = 50, unique = true)
    @NotBlank(message = "Username is required")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String tenDangNhap;

    @Column(name = "MatKhau", length = 250)
    @NotBlank(message = "Password is required")
    private String matKhau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDNV", referencedColumnName = "id")
    private NhanVien nhanVien;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TAIKHOAN_LOAITK",
            joinColumns = @JoinColumn(name = "TAIKHOAN_ID"),
            inverseJoinColumns = @JoinColumn(name = "LOAITK_ID"))
    private Set<LoaiTK> loaiTKs = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<LoaiTK> userRoles = this.getLoaiTKs();
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTenLoai()))
                .toList();
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public String getUsername() {
        return tenDangNhap;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TaiKhoan taiKhoan = (TaiKhoan) o;
        return getId() != null && Objects.equals(getId(), taiKhoan.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}