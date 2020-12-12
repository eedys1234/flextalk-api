package com.flextalk.file;

import java.io.IOException;

/**
 * 외부 resource에 대한 clean up을 하기위한 클래스
 * @author 이정환
 *
 */
public class FileWriterEAM {
	
	public static void use(
			String filePath, 
			IFileWriter<FileWriterARM, IOException> block) throws IOException {
		
		try(FileWriterARM arm = new FileWriterARM(filePath)){
			block.accept(arm);
		}
	}
}
