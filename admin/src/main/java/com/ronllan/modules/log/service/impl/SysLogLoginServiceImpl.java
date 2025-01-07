package com.ronllan.modules.log.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.log.dao.SysLogLoginDao;
import com.ronllan.modules.log.dto.SysLogLoginDto;
import com.ronllan.modules.log.entity.SysLogLoginEntity;
import com.ronllan.modules.log.service.SysLogLoginService;

/**
 * 登录日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Service
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLoginDao, SysLogLoginEntity> implements SysLogLoginService {

    @Override
    public PageData<SysLogLoginDto> page(Map<String, Object> params) {
        return getPage(params,SysLogLoginDto.class);
    }

    @Override
    public List<SysLogLoginDto> list(Map<String, Object> params) {
        List<SysLogLoginEntity> entityList = getObjectList(params);
        return ConvertUtils.sourceToTarget(entityList, SysLogLoginDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogLoginDto dto) {
    	SysLogLoginEntity entity = ConvertUtils.sourceToTarget(dto, SysLogLoginEntity.class);
        insert(entity);
    }

}