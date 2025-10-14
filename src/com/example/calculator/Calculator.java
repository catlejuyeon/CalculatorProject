package com.example.calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("첫 번째 양의 정수 입력하세요: ");
        int num1=scanner.nextInt();
        System.out.println("두 번째 양의 정수 입력하세요: ");
        int num2=scanner.nextInt();
        System.out.println("사칙연산 기호를 입력하세요(+,-,*,/: ");
        String operator = scanner.next();


        switch (operator) {
            case "+":
                System.out.println("결과: " + (num1 + num2));
                break;
            case "-":
                System.out.println("결과: " + (num1 - num2));
                break;
            case "*":
                System.out.println("결과: " + (num1 * num2));
                break;
            case "/":
                if (num2 == 0) {
                    System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                } else {
                    double divResult = (double) num1 / num2;
                    System.out.println("결과: " + divResult);
                }
                break;
            default:
                System.out.println("잘못된 연산 기호입니다.");
        }
    }
}
