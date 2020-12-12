package com.flextalk.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//nio�� �����ؾ���..?
//nio�� io�� ���̺��� �����Ұ�
public class FileWriterARM implements AutoCloseable {

	private OutputStream out;
	
	protected FileWriterARM(String filePath) throws IOException {
		out = new FileOutputStream(filePath);
	}
	
	public void stuff(byte[] data) throws IOException {
		out.write(data);
	}
	
	@Override
	public void close() throws IOException {
		out.flush();
		out.close();
	}

}
