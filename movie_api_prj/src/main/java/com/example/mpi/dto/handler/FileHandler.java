package com.example.mpi.dto.handler;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.mpi.dto.FileDto;

import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@AllArgsConstructor
@Component
public class FileHandler {

	private final String UP_DIR = "C:\\_javaweb\\movie_api_prj_git\\movie_api_prj\\frontend\\src\\assets\\uploadFile";
	
	public List<FileDto> uploadFiles(MultipartFile[] files) {
		
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		
		if (!folders.exists()) {
			folders.mkdirs();
		}
		
		List<FileDto> fileList = new ArrayList<FileDto>();
		
		for (MultipartFile file : files) {
			
			FileDto fileDto = new FileDto();
			fileDto.setSaveDir(today);
			fileDto.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf("\\") + 1);
			fileDto.setFileName(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			fileDto.setUuid(uuid.toString());
			
			String fullFileName = uuid.toString() + "_" + onlyFileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile);
				if (isImageFile(storeFile)) {
					fileDto.setFileType(1);
					File thumbnail = new File(folders,
							uuid.toString() + "_th_" + onlyFileName);
					Thumbnails.of(storeFile).size(640, 480).toFile(thumbnail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			fileList.add(fileDto);
		}
		
		return fileList;
	}
	
	private boolean isImageFile(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
