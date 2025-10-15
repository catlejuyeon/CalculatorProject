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
                result = (double) num1 / num2;
                break;
            default:
                System.out.println("잘못된 연산 기호입니다.");
        }

        return result;
    }


}
