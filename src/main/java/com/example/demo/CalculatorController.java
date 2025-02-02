package com.example.demo;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    private Calculator calculator;

    @GetMapping("/sum")
    public String calculator(@PathParam("a") Integer a, @PathParam("b") Integer b) {
        return String.valueOf(calculator.sum(a, b));
    }
}
