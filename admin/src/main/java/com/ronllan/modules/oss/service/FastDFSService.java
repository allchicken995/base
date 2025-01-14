package com.ronllan.modules.oss.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * FastDFS相关接口
 *
 * @author glq gugameds066@gmail.com
 */
public interface FastDFSService {
    /**
     * 文件上传
     */
	Map<String, Object> uploadFile(MultipartFile file);
	/**
     * 文件删除
     */
	boolean deleteFile(String url);
	
}
