package com.example.calculator.v1;

import java.util.Scanner;

//Lv 2. 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기 만들기
//캡슐화
//사칙연산을 수행 후, 결과값 반환 메서드 구현
//연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
public class App {
    public static void main(String[] args) {
        Calculate calculator = new Calculate();
        Scanner scanner = new Scanner(System.in);

        System.out.print("첫 번째 숫자를 입력하세요: ");
        int num1 = scanner.nextInt();
        System.out.print("두 번째 숫자를 입력하세요: ");
        int num2 = scanner.nextInt();
        System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
        String operator = scanner.next();

        double result = calculator.calculate(num1, num2, operator);

        if (result % 1 == 0) {
            System.out.println("결과: " + (int) result);
        } else {
            System.out.printf("결과: %f", result);
        }
    }
}
