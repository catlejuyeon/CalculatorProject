package com.example.calculator.v2;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator.ArithmeticCalculator<Number> calc = new Calculator.ArithmeticCalculator<>();

        while (true) {
            Number num1, num2;
            String operation;

            // 1️⃣ 첫 번째 숫자 입력
            try {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                num1 = parseNumber(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 정확히 입력해주세요.");
                continue; // 반복 처음으로
            }

            // 2️⃣ 두 번째 숫자 입력
            try {
                System.out.print("두 번째 숫자를 입력하세요: ");
                num2 = parseNumber(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 정확히 입력해주세요.");
                continue; // 반복 처음으로
            }

            // 3️⃣ 연산자 입력
            try {
                System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
                operation = sc.nextLine().trim();
                if (!operation.matches("[+\\-*/]")) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 연산 기호를 정확히 입력해주세요.");
                continue;
            }

            // 4️⃣ 계산 수행
            try {
                Double result = calc.calculate(num1, num2, operation);
                printResults(calc.getResults());
            } catch (ArithmeticException e) {
                System.out.println("[ERROR] " + e.getMessage());
                continue;
            }

            calc.maxValueResultsDesc();
            calc.maxValue();

            // 5️⃣ 계속할지 여부
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료): ");
            if (sc.nextLine().equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            System.out.println();
        }
    }

    // 문자열 → Number (정수/실수 자동 판별)
    private static Number parseNumber(String input) {
        if (input.contains(".")) {
            return Double.parseDouble(input);
        } else {
            return Integer.parseInt(input);
        }
    }

    // 결과 출력 (정수면 정수, 소수면 최대 5자리)
    private static void printResults(List<Double> results) {
        DecimalFormat df = new DecimalFormat("0.#####");
        System.out.print("저장된 연산 결과: ");
        for (int i = 0; i < results.size(); i++) {
            double r = results.get(i);
            System.out.print((r % 1 == 0) ? (int) r : df.format(r));
            if (i < results.size() - 1) System.out.print(", ");
        }
        System.out.println();
    }
}
