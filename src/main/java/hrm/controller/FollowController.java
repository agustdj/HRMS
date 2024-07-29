package hrm.controller;

import hrm.model.KhoaDaoTao;
import hrm.model.NhanVien;
import hrm.model.User;
import hrm.service.NhanVienService;
import hrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import hrm.service.DaoTaoNhanVienService;
import hrm.service.KhoaDaoTaoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FollowController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private KhoaDaoTaoService khoaDaoTaoService;

    @Autowired
    private DaoTaoNhanVienService followService;

    @Autowired
    private UserService userService;

    @PostMapping("/follow")
    public String followKhoaDaoTao(@RequestParam("khoaDaoTaoId") int khoaDaoTaoId,
                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                   Model model) {
        User currentUser = userService.getCurrentUser(); // Lấy người dùng hiện tại

        if (currentUser == null) {
            model.addAttribute("errors", List.of("Người dùng chưa đăng nhập"));
            return "error/errorPage";
        }

        NhanVien nhanVien = currentUser.getNhanVien(); // Lấy NhanVien từ User

        if (nhanVien == null) {
            model.addAttribute("errors", List.of("Không tìm thấy thông tin nhân viên"));
            return "error/errorPage";
        }

        String nhanVienId = nhanVien.getId();
        System.out.println("NhanVienId before calling service: " + nhanVienId);

        List<String> errors = new ArrayList<>();
        Optional<KhoaDaoTao> khoaDaoTaoOptional = khoaDaoTaoService.getKhoaDaoTaoById(khoaDaoTaoId);

        if (khoaDaoTaoOptional.isEmpty()) {
            errors.add("Không tìm thấy khóa đào tạo với ID: " + khoaDaoTaoId);
        }

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "error/errorPage";
        }
        KhoaDaoTao khoaDaoTao = khoaDaoTaoOptional.get();
        followService.followKhoaDaoTao(nhanVien, khoaDaoTao);
        return "redirect:/khoaDaoTaos?page=" + page; // Chuyển hướng kèm theo tham số page
    }

    @PostMapping("/unfollow")
    public String unfollowKhoaDaoTao(@RequestParam("nhanVienId") String nhanVienId,
                                     @RequestParam("khoaDaoTaoId") int khoaDaoTaoId,
                                     @RequestParam(value = "page", defaultValue = "1") int page) {
        followService.unfollowKhoaDaoTao(nhanVienId, khoaDaoTaoId);
        return "redirect:/khoaDaoTaos?page=" + page; // Chuyển hướng kèm theo tham số page
    }
}
