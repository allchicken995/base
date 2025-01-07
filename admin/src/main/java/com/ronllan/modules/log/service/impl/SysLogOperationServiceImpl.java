package com.ronllan.modules.log.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.log.dao.SysLogOperationDao;
import com.ronllan.modules.log.dto.SysLogOperationDto;
import com.ronllan.modules.log.entity.SysLogOperationEntity;
import com.ronllan.modules.log.service.SysLogOperationService;

/**
 * 操作日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Service
public class SysLogOperationServiceImpl extends BaseServiceImpl<SysLogOperationDao, SysLogOperationEntity> implements SysLogOperationService {

    @Override
    public PageData<SysLogOperationDto> page(Map<String, Object> params) {
        return getPage(params,SysLogOperationDto.class);
    }

    @Override
    public List<SysLogOperationDto> list(Map<String, Object> params) {
        List<SysLogOperationEntity> entityList = getObjectList(params);
        return ConvertUtils.sourceToTarget(entityList, SysLogOperationDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogOperationEntity entity) {
        insert(entity);
    }

}