package by.dima.files.service.impl;

import by.dima.files.repository.FileRepository;
import by.dima.files.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public boolean exist(String filename) {
        return fileRepository.exist(filename);
    }

    @Override
    public void create(MultipartFile file) {
        fileRepository.create(file);
    }

    @Override
    public Resource get(String filename) {
        return fileRepository.get(filename);
    }
}
