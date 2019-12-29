package by.dima.files.controller;

import by.dima.files.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/profile")
public class ProfileImageController {

    private FileService fileService;

    public ProfileImageController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, Authentication authentication) {
        fileService.create(file);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{filename:.+}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getFile(@PathVariable String filename, HttpServletRequest request) {
        String contentType = null;
        Resource resource = fileService.get(filename);
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
