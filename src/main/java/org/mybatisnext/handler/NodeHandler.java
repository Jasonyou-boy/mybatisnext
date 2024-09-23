package org.mybatisnext.handler;

import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.SqlNode;

/**

 *
 * @author Jasonyou
 * @version 1.0.0
 * @since 1.0.0
 */
public interface NodeHandler extends ExternalHandler {
    /**
     * <b>NOTE:不建议直接使用，直接操作节点是不安全的，使用时务必要对mybatis动态sql足够了解</b>
     *
     * @param contents 内容
     * @param context  上下文
     * @return boolean
     */
    boolean apply(SqlNode contents, DynamicContext context);
}
