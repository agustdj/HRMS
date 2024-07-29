package hrm.service;

import hrm.model.ViTriCV;
import hrm.repository.ViTriCVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViTriCVService {
    @Autowired
    private ViTriCVRepository viTriCVRepository;

    public List<ViTriCV> findAll() {
        return viTriCVRepository.findAll();
    }

    public ViTriCV findById(int idViTri) {
        return viTriCVRepository.findById(idViTri).orElse(null);
    }
}
