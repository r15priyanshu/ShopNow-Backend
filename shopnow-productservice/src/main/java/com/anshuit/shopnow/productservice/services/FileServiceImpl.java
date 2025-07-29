package com.anshuit.shopnow.productservice.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.anshuit.shopnow.productservice.exceptions.CustomException;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String folderPath, MultipartFile file) throws IOException {
		// folderPath WILL BE LIKE : images/product-images THAT WILL BE PASSED
		String fileName = file.getOriginalFilename();
		String fileNameWithTimestamp = "";
		if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
			Date d = new Date();
			fileNameWithTimestamp = d.getTime() + "-" + fileName;
		} else {
			throw new CustomException("File Format Not Supported", HttpStatus.BAD_REQUEST);
		}

		// CREATING FULLPATH ALONG WITH IMAGE NAME
		String fullFilePath = folderPath + File.separator + fileNameWithTimestamp;

		// CREATE FOLDER WHERE IMAGES WILL BE STORED IF ALREADY NOT CREATED
		File f = new File(folderPath);
		if (!f.exists()) {
			f.mkdirs();
		}

		try {
			byte[] data = file.getBytes();
			FileOutputStream fos = new FileOutputStream(fullFilePath);
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			System.out.println("Some Error Occured");
		}
		return fileNameWithTimestamp;
	}

	@Override
	public InputStream serveImage(String folderPath, String fileName) throws FileNotFoundException {
		String fullFilePath = folderPath + File.separator + fileName;
		InputStream is = new FileInputStream(fullFilePath);
		return is;
	}

	@Override
	public boolean deleteImage(String folderPath, String imageName) {
		File f = new File(folderPath + File.separator + imageName);
		if (f.exists()) {
			boolean result = f.delete();
			return result;
		} else {
			return false;
		}
	}

}
