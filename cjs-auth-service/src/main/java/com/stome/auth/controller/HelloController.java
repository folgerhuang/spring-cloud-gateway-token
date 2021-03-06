package com.stome.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc xxxx
 * @date 2020/5/26 23:36
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping("/sayHello")
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @GetMapping("/sayHi")
    public String sayHi(@RequestHeader("userId") String userId) {
        return "Hi " + userId;
    }
}
