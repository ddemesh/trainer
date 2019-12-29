package by.dima.files.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean exist(String filename);
    void create(MultipartFile file);
    Resource get(String filename);
}
