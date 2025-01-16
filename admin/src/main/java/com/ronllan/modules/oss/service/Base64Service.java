package com.ronllan.modules.oss.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Base64文件接口
 *
 * @author glq gugameds066@gmail.com
 */
public interface Base64Service {
    /**
     * 文件上传
     */
	Map<String, Object> uploadFile(MultipartFile file);
	
}
