package com.joo.abysshop.util.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class FileUtil {

    public static void save(MultipartFile file, String uploadDir, String filename) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            file.transferTo(uploadPath.resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + filename, e);
        }
    }
}
