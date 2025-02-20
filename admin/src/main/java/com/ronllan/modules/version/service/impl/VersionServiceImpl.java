package com.ronllan.modules.version.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ronllan.common.exception.DefineException;
import com.ronllan.common.exception.ErrorCode;
import com.ronllan.common.utils.BaseUtils;
import com.ronllan.modules.version.dto.VersionDto;
import com.ronllan.modules.version.service.VersionService;

import lombok.AllArgsConstructor;


/**
 * 版本管理
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class VersionServiceImpl implements VersionService {

    @Override
    public List<VersionDto> list(Map<String, Object> params) {
    	List<VersionDto> dtoList = new ArrayList();
    	List<VersionDto> temp = new ArrayList();
    	String fileName = "version.txt";
    	File file = null;
    	try {
    		file = ResourceUtils.getFile(System.getProperty("user.dir")+"/"+fileName);
    		if(!file.exists()){
        		file = ResourceUtils.getFile("classpath:"+fileName);
        	}
		} catch (Exception e) {
			
		}
    	try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
    		VersionDto dto = null;
    		String line;
            while ((line = reader.readLine()) != null) {
            	if(line.indexOf("|版本号")!=-1) {
            		if(dto==null) {
            			dto = new VersionDto();
            			dto.setDate(line.substring(0,10));
            			dto.setVersion(line.substring(11));
            		}else {
            			temp.add(dto);
            			dto = new VersionDto();
            			dto.setDate(line.substring(0,10));
            			dto.setVersion(line.substring(11));
            		}
            	}else {
            		if(dto!=null) {
            			if(StringUtils.isBlank(dto.getContent())) {
            				dto.setContent(line);
            			}else {
            				dto.setContent(dto.getContent()+"\r\n"+line);
            			}
            		}
            	}
            }
            if(dto!=null) {
            	temp.add(dto);
            }
            if(params.get("limit")==null) {
            	dtoList.addAll(temp);
            }else {
            	if(BaseUtils.isNumeric(String.valueOf(params.get("limit")))) {
            		int count = Integer.parseInt(String.valueOf(params.get("limit")));
            		if(count<=temp.size()) {
            			for(int i=0;i<count;i++) {
            				dtoList.add(temp.get(i));
            			}
            		}else {
            			dtoList.addAll(temp);
            		}
            	}else {
            		dtoList.addAll(temp);
            	}
            }
        } catch (Exception e) {
        	throw new DefineException(ErrorCode.READ_FILE_ERROR_0);
        }
        return dtoList;
    }

}
