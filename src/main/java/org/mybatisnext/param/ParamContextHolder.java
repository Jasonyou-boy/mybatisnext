package org.mybatisnext.param;

import lombok.Getter;

/**
 * @author Jasonyou
 * @version 1.0.0
 * @since 1.0.0
 */
public class ParamContextHolder {

    @Getter
    private static final ParamContext context = new DefaultParamContext();

}
