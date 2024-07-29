package hrm.service;

import hrm.model.ChucVu;
import hrm.repository.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChucVuService {
    @Autowired
    private ChucVuRepository chucVuRepository;

    public List<ChucVu> findAll() {
        return chucVuRepository.findAll();
    }

    public ChucVu findById(int idChucVu) {
        return chucVuRepository.findById(idChucVu).orElse(null);
    }
}
