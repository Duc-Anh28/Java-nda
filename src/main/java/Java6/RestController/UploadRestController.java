package Java6.RestController;

import java.io.File;
import java.io.IOException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Java6.Service.UploadService;

@CrossOrigin("*")
@RestController
public class UploadRestController {

	@Autowired
	UploadService uploadService;
	
	@PostMapping("/rest/upload/{folder}")
	public JsonNode upload(@PathParam("file") MultipartFile file,
			@PathVariable("folder") String folder) throws IOException {
		File savedFile = uploadService.save(file, folder);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", savedFile.getName());
		node.put("size",savedFile.length());
		return node;
	}

	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(
			@RequestParam("file") MultipartFile multipartFile)
			throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		long size = multipartFile.getSize();

		String filecode = uploadService.saveFile(fileName, multipartFile);

//		FileUploadResponse response = new FileUploadResponse();
//		response.setFileName(fileName);
//		response.setSize(size);
//		response.setDownloadUri("/downloadFile/" + filecode);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", fileName);
		node.put("size",filecode);
		return new ResponseEntity<>(node, HttpStatus.OK);
	}
}
