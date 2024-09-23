package org.mybatisnext;

import org.mybatisnext.handler.NodeHandler;
import org.mybatisnext.handler.SqlHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * 处理器上下文，可以注册多种处理器。
 * <p/>
 * <b>NOTE:</b>在SqlSessionFactory构建之前完成注册
 *
 * @author Jasonyou
 * @version 1.0.0
 * @since 1.0.0
 */
public class HandlerContext {
    private final Map<String, SqlHandler> sqlHandlerMap = new HashMap<>();
    private final Map<String, NodeHandler> nodeHandlerMap = new HashMap<>();

    private static volatile HandlerContext INSTANCE;

    private HandlerContext() {
        // Private constructor to prevent instantiation
    }

    public static HandlerContext getInstance() {
        if (INSTANCE == null) {
            synchronized (HandlerContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HandlerContext();
                }
                return INSTANCE;
            }
        } else {
            return INSTANCE;
        }
    }

    public static SqlHandler getSqlHandler(String name) {
        return getInstance().sqlHandlerMap.get(name);
    }

    public static NodeHandler getNodeHandler(String name) {
        return getInstance().nodeHandlerMap.get(name);
    }

    public static void registerHandler(String name, SqlHandler handler) {
        getInstance().sqlHandlerMap.put(name, handler);
    }

    public static void registerHandler(String name, NodeHandler handler) {
        getInstance().nodeHandlerMap.put(name, handler);
    }

}
