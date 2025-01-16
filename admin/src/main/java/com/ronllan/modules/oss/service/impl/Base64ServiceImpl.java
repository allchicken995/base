package com.ronllan.modules.oss.service.impl;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ronllan.common.exception.DefineException;
import com.ronllan.common.exception.ErrorCode;
import com.ronllan.common.utils.StreamUtils;
import com.ronllan.modules.oss.service.Base64Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class Base64ServiceImpl implements Base64Service {
	
	@Override
	public Map<String, Object> uploadFile(MultipartFile file) {
		Map<String, Object> result = new HashMap<>(1);
		try {
	        String ext = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
	        String url = Base64.getEncoder().encodeToString(StreamUtils.toByteArray(file.getInputStream()));
			result.put("ext", ext);
			result.put("url", "data:"+file.getContentType()+";base64,"+url);
		} catch (Exception e) {
			throw new DefineException(ErrorCode.UPLOAD_FILE_ERROR_0);
		}
		return result;
	}
	
}