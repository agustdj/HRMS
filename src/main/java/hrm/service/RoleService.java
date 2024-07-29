package hrm.service;

import hrm.model.Role;
import hrm.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final IRoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
