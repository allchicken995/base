package com.ronllan.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class StreamUtils {

	public static byte[] toByteArray(InputStream input) throws IOException {
		byte[] bytes = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            int bufferSize;
            byte[] buffer = new byte[4096];
            while ((bufferSize = input.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bufferSize);
            }
            // 获取字节数组
            bytes = outputStream.toByteArray();
        } finally {
            if (input != null) {
            	input.close();
            }
        }
		return bytes;
    }
	
	public static MultipartFile byteToMultipartFile(byte[] bytes) throws IOException{
		return new MockMultipartFile("multipartFile",IOUtils.toByteArray(new ByteArrayInputStream(bytes)));
	}

}
