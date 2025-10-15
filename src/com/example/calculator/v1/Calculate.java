package com.example.calculator.v1;

public class Calculate {

    public double calculate(int num1, int num2, String operator) {
        double result=0;

        switch (operator) {
            case "+":
                result= num1 + num2;
                break;
            case "-":
                result= num1 - num2;
                break;
            case "*":
                result= num1 * num2;
                break;
            case "/":
                if (num2==0) {
                    throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                }
                return (double) num1 / num2;
            default:
                throw new IllegalArgumentException("잘못된 연산 기호입니다. (+, -, *, / 만 하나만 입력하세요.");
        }

        return result;
    }


}
