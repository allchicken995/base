package com.ronllan.common.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

public class BaseUtils {
	
	public static String getUuid(){
		return UUID.randomUUID().toString();
	}
	
	public static MultipartFile byteToMultipartFile(byte[] bytes) throws IOException{
		return new MockMultipartFile("multipartFile",IOUtils.toByteArray(new ByteArrayInputStream(bytes)));
	}
	
}
