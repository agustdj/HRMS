
package hrm.service;

import hrm.model.Luong;
import hrm.repository.LuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;

@Service
public class LuongService {

    private final LuongRepository luongRepository;

    @Autowired
    public LuongService(LuongRepository luongRepository) {
        this.luongRepository = luongRepository;
    }

    @Transactional(readOnly = true)
    public List<Luong> getLuongByMonth(YearMonth selectedMonth) {
        int year = selectedMonth.getYear();
        int month = selectedMonth.getMonthValue();
        return luongRepository.findByMonth(year, month);
    }

    @Transactional(readOnly = true)
    public List<Luong> getAllLuong() {
        return luongRepository.findAll();
    }
}

