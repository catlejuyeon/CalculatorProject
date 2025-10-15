package com.example.calculator;

import java.util.Scanner;

//Lv 1. 클래스 없이 기본적인 연산을 수행할 수 있는 계산기 만들기
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //반복문 시작
        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int num1 = scanner.nextInt();
            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = scanner.nextInt();
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
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
                //나눗셈은 double로 소수점 표현하기 위해 (+, -, *)는 프린트문에 계산식넣고
                // '/'나누기의 연산 값은 result 변수로 형변환 하기위해 초기화
                case "/":
                    if (num2 == 0) {
                        System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                    } else {
                        double divResult = (double) num1 / num2;
                        System.out.println("결과: " + divResult);
                    }
                    break;
                //괄호안에 적히지 않은 연산자 입력시 주의문
                default:
                    System.out.println("잘못된 연산 기호입니다.");
            }

            //exit 입력시 반복문 종료
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            String next = scanner.next();
            if (next.equalsIgnoreCase("exit")) {
                System.out.println("계산기를 종료합니다.");
                break;
            }
        }
    }
}
