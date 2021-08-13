package erecrutement.finances.gov.ma.MEF.Services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadDownloadService implements IUploadDownload{

    public static final String DIRECTORY = "src/main/resources/static/FilesUploaded";

    @Override
    public ResponseEntity<List<String>> UploadFiles(List<MultipartFile> multipartFiles) throws Exception{
        List<String> filenames = new ArrayList<>();
        for (MultipartFile file :multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY,filename).toAbsolutePath().normalize();
            copy(file.getInputStream(),fileStorage,REPLACE_EXISTING);
            filenames.add(filename);
        }
        return ResponseEntity.ok().body(filenames);
    }

    @Override
    public ResponseEntity<Resource> DownloadFile(String file) throws Exception {
        Path filepath = get(DIRECTORY).toAbsolutePath().normalize().resolve(file);
        if(!Files.exists(filepath)){
            throw new FileNotFoundException("File not founded in the storage");
        }
        Resource ressource = new UrlResource(filepath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", file);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + ressource.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filepath)))
                .headers(httpHeaders).body(ressource);
    }

    @Override
    public ResponseEntity<String> DeleteFile(String file) throws Exception {
        Path path = Paths.get(DIRECTORY+"/"+file);
        boolean isDeleted = Files.deleteIfExists(path);
        if(isDeleted) {
            return new ResponseEntity<String>("File deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("File not deleted successfully seems that is already removed", HttpStatus.BAD_REQUEST);
        }
    }
}
