package org.mybatisnext.param;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * 参数注入的上下文
 * <p>上下文生命结束后执行{@link #remove}清空内容
 *
 * @author Jasonyou
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultParamContext extends ThreadLocal<Map<String, Object>> implements ParamContext {


    @Override
    public Object addParam(String key, Object value) {
        return initIfAbsent().put(key, value);
    }

    @Override
    public void addParams(Map<String, Object> params) {
        initIfAbsent().putAll(params);
    }

    @Override
    public Map<String, Object> getContext() {
        Map<String, Object> params = get();
        return params != null ? Collections.unmodifiableMap(params) : null;
    }


    @Override
    public boolean isPresent() {
        return get() != null;
    }

    private Map<String, Object> initIfAbsent() {
        Map<String, Object> params = get();
        if (params == null) {
            params = new HashMap<>();
            set(params);
        }
        return params;
    }

}
