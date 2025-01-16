package com.ronllan.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

}
