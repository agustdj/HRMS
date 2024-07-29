package hrm.controller;

import hrm.model.Dtos.NhanVienDto;
import hrm.model.NhanVien;
import hrm.service.ChucVuService;
import hrm.service.NhanVienService;
import hrm.service.PhongBanService;
import hrm.service.ViTriCVService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/nhanvien")
@PreAuthorize("hasAnyAuthority('USER')")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private ViTriCVService viTriCVService;

    @Autowired
    private PhongBanService phongBanService;

    private static final int PAGE_SIZE = 5; // Số lượng bản ghi trên mỗi trang

    @GetMapping("")
    public String listNhanVien(Model model,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "") String keyword) {
        int pageSize = 5; // Số lượng bản ghi trên mỗi trang
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        Page<NhanVien> nhanVienPage;

        if (keyword.isEmpty()) {
            nhanVienPage = nhanVienService.getAllNhanViens(pageable);
        } else {
            nhanVienPage = nhanVienService.searchNhanViens(keyword, pageable);
        }

        model.addAttribute("nhanViens", nhanVienPage.getContent());
        model.addAttribute("totalPages", nhanVienPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        model.addAttribute("keyword", keyword);

        return "nhanvien/nhanvien-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        NhanVien nhanVien = new NhanVien();
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("listViTriCV", viTriCVService.findAll());
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("listPhongBan", phongBanService.findAll());
        return "nhanvien/nhanvien-add";
    }

    @PostMapping("/add")
    public String addNhanVien(@ModelAttribute("nhanVien") @Valid NhanVienDto nhanVienDTO,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) throws IOException {

        if (result.hasErrors()) {
            // handle validation errors
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.nhanVienDTO", result);
            redirectAttributes.addFlashAttribute("nhanVienDTO", nhanVienDTO);
            return "redirect:/nhanvien/add";
        }

        // convert NhanVienDTO to NhanVien using service method
        NhanVien nhanVien = nhanVienService.convertToNhanVien(nhanVienDTO, nhanVienDTO.getAnhProfile());

        // save NhanVien to database
        nhanVienService.save(nhanVien);

        // redirect to employee list page
        return "redirect:/nhanvien";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Optional<NhanVien> optionalNhanVien = nhanVienService.getNhanVienById(id);
        if (optionalNhanVien.isPresent()) {
            NhanVien nhanVien = optionalNhanVien.get();
            model.addAttribute("nhanVien", nhanVien);
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("listViTriCV", viTriCVService.findAll());
            model.addAttribute("listPhongBan", phongBanService.findAll());
            return "nhanvien/nhanvien-edit";
        } else {
            return "redirect:/nhanvien/list";
        }
    }

    @PostMapping("/edit/{id}")
    public String editNhanVien(@PathVariable("id") String id, @ModelAttribute("nhanVien") NhanVienDto nhanVienDTO) {
        try {
            nhanVienService.updateNhanVien(id, nhanVienDTO, nhanVienDTO.getAnhProfile());
            System.out.print("Cập nhật nhân viên thành công");
        } catch (Exception e) {
            System.out.print("Cập nhật nhân viên thất bại");
        }
        return "redirect:/nhanvien";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable("id") String id) {
        try {
            nhanVienService.deleteNhanVienById(id);
            System.out.print("Xóa nhân viên thành công");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Xóa nhân viên thất bại");
        }
        return "redirect:/nhanvien";
    }
}
