package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    void sumCalculate() {
        assertThat(calculator.sum(1, 2)).isEqualTo(3);
    }

    @Test
    void sumCalculateAnothor() {
        assertThat(calculator.sum(1, 4)).isEqualTo(5);
    }
}
