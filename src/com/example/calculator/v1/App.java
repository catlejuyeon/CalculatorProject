package com.example.calculator.v1;

import java.util.InputMismatchException;
import java.util.Scanner;

//Lv 2. 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기 만들기
//캡슐화
//사칙연산을 수행 후, 결과값 반환 메서드 구현
//연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
public class App {
    public static void main(String[] args) {
        Calculate calculator = new Calculate();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                int num1 = Integer.parseInt(scanner.nextLine());

                System.out.print("두 번째 숫자를 입력하세요: ");
                int num2 = Integer.parseInt(scanner.nextLine());

                System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
                String operator = scanner.nextLine();

                double result = calculator.calculate(num1, num2, operator);

                if (result % 1 == 0) {
                    System.out.println("결과: " + (int) result);
                } else {
                    System.out.printf("결과: %f\n", result);
                }

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 정확히 입력해주세요.");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 연산 기호를 정확히 입력해주세요.");
                continue;
            } catch (ArithmeticException e) {
                System.out.println("[ERROR] " + e.getMessage());
                continue;
            }

            // 계산 끝난 후 종료 여부 묻기
            //println으로하니 enter입력하면서 너무 간격이 멀어져 print로 작성
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료, 아니면 Enter): ");
            String exitInput = scanner.nextLine();
            if (exitInput.equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            System.out.println(); // 다음 계산을 위한 줄바꿈
        }
    }
}