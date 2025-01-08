package com.ronllan.common.service.impl;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.ronllan.common.constant.Constant;
import com.ronllan.common.dao.BaseDao;
import com.ronllan.common.exception.DefineException;
import com.ronllan.common.exception.ErrorCode;
import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;
import com.ronllan.common.utils.ConvertUtils;

/**
 * 基础服务类，所有Service都要继承
 *
 * @author glq gugameds066@gmail.com
 */
public abstract class BaseServiceImpl<M extends BaseDao<T>, T> implements BaseService<T> {
	
    @Autowired
    protected M baseDao;
    
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    protected Log log = LogFactory.getLog(getClass());

    /**
     * 获取分页对象
     *
     * @param params            分页查询参数
     * @param defaultOrderField 默认排序字段
     * @param isAsc             排序方式
     */
    protected IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;
        if (params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constant.LIMIT));
        }
        //分页对象
        Page<T> page = new Page<>(curPage, limit);
        //分页参数
        params.put(Constant.PAGE, page);
        //排序字段
        String orderField = (String) params.get(Constant.ORDER_FIELD);
        String order = (String) params.get(Constant.ORDER);
        //前端字段排序
        if (StringUtils.isNotBlank(orderField) && StringUtils.isNotBlank(order)) {
            if (Constant.ASC.equalsIgnoreCase(order)) {
                return page.addOrder(OrderItem.asc(orderField));
            } else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }
        //没有排序字段，则不排序
        if (StringUtils.isBlank(defaultOrderField)) {
            return page;
        }
        //默认排序
        if (isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        } else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }
        return page;
    }

    protected <T> PageData<T> getPageData(IPage page,List<?> list, Class<T> target) {
        List<T> targetList = ConvertUtils.sourceToTarget(list, target);
        return new PageData<>(page,targetList);
    }

    protected <T> PageData<T> getPageData(IPage page, Class<T> target) {
        return getPageData(page,page.getRecords(), target);
    }

    protected void paramsToLike(Map<String, Object> params, String... likes) {
        for (String like : likes) {
            String val = (String) params.get(like);
            if (StringUtils.isNotBlank(val)) {
                params.put(like, "%" + val + "%");
            } else {
                params.put(like, null);
            }
        }
    }

    protected Class<M> currentMapperClass() {
        return (Class<M>) ReflectionKit.getSuperClassGenericType(this.getClass(), BaseServiceImpl.class, 0);
    }

    @Override
    public Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), BaseServiceImpl.class, 1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(T entity) {
        return SqlHelper.retBool(baseDao.insert(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(Collection<T> entityList) {
    	for(T entity : entityList) {
    		if(!insert(entity)) {
    			throw new DefineException(ErrorCode.DATA_INSERT_ERROR_0);
    		}
    	}
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(T entity) {
        return SqlHelper.retBool(baseDao.updateById(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Collection<T> entityList) {
    	for(T entity : entityList) {
    		if(!updateById(entity)) {
    			throw new DefineException(ErrorCode.DATA_UPDATE_ERROR_0);
    		}
    	}
        return true;
    }
    
    @Override
	public <T> PageData<T> getPage(Map<String, Object> params,Class<T> target) {
    	QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq(Constant.LOGICAL_DELETE, 0);
        String className = target.getCanonicalName().replace("dto", "entity").replace("Dto", "Entity");
        for(String name : params.keySet()) {
        	Object object = params.get(name);
        	if(object!=null) {
	        	Field field = null;
	        	try {
					field = Class.forName(className).getDeclaredField(name);
				} catch (Exception e) {
					field = null;
				}
	        	if(field!=null) {
					Annotation[] annotation = field.getAnnotations();
		        	if(annotation!=null&&annotation.length>0) {
		        		
		        	}else {
		        		if(field.getType().equals(String.class)) {
		        			if(String.valueOf(object).length()>0) {
		        				wrapper.like(field.getName().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase(), object);
		        			}
	        			}else {
		        			wrapper.eq(field.getName().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase(), object);
		        		}
		        	}
	        	}
        	}
        }
        IPage page = baseDao.selectPage(getPage(params, Constant.CREATE_DATE, false),wrapper);
		return getPageData(page,target) ;
	}
    
    @Override
	public List<T> getObjectList(Map<String, Object> params) {
    	QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq(Constant.LOGICAL_DELETE, 0);
        String className = ReflectionKit.getSuperClassGenericType(this.getClass(), BaseServiceImpl.class, 1).getCanonicalName();
        for(String name : params.keySet()) {
        	Object object = params.get(name);
        	if(object!=null) {
	        	Field field = null;
	        	try {
					field = Class.forName(className).getDeclaredField(name);
				} catch (Exception e) {
					field = null;
				}
	        	if(field!=null) {
					Annotation[] annotation = field.getAnnotations();
		        	if(annotation!=null&&annotation.length>0) {
		        		
		        	}else {
		        		if(field.getType().equals(String.class)) {
		        			if(String.valueOf(object).length()>0) {
		        				wrapper.like(field.getName().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase(), object);
		        			}
		        		}else {
		        			wrapper.eq(field.getName().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase(), object);
		        		}
		        	}
	        	}
        	}
        }
        return baseDao.selectList(wrapper);
	}
    
    @Override
	public List<T> getObjectList(String sqlMethod,Map<String, Object> params) {
		String statement = this.currentMapperClass().getName() + StringPool.DOT + sqlMethod;
		try {
			sqlSessionFactory.getConfiguration().getMappedStatement(statement);
		} catch (Exception e) {
			throw new DefineException(ErrorCode.SQLMETHOD_ERROR_1,sqlMethod);
		}
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	return sqlSession.selectList(statement, params);
    }

	@Override
	public List<T> getObjectList(T entity) {
		QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(Constant.LOGICAL_DELETE, 0);
        for(Field field : entity.getClass().getDeclaredFields()) {
        	field.setAccessible(true);
        	try {
				Object object = field.get(entity);
				if(object!=null) {
					Annotation[] annotation = field.getAnnotations();
		        	if(annotation!=null&&annotation.length>0) {
		        		
		        	}else {
		        		wrapper.eq(field.getName().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase(), object);
		        	}
				}
			} catch (Exception e) {
				throw new DefineException(e.getLocalizedMessage(),e);
			} 
        }
		return baseDao.selectList(wrapper);
	}
    
    @Override
	public T getObject(T entity) {
		QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(Constant.LOGICAL_DELETE, 0);
        for(Field field : entity.getClass().getDeclaredFields()) {
        	field.setAccessible(true);
        	try {
				Object object = field.get(entity);
				if(object!=null) {
					Annotation[] annotation = field.getAnnotations();
		        	if(annotation!=null&&annotation.length>0) {
		        		
		        	}else {
		        		wrapper.eq(field.getName().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase(), object);
		        	}
				}
			} catch (Exception e) {
				throw new DefineException(e.getLocalizedMessage(),e);
			} 
        }
		return baseDao.selectOne(wrapper);
	}
    
	@Override
	public T getObjectById(Serializable id) {
		QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(Constant.ID, id);
        wrapper.eq(Constant.LOGICAL_DELETE, 0);
		return baseDao.selectOne(wrapper);
	}
	
	@Override
	public T getObjectById(String sqlMethod,Serializable id) {
		String statement = this.currentMapperClass().getName() + StringPool.DOT + sqlMethod;
		try {
			sqlSessionFactory.getConfiguration().getMappedStatement(statement);
		} catch (Exception e) {
			throw new DefineException(ErrorCode.SQLMETHOD_ERROR_1,sqlMethod);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constant.ID, id);
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	return sqlSession.selectOne(statement, params);
    }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteById(Serializable id) {
		QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(Constant.ID, id);
        wrapper.eq(Constant.LOGICAL_DELETE, 0);
        T object = baseDao.selectOne(wrapper);
        if(object==null) {
        	return true;
        }else {
        	return SqlHelper.retBool(baseDao.delete(object));
        }
	}
    
	@Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Serializable[] idList) {
    	for(Serializable id : idList) {
    		if(!deleteById(id)) {
    			throw new DefineException(ErrorCode.DATA_DELETE_ERROR_0);
    		}
    	}
        return true;
    }
}