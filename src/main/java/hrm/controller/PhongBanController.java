package hrm.controller;

import hrm.model.PhongBan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import hrm.service.PhongBanService;

@Controller
@RequestMapping("/phongban")
@PreAuthorize("hasAnyAuthority('USER')")
public class PhongBanController {

    private final PhongBanService phongBanService;
    private static final int PAGE_SIZE = 5; // Số lượng bản ghi trên mỗi trang

    @Autowired
    public PhongBanController(PhongBanService phongBanService) {
        this.phongBanService = phongBanService;
    }

    // List all departments with pagination
    @GetMapping("")
    public String listPhongBan(Model model,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "") String keyword) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        Page<PhongBan> phongBanPage;

        if (keyword.isEmpty()) {
            phongBanPage = phongBanService.findAll(pageable);
        } else {
            phongBanPage = phongBanService.searchPhongBan(keyword, pageable);
        }

        model.addAttribute("phongBans", phongBanPage.getContent());
        model.addAttribute("totalPages", phongBanPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        model.addAttribute("keyword", keyword);

        return "phongban/phongban-list";
    }

    // Add new department form
    @GetMapping("/add")
    public String addPhongBanForm(Model model) {
        model.addAttribute("phongBan", new PhongBan());
        return "phongban/phongban-add";
    }

    // Save new department
    @PostMapping("/add")
    public String addPhongBan(@ModelAttribute("phongBan") PhongBan phongBan) {
        phongBanService.save(phongBan);
        return "redirect:/phongban";
    }

    // Edit department form
    @GetMapping("/edit/{id}")
    public String editPhongBanForm(@PathVariable("id") int id, Model model) {
        PhongBan phongBan = phongBanService.getPhongBanById(id);
        model.addAttribute("phongBan", phongBan);
        return "phongban/phongban-edit";
    }

    // Update department
    @PostMapping("/edit/{id}")
    public String editPhongBan(@PathVariable("id") int id, @ModelAttribute("phongBan") PhongBan phongBan) {
        phongBan.setId(id);
        phongBanService.save(phongBan);
        return "redirect:/phongban";
    }

    // Delete department - không cho xóa phòng ban
//    @GetMapping("/delete/{id}")
//    public String deletePhongBan(@PathVariable("id") int id) {
//        phongBanService.deletePhongBanById(id);
//        return "redirect:/phongban";
//    }
}
