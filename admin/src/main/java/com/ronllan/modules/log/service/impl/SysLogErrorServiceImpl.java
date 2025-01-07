package com.ronllan.modules.log.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.log.dao.SysLogErrorDao;
import com.ronllan.modules.log.dto.SysLogErrorDto;
import com.ronllan.modules.log.entity.SysLogErrorEntity;
import com.ronllan.modules.log.service.SysLogErrorService;

/**
 * 异常日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Service
public class SysLogErrorServiceImpl extends BaseServiceImpl<SysLogErrorDao, SysLogErrorEntity> implements SysLogErrorService {

    @Override
    public PageData<SysLogErrorDto> page(Map<String, Object> params) {
		return getPage(params,SysLogErrorDto.class);
    }

    @Override
    public List<SysLogErrorDto> list(Map<String, Object> params) {
        List<SysLogErrorEntity> entityList = getObjectList(params);
        return ConvertUtils.sourceToTarget(entityList, SysLogErrorDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogErrorEntity entity) {
        insert(entity);
    }

}