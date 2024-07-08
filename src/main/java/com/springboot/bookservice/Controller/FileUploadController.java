package com.springboot.bookservice.Controller;

import com.springboot.bookservice.Helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Fileupload")
public class FileUploadController {

	@Autowired
	private FileUploadHelper helper;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		System.out.println(file.getContentType());
		System.out.println(file.isEmpty());

		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is Empty");
		} else if (!file.getContentType().equals("application/pdf")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is not Pdf, pls send Pdfs only");
		}
		else {
		helper.uploadFile(file);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
		}
		
		

	}
}
