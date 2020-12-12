package com.flextalk.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 특정파일의 경로를 가져오는 클래스
 * @author 이정환
 *
 */
public class FileManager {
	
	/*
	 * FileName
	 * IsDirectory 여부
	 * 순서
	 * 날짜
	 * 부모 디렉토리
	 */
	
	public FileManager() {
		
	}
	
	public List<FileNode> find(String filePath) { 
		
		return Stream.of(new File(filePath).listFiles())
					 .flatMap(it->Objects.isNull(it.listFiles()) ?
						 	Stream.of(it):Stream.of(it.listFiles()))
					 .map(FileNode::new)
					 .collect(Collectors.toList());		
		
	}
	
	public boolean copy(String oriPath, String copyPath) throws IOException {
		
		Files.copy(new File(oriPath).toPath(), new File(copyPath).toPath());
		return new File(copyPath).exists();
	}
	
}
