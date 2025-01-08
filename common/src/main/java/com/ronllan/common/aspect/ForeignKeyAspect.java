package com.ronllan.common.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ronllan.common.annotation.ForeignKeyField;
import com.ronllan.common.dao.BaseDao;
import com.ronllan.common.constant.Constant;
import com.ronllan.common.entity.BaseEntity;
import com.ronllan.common.exception.DefineException;
import com.ronllan.common.exception.ErrorCode;

import lombok.extern.slf4j.Slf4j;
/**
 * 外键级联处理切面
 *
 * @author glq gugameds066@gmail.com
 */
@Slf4j
@Aspect
@Component
public class ForeignKeyAspect {
    
	@Autowired
	private ApplicationContext applicationContext;
	
    @Before(value = "@annotation(com.ronllan.common.annotation.ForeignKey)")
    public void before(JoinPoint joinPoint) throws Throwable {
    	Object[] args = joinPoint.getArgs();
    	if (args != null && args.length > 0) {
    		String table = null;
    		Object object = args[0];
    		for(Annotation annotation : object.getClass().getAnnotations()) {
    			if(TableName.class.getCanonicalName().equals(annotation.annotationType().getCanonicalName())) {
    				table = ((TableName)annotation).value();
    			}
    		}
    		for (Field field : object.getClass().getSuperclass().getDeclaredFields()) {
    			field.setAccessible(true);
				if("table".equals(field.getName())){
					field.set(object, table);
					break;
				}
			}
        }
    }
	
    @AfterReturning(returning = "result", pointcut = "@annotation(com.ronllan.common.annotation.ForeignKey)")
    public void after(JoinPoint joinPoint, Integer result) throws Throwable {
    	if(result!=1) {
    		 throw new DefineException(ErrorCode.DATA_DELETE_ERROR_0);
    	}
    	//获取代理对象
    	Object proxy = joinPoint.getThis(); 
    	Advised advised = (Advised) proxy;
    	String interfaceName = advised.getProxiedInterfaces()[0].getCanonicalName();
    	String className = interfaceName.replace("dao", "entity").replace("Dao", "Entity");
    	for(Field field : Class.forName(className).getDeclaredFields()) {
    		if("fk".equals(field.getName())) {
    			for(Annotation annotation : field.getAnnotations()) {
    				if(ForeignKeyField.class.getCanonicalName().equals(annotation.annotationType().getCanonicalName())) {
    					for(String handle : ((ForeignKeyField)annotation).handle()) {
    						String foreignKey = handle.split("=")[0].split("\\.")[handle.split("=")[0].split("\\.").length-1];
    						String handleType = handle.split("=")[1];
    						String handleClassName = null;
    						for(int i=0;i<handle.split("=")[0].split("\\.").length-1;i++) {
    							if(handleClassName==null) {
    								handleClassName = handle.split("=")[0].split("\\.")[i];
    							}else {
    								handleClassName = handleClassName + "." + handle.split("=")[0].split("\\.")[i];
    							}
    						}
    						if(handleClassName==null) {
    							throw new DefineException(ErrorCode.HANDLE_OBJECT_NOT_NULL_0);
    						}
    						String handleInterfaceName = handleClassName.replace("entity", "dao").replace("Entity", "Dao");
    						BaseEntity target = (BaseEntity) joinPoint.getArgs()[0];
    						BaseDao dao = (BaseDao) applicationContext.getBean(Class.forName(handleInterfaceName));
    						switch (handleType) {
								case Constant.CASCADE:
									QueryWrapper wrapper = new QueryWrapper<>();
							        wrapper.eq(foreignKey.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase(), target.getId());
									List<BaseEntity> list = dao.selectList(wrapper);
									for(BaseEntity entity : list) {
										dao.delete(entity);
									}
									break;
								case Constant.SETNULL:
									String table = null;
									Object object = Class.forName(handleClassName).newInstance();
									for(Annotation temp : object.getClass().getAnnotations()) {
						    			if(TableName.class.getCanonicalName().equals(temp.annotationType().getCanonicalName())) {
						    				table = ((TableName)temp).value();
						    			}
						    		}
									for (Field temp : object.getClass().getSuperclass().getDeclaredFields()) {
										temp.setAccessible(true);
										if("table".equals(temp.getName())){
											temp.set(object, table);
										}else if("foreignKey".equals(temp.getName())){
											temp.set(object, foreignKey.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase());
										}else if("foreignValue".equals(temp.getName())){
											temp.set(object, target.getId());
										}
									}
									dao.handleForeignKey(object);
									break;
								case Constant.NOACTION:
									break;
								case Constant.RESTRICT:
									throw new DefineException(ErrorCode.DATA_DELETE_NOT_ALLOW_0);
							}
    					}
    					break;
    				}
    			}
    			break;
    		}
    	}
    }
}
