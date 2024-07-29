package hrm.controller;


import hrm.model.ChamCong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import hrm.service.ChamCongService;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/chamcong")
@PreAuthorize("hasAuthority('ADMIN')")
public class ChamCongController {

    @Autowired
    private ChamCongService chamCongService;

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("selectedDate", LocalDate.now());
        return "hr/chamcong";
    }

 @GetMapping("/list")
 public String listChamCong(@RequestParam(value = "selectedMonth", required = false) String selectedMonth, Model model) {
     YearMonth yearMonth;

     if (selectedMonth == null) {
         List<ChamCong> listChamCong = chamCongService.getAllChamCong(); // Fetch from service
         model.addAttribute("listChamCong", listChamCong);
         model.addAttribute("selectedDate", LocalDate.now());
         return "hr/chamcong";
     } else {
             // Convert String to YearMonth
             yearMonth = YearMonth.parse(selectedMonth, DateTimeFormatter.ofPattern("yyyy-MM"));
             List<ChamCong> listChamCong = chamCongService.getChamCongByMonth(yearMonth);
             model.addAttribute("listChamCong", listChamCong);

             return "hr/chamcong";
         }
     }


    @PostMapping("/upload")
    public String uploadChamCongFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<ChamCong> importedChamCongList = chamCongService.importChamCongDataFromExcel(file);
            chamCongService.updateSalary(importedChamCongList); // Call updateSalary with the imported list
            model.addAttribute("message", "File uploaded successfully!");

        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", "Error in data: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/chamcong/list"; // Redirect to hr/chamcong upon successful upload
    }
}