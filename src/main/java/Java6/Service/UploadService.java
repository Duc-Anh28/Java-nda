package Java6.Service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	File save(MultipartFile file, String folder) throws IOException;

	String saveFile(String fileName, MultipartFile multipartFile) throws IOException;
}
