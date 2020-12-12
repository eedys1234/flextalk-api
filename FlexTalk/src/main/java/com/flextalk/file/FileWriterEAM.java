package com.flextalk.file;

import java.io.IOException;

/**
 * �ܺ� resource�� ���� clean up�� �ϱ����� Ŭ����
 * @author ����ȯ
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
