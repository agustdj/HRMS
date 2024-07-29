package hrm.model.Dtos;

import hrm.model.NhanVien;
import hrm.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String phone;
    private String password;
    private NhanVien nhanVien;
    private Set<Role> roles = new HashSet<>();
}

