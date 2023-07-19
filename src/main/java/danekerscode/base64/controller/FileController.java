package danekerscode.base64.controller;

import danekerscode.base64.model.FileEntity;
import danekerscode.base64.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file")
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(201).body(fileService.saveFile(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getFileById(@PathVariable Long id) {
        FileEntity fileEntity = fileService.getFileById(id);
        if (fileEntity != null) {
            System.out.println(fileEntity.getType());
            byte[] fileBytes = Base64.getDecoder().decode(fileEntity.getBase64Data());
            ByteArrayResource resource = new ByteArrayResource(fileBytes);
            return ResponseEntity.ok()
                    .header("Content-disposition", "attachment; filename=file.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(fileBytes.length)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

