package danekerscode.base64.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.MediaType;

import java.nio.file.spi.FileTypeDetector;

@Entity
@Getter @Setter
@ToString
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String base64Data;

    private String name;

    private MediaType type;

}
