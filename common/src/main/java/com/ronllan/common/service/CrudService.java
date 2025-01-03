package com.ronllan.common.service;

import java.util.List;
import java.util.Map;

import com.ronllan.common.page.PageData;

/**
 *  CRUD基础服务接口
 *
 * @author glq gugameds066@gmail.com
 */
public interface CrudService<T, D> extends BaseService<T> {

    PageData<D> page(Map<String, Object> params);

    List<D> list(Map<String, Object> params);

    D get(Long id);

    void save(D dto);

    void update(D dto);

    void delete(Long[] ids);

}