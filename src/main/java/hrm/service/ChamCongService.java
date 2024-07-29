package hrm.service;

import hrm.model.*;
import hrm.repository.ChamCongRepository;
import hrm.repository.LuongRepository;
import hrm.repository.NhanVienRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ChamCongService {

    private final ChamCongRepository chamCongRepository;
    private final NhanVienRepository nhanVienRepository;
    private final LuongRepository luongRepository;

    public List<ChamCong> getAllChamCong() {
        return chamCongRepository.findAll(); // Example method; adjust as per your repository
    }

    @Autowired
    public ChamCongService(ChamCongRepository chamCongRepository, NhanVienRepository nhanVienRepository,LuongRepository luongRepository) {
        this.chamCongRepository = chamCongRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.luongRepository = luongRepository;
    }


    @Transactional
    public List<ChamCong> importChamCongDataFromExcel(MultipartFile file) throws IOException, ParseException {
        List<ChamCong> chamCongList = readChamCongFromExcel(file.getInputStream());

        for (ChamCong chamCong : chamCongList) {
            String idNV = chamCong.getId().getIdNV();
            NhanVien nhanVien = nhanVienRepository.findById(idNV).orElse(null);

            if (nhanVien == null) {
                // Handle case where NhanVien with given ID is not found
                continue;
            }

            chamCong.setNhanVien(nhanVien);
        }

        return chamCongRepository.saveAll(chamCongList); // Save imported ChamCong entities and return the list
    }

    private List<ChamCong> readChamCongFromExcel(InputStream inputStream) throws IOException, ParseException {
        List<ChamCong> chamCongList = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMyyyy");

        // Skip header row if needed
        if (rows.hasNext()) {
            rows.next(); // Skipping header row
        }

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            ChamCong chamCong = new ChamCong();
            ChamCongId chamCongId = new ChamCongId();

            // Read employee ID from first column assuming it's a string
            Cell idCell = currentRow.getCell(0);
            String idNV = idCell != null ? idCell.getStringCellValue() : null;
            if (idNV == null || idNV.isEmpty()) {
                // Handle null or empty employee ID
                continue; // Skip this row or throw an exception as needed
            }
            chamCongId.setIdNV(idNV);

            // Parse date from the second column
            Cell dateCell = currentRow.getCell(1);
            Date ngayLamViec = null;
            if (dateCell != null) {
                if (dateCell.getCellType() == CellType.NUMERIC) {
                    if (DateUtil.isCellDateFormatted(dateCell)) {
                        ngayLamViec = dateCell.getDateCellValue();
                    }
                } else if (dateCell.getCellType() == CellType.STRING) {
                    ngayLamViec = dateFormat.parse(dateCell.getStringCellValue());
                }
            }


            // Convert date to month and year integer format
            int monthYear = Integer.parseInt(monthYearFormat.format(ngayLamViec));
            chamCongId.setId(monthYear);

            // Set other numeric cell values
            chamCong.setThangNam(currentRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getDateCellValue());
            chamCong.setSoNgayLam((int) currentRow.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            chamCong.setSoNgayNghi((int) currentRow.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            chamCong.setSoLanTre((int) currentRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());

            // Set composite primary key and add to list
            chamCong.setId(chamCongId);
            chamCongList.add(chamCong);
        }

        workbook.close();
        return chamCongList;
    }

    public List<ChamCong> getChamCongByMonth(YearMonth selectedMonth) {
        int year = selectedMonth.getYear();
        int month = selectedMonth.getMonthValue();
        return chamCongRepository.findByMonth(year, month);
    }
    @Transactional
    public void updateSalary(List<ChamCong> chamCongList) {
        for (ChamCong chamCong : chamCongList) {
            NhanVien nhanVien = chamCong.getNhanVien();
            ChucVu chucVu = nhanVien.getChucVu();
            LuongId luongId = new LuongId(chamCong.getId().getId(), chamCong.getId().getIdNV());
            Luong luong = luongRepository.findById(luongId).orElse(new Luong());

            luong.setId(luongId);
            luong.setNhanVien(nhanVien);
            luong.setThangNam(chamCong.getThangNam());

            BigDecimal luongCoBan = chucVu.getLuongCoBan();
            BigDecimal heSoLuong = chucVu.getHeSoLuong();
            BigDecimal phuCap = chucVu.getPhuCapChucVu();
            int soNgayLam = chamCong.getSoNgayLam();
            int soNgayNghi = chamCong.getSoNgayNghi();
            int soLanTre = chamCong.getSoLanTre();

            // Salary calculation
            BigDecimal salary = luongCoBan.multiply(BigDecimal.valueOf(soNgayLam)).multiply(heSoLuong).add(phuCap);
            BigDecimal tax = calculateTax(salary);
            salary = salary.subtract(tax);

            // Deduct 5% if total days off and late arrivals exceed 5
            if (soNgayNghi + soLanTre > 5) {
                salary = salary.subtract(salary.multiply(BigDecimal.valueOf(0.05)));
            }

            luong.setThanhTien(salary);
            luongRepository.save(luong);
        }
    }
    private BigDecimal calculateTax(BigDecimal salary) {
        BigDecimal taxRate = BigDecimal.valueOf(0.1); // 10% tax
        return salary.multiply(taxRate);
    }
}
