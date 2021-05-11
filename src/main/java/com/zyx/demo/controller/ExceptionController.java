package com.zyx.demo.controller;

import com.zyx.demo.exception.ResourceNotFoundException;
import com.zyx.demo.exception.ResourceNotFoundException2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * <pre>
 * 描述：用于模拟抛出异常
 *  1. 使用 @ControllerAdvice 和 @ExceptionHandler 处理全局异常
 *  2. @ExceptionHandler 处理 Controller 级别的异常
 *  3. ResponseStatusException
 * </pre>
 *
 * @author zhengyongxian
 */
@RestController
@RequestMapping("/api")
public class ExceptionController {

    @GetMapping("/illegalArgumentException")
    public void throwException() {
        throw new IllegalArgumentException();
    }

    @GetMapping("/resourceNotFoundException")
    public void throwException2() {
        throw new ResourceNotFoundException();
    }

    /**
     * @Description: 通过 ResponseStatus注解简单处理异常
     * 一般我们不会这样做，因为添加了额外的异常类ResourceNotFoundException2
     * @Author: zhengyongxian
     * @Date: 2020/10/10 17:34
     * @param
     * @return: void
     */
    @GetMapping("/resourceNotFoundException2")
    public void throwException3() {
        throw new ResourceNotFoundException2("Sorry, the resource not found!");
    }

    /**
     * @Description: 3. 通过ResponseStatusException处理异常
     *
     * @Author: zhengyongxian
     * @Date: 2020/10/10 17:36
     * @param
     * @return: void
     */
    @GetMapping("/resourceNotFoundException3")
    public void throwException4() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, the resource not found!", new ResourceNotFoundException());
    }
}