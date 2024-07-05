package com.REST_API_book.java.Helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR="D:\\SpringBoot_Workspace\\REST_API_book\\src\\main\\resources\\static";
	
	
	public boolean uploadFile(MultipartFile file) {
		
		boolean f = false;
		try {
			
//			InputStream is = file.getInputStream();
//			byte[] b = new byte[is.available()];
//		    is.read(b);
//			
//			FileOutputStream fos= new FileOutputStream(UPLOAD_DIR+File.separator+file.getOriginalFilename());
//			fos.write(b);
//			fos.flush();
//			fos.close();
//			f=true;
			
			//the above code can be minimized using below
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
