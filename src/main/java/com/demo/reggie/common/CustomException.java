package com.demo.reggie.common;

import org.springframework.context.annotation.Configuration;

/**
 * 自定义业务异常
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
