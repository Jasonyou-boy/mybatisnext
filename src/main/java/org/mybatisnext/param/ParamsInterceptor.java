package org.mybatisnext.param;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;
import java.util.Properties;

/**
 * @author Jasonyou
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class ParamsInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object parameter = args[1];
        args[1] = mergeParameter(parameter);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }

    /**
     * 增强参数
     *
     * @param parameter 参数,可能为null,任意非{@link java.util.Collection }及其子类,{@link org.apache.ibatis.binding.MapperMethod.ParamMap},{@link java.util.Map}
     * @return {@link Object} 若没有增强则返回空值
     * @see {@link org.apache.ibatis.reflection.ParamNameResolver#getNamedParams(Object[])}
     * @see {@link org.apache.ibatis.reflection.ParamNameResolver#wrapToMapIfCollection(Object, String)}
     */
    private Object mergeParameter(Object parameter) {
        ParamContext paramContext = ParamContextHolder.getContext();
        if (!paramContext.isPresent()) return parameter;
        Map<String, Object> context = paramContext.getContext();
        //多数情况下
        if (parameter instanceof Map) {
            if (((Map<?, ?>) parameter).containsKey("_ctx"))
                throw new PersistenceException("尝试参数注入失败，原始参数中_ctx已存在，发生冲突");
            return ((Map<String, Object>) parameter).put("_ctx", context);
        }
        Map<String, Object> paramMap = new MapperMethod.ParamMap();
        paramMap.put("_ctx", context);
        if (parameter instanceof Map) {
            paramMap.putAll((Map<String, ?>) parameter);
        } else {
            //仅有一个值且不为集合时
            paramMap.put("0", parameter);
        }
        return paramMap;
    }
}