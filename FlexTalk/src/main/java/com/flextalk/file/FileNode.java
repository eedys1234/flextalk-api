package com.flextalk.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class FileNode {

	/*
	 * FileName
	 * IsDirectory 여부
	 * 순서
	 * 날짜
	 * 부모 디렉토리
	 */
	
	private String fileKey;
	private String fileName;
	private String isDirectory;
	private int order;
	private Date createAt;
	private String parentFile;
	
	public FileNode(File file) {

		this.fileName = file.getName();
		this.isDirectory = file.isDirectory()?"1":"0";
		this.parentFile = file.getParentFile().getName();
		this.createAt = createTime(file);			
	}
	
	private Date createTime(File file) {
		BasicFileAttributes readAttributes = null;
		
		try {
			readAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);			
		}
		catch(IOException e) {
			log.error("FileNode-createTime", e.getMessage());
		}
		return new Date(readAttributes.creationTime().toMillis());
	}


}
