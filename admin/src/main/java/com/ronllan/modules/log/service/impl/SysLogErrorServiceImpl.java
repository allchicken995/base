package com.ronllan.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ronllan.modules.log.dao.SysLogErrorDao;
import com.ronllan.modules.log.dto.SysLogErrorDto;
import com.ronllan.modules.log.entity.SysLogErrorEntity;
import com.ronllan.modules.log.service.SysLogErrorService;

import com.ronllan.common.constant.Constant;
import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
        IPage<SysLogErrorEntity> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogErrorDto.class);
    }

    @Override
    public List<SysLogErrorDto> list(Map<String, Object> params) {
        List<SysLogErrorEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogErrorDto.class);
    }

    private QueryWrapper<SysLogErrorEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<SysLogErrorEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogErrorEntity entity) {
        insert(entity);
    }

}