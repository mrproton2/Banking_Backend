package com.Test.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api/customer")
class CustomerController {
    @GetMapping("/hello")
    public String hello() { return "hello customer"; }
}