/*
package sushine_group.hrm_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sushine_group.hrm_management_system.model.Luong;
import sushine_group.hrm_management_system.service.LuongService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
@Controller
@RequestMapping("/luong")
public class LuongController {

    private final LuongService luongService;

    @Autowired
    public LuongController(LuongService luongService) {
        this.luongService = luongService;
    }

    @GetMapping("/danhsach")
    public String showLuongByMonth(@RequestParam(value = "selectedMonth", required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth selectedMonth,
                                   Model model) {
        List<Luong> danhSachLuong;
        if (selectedMonth != null) {
            danhSachLuong = luongService.getLuongByMonth(selectedMonth);
        } else {
            // If no month is selected, retrieve all salary records
            danhSachLuong = luongService.getAllLuong();
        }
        model.addAttribute("danhSachLuong", danhSachLuong);
        return "hr/luong"; // Thymeleaf template name
    }
}*/
package hrm.controller;

import hrm.model.Luong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import hrm.service.LuongService;

import java.time.YearMonth;
import java.util.List;

@Controller
@RequestMapping("/luong")
public class LuongController {

    private final LuongService luongService;

    @Autowired
    public LuongController(LuongService luongService) {
        this.luongService = luongService;
    }

    @GetMapping("/danhsach")
    public String showLuongByMonth(@RequestParam(value = "selectedMonth", required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth selectedMonth,
                                   Model model) {
        List<Luong> danhSachLuong;
        if (selectedMonth != null) {
            danhSachLuong = luongService.getLuongByMonth(selectedMonth);
        } else {
            danhSachLuong = luongService.getAllLuong();
        }
        model.addAttribute("danhSachLuong", danhSachLuong);
        return "hr/luong"; // Thymeleaf template name
    }
}

