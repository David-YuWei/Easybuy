package com.easybuy.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class ImageService {
	
	private String imagePath = "";
	
	public ImageService(){
		
	}
	
	public String upload(HttpServletRequest request,String param) throws Exception{
		String imageUrl = "";
		List<MultipartFile> multipartFiles = null;
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			multipartFiles = multipartRequest.getFiles(param);
		}

		if (multipartFiles != null && multipartFiles.size() >0){
			MultipartFile mfile = multipartFiles.get(0);
			if(mfile.getSize() > 0){
				String type = mfile.getOriginalFilename().substring(mfile.getOriginalFilename().lastIndexOf(".")+1);
				long timestamp = System.currentTimeMillis();
				Random rand = new Random();
				rand.nextInt(10);
				String fileName = String.valueOf(timestamp) + "at"+String.valueOf(rand.nextInt(10)) +"."+type;
				File image = new File(imagePath+fileName);
				BufferedImage bi = ImageIO.read(mfile.getInputStream());
				BufferedImage thumbnail = Scalr.resize(bi, 120, 120);
				ImageIO.write(thumbnail, type, image);
				imageUrl = "file/"+fileName;
			}
		}
		return imageUrl;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
