package by.dima.files.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileRepository {
    boolean exist(String filename);

    void create(MultipartFile file);

    Resource get(String filename);
}
