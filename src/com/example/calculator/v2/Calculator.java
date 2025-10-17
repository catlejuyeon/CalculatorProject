package com.example.calculator.v2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {
    public enum OperatorType {
        Sum, Minus, Multiply, Divide
    }



    public static class ArithmeticCalculator {
        public double calculate(double num1, double num2, OperatorType operator) {
            return switch (operator) {
                case Sum -> num1 + num2;
                case Minus -> num1 - num2;
                case Multiply -> num1 * num2;
                case Divide -> {
                    if (num2 == 0) {
                        throw new ArithmeticException("0으로 나눌 수 없습니다.");
                    }
                    yield num1 / num2;
                }
            };
        }
    }
}
