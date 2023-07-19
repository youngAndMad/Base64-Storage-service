package danekerscode.base64.service;

import danekerscode.base64.model.FileEntity;
import danekerscode.base64.repository.FileRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity saveFile(MultipartFile file) {
        FileEntity fileEntity = new FileEntity();
        try {
            fileEntity.setBase64Data(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(file.getContentType());
        fileEntity.setName(file.getName());
        fileEntity.setType(MediaType.APPLICATION_PDF);
        return fileRepository.save(fileEntity);
    }

    public FileEntity getFileById(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    private void getMediaTypeFromFile(String file){
        if (!file.contains("pdf")){
            throw new RuntimeException("invalid file type");
        }
    }
}
