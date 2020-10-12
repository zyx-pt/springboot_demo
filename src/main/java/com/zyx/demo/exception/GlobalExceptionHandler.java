package com.zyx.demo.exception;

import com.zyx.demo.controller.ExceptionController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * <pre>
 * 描述： 异常处理类
 * 1. 使用 @ControllerAdvice 和 @ExceptionHandler 处理全局异常
 *      在类上加上@ControllerAdvice注解这个类就成为了全局异常处理类
 * 2. @ExceptionHandler 处理 Controller 级别的异常
 *  通过 assignableTypes 指定特定的 Controller 类，让异常处理类只处理特定类抛出的异常。
 * </pre>
 *
 * @author zhengyongxian
 */
@ControllerAdvice(assignableTypes = {ExceptionController.class})
@ResponseBody
public class GlobalExceptionHandler {

    ErrorResponse illegalArgumentResponse = new ErrorResponse(new IllegalArgumentException("参数错误!"));
    ErrorResponse resourceNotFoundResponse = new ErrorResponse(new ResourceNotFoundException("Sorry, the resource not found!"));

    /**
     * @Description: 拦截所有异常, 这里只是为了演示，一般情况下一个方法特定处理一种异常
     *
     * @Author: zhengyongxina
     * @Date: 2020/10/10 11:12
     * @param e
     * @return: org.springframework.http.ResponseEntity<com.zyx.demo.exception.ErrorResponse>
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {

        if (e instanceof IllegalArgumentException) {
            return ResponseEntity.status(400).body(illegalArgumentResponse);
        } else if (e instanceof ResourceNotFoundException) {
            return ResponseEntity.status(404).body(resourceNotFoundResponse);
        }
        return null;
    }
}
