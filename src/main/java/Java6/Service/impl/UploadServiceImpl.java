package Java6.Service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.management.RuntimeErrorException;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import Java6.Service.UploadService;
import org.thymeleaf.expression.Strings;

@Service
public class UploadServiceImpl implements UploadService{

	@Autowired
	ServletContext app;

	@Autowired
	HttpServletRequest request;
	private Strings RandomStringUtils;

	public File save(MultipartFile file,String folder) throws IOException {
		Path uploadPath = Paths.get("Files-Upload");

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		System.out.println("uploadPath=" + uploadPath);

		File dir = new File(request.getServletContext().getRealPath(folder));//đường dẫn đến file image
		if(!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println("dir= " + dir);
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode())+s.substring(s.lastIndexOf("."));
		try {
			File saveFile = new File(dir,name);
			file.transferTo(saveFile);
			System.out.println(saveFile.getAbsolutePath());
			return saveFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get("Files-Upload");
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		String fileCode = "NDA";

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save file: " + fileName, ioe);
		}
		return fileCode;
	}
}
