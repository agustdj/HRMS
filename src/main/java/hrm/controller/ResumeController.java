package hrm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

@Controller
public class ResumeController {

    @Value("${flask.api.url}")
    private String flaskApiUrl;

    @GetMapping("/resume")
    public String uploadPage() {
        return "resume/index";
    }

    @PostMapping("/resume")
    public String uploadCV(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // Lưu file tạm thời
            File tempFile = Files.createTempFile("cv_", ".pdf").toFile();
            file.transferTo(tempFile);

            // Gọi Flask API
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new FileSystemResource(tempFile));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(flaskApiUrl + "/predict", requestEntity, Map.class);

            // Nhận kết quả và hiển thị
            String predictedCategory = response.getBody().get("Predicted Category").toString();
            model.addAttribute("predictedCategory", predictedCategory);

            // Xóa file tạm sau khi sử dụng xong
            tempFile.delete();

            return "resume/result";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Đã xảy ra lỗi khi xử lý CV của bạn.");
            return "error/error";
        }
    }
}
