package diagne.election_management_ws.Entities.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class FileService
{
    private final String DEFAULT_UPLOAD_DIRECTORY = "public/images";
    @Autowired
    public FileService()
    {
    }

    public String uploadFile(MultipartFile file)
    {
        String fileName = new Date().getTime() + "-" + file.getOriginalFilename();
        return this.storeFile(file,fileName);
    }


    public String storeFile(MultipartFile file, String fileName)
    {
        try
        {
            Path path = Paths.get(DEFAULT_UPLOAD_DIRECTORY,fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return path.toString().replace('\\','/');
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new FileException("Something went wrong!");
        }
    }

    public Resource getDownloadResource(String fileName)
    {
        try
        {
            Path filePath = Paths.get(DEFAULT_UPLOAD_DIRECTORY, fileName);
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e)
        {
            throw new FileException("Fichier introuvable!");
        }
    }

    public void removeFile(String fileURI)
    {
        try
        {
            Files.delete(Paths.get(fileURI));
        } catch (IOException e)
        {
            throw new FileException("Erreur de la suppresssion du fichier!");
        }
    }
}