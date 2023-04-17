package com.yinjunbiao.Exception;

/**
 * 执行sql时的异常
 * @author yinjunbiao
 * @version 1.0
 */
public class MapperException extends RuntimeException{

    public MapperException(String message) {
        super(message);
    }
}
