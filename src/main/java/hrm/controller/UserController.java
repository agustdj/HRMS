package hrm.controller;

import hrm.model.NhanVien;
import hrm.model.User;
import hrm.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import hrm.model.Dtos.UserDto;
import hrm.service.RoleService;

@Controller // Đánh dấu lớp này là một Controller trong Spring MVC.
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }
    
    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto) {
        userService.save(userDto);
        userService.setDefaultRole(userDto.getUsername());
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(@NotNull Model model) {
        User currentUser = userService.getCurrentUser();
        NhanVien nhanVien = currentUser.getNhanVien();
        model.addAttribute("nhanVien", nhanVien);
        return "users/profile";
    }

    @GetMapping("/devFunc")
    public String devFunc() {
        return "developFunc";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Hủy phiên HttpSession
        SecurityContextHolder.clearContext(); // Xóa thông tin xác thực
        return "redirect:/login";
    }
}
