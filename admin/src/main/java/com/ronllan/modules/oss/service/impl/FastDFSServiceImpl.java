package com.ronllan.modules.oss.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ronllan.common.exception.DefineException;
import com.ronllan.common.exception.ErrorCode;
import com.ronllan.modules.oss.service.FastDFSService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FastDFSServiceImpl implements FastDFSService {
	
	@Autowired
	private FastFileStorageClient storageClient;
	
	@Override
	public Map<String, Object> uploadFile(MultipartFile file) {
		Map<String, Object> result = new HashMap<>(1);
		try {
			String ext = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
			StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
			result.put("ext", ext);
			result.put("url", storePath.getFullPath());
		} catch (Exception e) {
			throw new DefineException(ErrorCode.UPLOAD_FILE_ERROR_0);
		}
		return result;
	}

	@Override
	public boolean deleteFile(String url) {
		try {
            StorePath storePath = StorePath.praseFromUrl(url);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
        	return false;
        }
		return true;
	}
	
}