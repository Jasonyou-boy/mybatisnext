package org.mybatisnext.param;

import java.util.Map;

public interface ParamContext {

    boolean isPresent();

    Object addParam(String key, Object value);

    void addParams(Map<String, Object> params);

    /**
     * 返回上下文，若上下文为空则返回null,若不为空返回不可修改的Map
     *
     * @return {@link Map }<{@link String }, {@link Object }>
     */
    Map<String, Object> getContext();
}
