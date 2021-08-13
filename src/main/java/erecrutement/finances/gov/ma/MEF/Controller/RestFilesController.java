package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Services.IUploadDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/file")
public class RestFilesController implements IFileController{

    private IUploadDownload files;

    @Autowired
    public void setFiles(IUploadDownload files) {
        this.files = files;
    }

    @PostMapping("/upload")
    @Override
    public ResponseEntity<List<String>> UploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles) throws Exception {
        return files.UploadFiles(multipartFiles);
    }

    @GetMapping("/download/{filename}")
    @Override
    public ResponseEntity<Resource> DownloadFile(@PathVariable("filename") String file) throws Exception {
        return files.DownloadFile(file);
    }

    @GetMapping("/delete/{file}")
    @Override
    public ResponseEntity<String> DeleteFile(@PathVariable("file") String file) throws Exception {
        return files.DeleteFile(file);
    }
}
