package org.mybatisnext;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.Configuration;
import org.mybatisnext.handler.ExternalHandler;
import org.mybatisnext.handler.NodeHandler;
import org.mybatisnext.handler.SqlHandler;

import java.util.Map;

/**
 * 外部处理适配器
 *
 * @author Jasonyou
 * @version 1.0.0
 * @since 1.0.0
 */
public class HandlerAdapter {
    ExternalHandler externalHandler;
    String targetName;
    String targetType;
    HandlerAdapter(String name, String type){
        this.targetName=name;
        this.targetType=type;
        initAdapter(name,type);
    }

    public boolean apply(SqlNode contents,DynamicContext context){
       if("node".equals(targetType)){
           ((NodeHandler)externalHandler).apply(contents,context);
       }else if("sql".equals(targetType)) {
           ExternalDynamicContext externalContext = new ExternalDynamicContext(context);
           contents.apply(externalContext);
           String applied = ((SqlHandler) externalHandler).apply(externalContext.getSql());
           context.appendSql(applied);
       }else {
           throw new PersistenceException("Supported types are sql and node,Unknown handler type: " + targetType);
       }
       return true;
    }


    /**
     * 初始化
     *
     * @param name 姓名
     * @param type 类型
     */
    private void initAdapter(String name, String type){
        if("sql".equals(type)){
            this.externalHandler = HandlerContext.getSqlHandler(name);
            if(externalHandler==null){
                throw new PersistenceException("No handler of the sql type  found for name: " + name);
            }
        }else if("node".equals(type)){
            this.externalHandler= HandlerContext.getNodeHandler(name);
            if(externalHandler==null){
                throw new PersistenceException("No handler of the node type  found for name: " + name);
            }
        }else {
            throw new PersistenceException("Supported types are sql and node,Unknown handler type: " + type);
        }
    }


    //装饰器模式:防止污染原有上下文sql
    private static class ExternalDynamicContext extends DynamicContext{
        private final DynamicContext delegate;
        private final StringBuilder sqlBuffer;

        public ExternalDynamicContext(DynamicContext context) {
            super(new Configuration(),null);
            this.delegate= context;
            this.sqlBuffer=new StringBuilder();
        }

        @Override
        public Map<String, Object> getBindings() {
            return super.getBindings();
        }

        @Override
        public void bind(String name, Object value) {
            delegate.bind(name, value);
        }

        @Override
        public void appendSql(String sql) {
            sqlBuffer.append(sql);
        }

        /**
         * 获取临时sql
         *
         * @return {@link String }
         */
        @Override
        public String getSql() {
            return sqlBuffer.toString().trim();
        }

        @Override
        public int getUniqueNumber() {
            return delegate.getUniqueNumber();
        }
    }

}
