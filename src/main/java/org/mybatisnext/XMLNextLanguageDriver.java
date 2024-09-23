package org.mybatisnext;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

/**
 * XMLNext语言驱动程序
 *
 * @author Jasonyou
 * @version 1.0.0
 * @since 1.0.0
 */

public class XMLNextLanguageDriver extends XMLLanguageDriver {
    @Override
    public SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType) {
        MybatisNextXMLScriptBuilder builder = new MybatisNextXMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }
}
