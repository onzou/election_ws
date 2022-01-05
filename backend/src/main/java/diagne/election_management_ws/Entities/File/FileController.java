package diagne.election_management_ws.Entities.File;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "file")
public class FileController
{
    private final FileService fileService;

    public FileController(FileService fileService)
    {
        this.fileService = fileService;
    }

    @GetMapping()
    public ResponseEntity<Object> downloadFileFromLocal(@RequestParam("filename") String fileName)
    {
        Resource resource = this.fileService.getDownloadResource(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("multipart/form-data"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
