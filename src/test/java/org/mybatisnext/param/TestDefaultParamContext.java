package org.mybatisnext.param;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestDefaultParamContext {
    @Test
    void testIsPresent() {
        ParamContext context = ParamContextHolder.getContext();
        Assertions.assertFalse(context.isPresent());
        context.addParam("deptId", 10);
        Assertions.assertTrue(context.isPresent());
    }

    @Test
    void testAddParam() {
        ParamContext context = ParamContextHolder.getContext();
        //上下文为空
        Assertions.assertFalse(context.isPresent());
        context.addParam("deptId", 10);
        context.addParam("userId", 1);
        Object deptId = context.getContext().get("deptId");
        Object userId = context.getContext().get("userId");
        Assertions.assertNotNull(deptId);
        Assertions.assertNotNull(userId);
        Assertions.assertTrue(() -> {
            return deptId instanceof Integer && (Integer) deptId == 10;
        });
        Assertions.assertTrue(() -> {
            return userId instanceof Integer && (Integer) userId == 1;
        });

    }

    @Test
    void testAddParams() {
        ParamContext context = ParamContextHolder.getContext();
        Assertions.assertFalse(context.isPresent());
        //初次新增
        HashMap<String, Object> params0 = new HashMap<>();
        params0.put("deptId", 10);
        params0.put("userId", 1);
        context.addParams(params0);
        Map<String, Object> params = context.getContext();
        params0.forEach((key, value) -> {
            Assertions.assertTrue(() -> {
                return params.containsKey(key) && value.equals(params.get(key));
            });
        });
        //二次新增
        HashMap<String, Object> params1 = new HashMap<>();
        params1.put("name", "youchunhai");
        params1.put("age", 18);
        context.addParams(params1);
        params0.putAll(params1);
        params0.forEach((key, value) -> {
            Assertions.assertTrue(() -> {
                return params.containsKey(key) && value.equals(params.get(key));
            });
        });
        //覆盖
        Object name0 = context.addParam("name", "Jasonyou");
        Assertions.assertEquals("youchunhai", name0);
        Object name1 = context.getContext().get("name");
        Assertions.assertEquals("Jasonyou", name1);

    }
}
