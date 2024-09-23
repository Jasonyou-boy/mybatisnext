package org.mybatisnext.handler;

/**
 *
 * @author Jasonyou
 * @version 1.0.0
 * @since 1.0.0
 */
@FunctionalInterface
public interface SqlHandler extends ExternalHandler {
    String apply(String sql);
}
