package erecrutement.finances.gov.ma.MEF.Services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUploadDownload {

    public ResponseEntity<List<String>> UploadFiles(List<MultipartFile> multipartFiles) throws Exception;
    public ResponseEntity<Resource> DownloadFile(String file) throws Exception;
    public ResponseEntity<String> DeleteFile(String file) throws Exception;

}
