package com.example.calculator.v2;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final DecimalFormat df = new DecimalFormat("0.#####");
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArithmeticCalculator<Number> calc = new ArithmeticCalculator<>();

        while (true) {
            Number num1, num2;
            String operation;

            // 1️⃣ 첫 번째 숫자 입력
            try {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                num1 = parseNumber(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 정확히 입력해주세요.");
                continue;
            }

            // 2️⃣ 두 번째 숫자 입력
            try {
                System.out.print("두 번째 숫자를 입력하세요: ");
                num2 = parseNumber(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 정확히 입력해주세요.");
                continue;
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
                Number result = calc.calculate(num1, num2, operation);  // Number로 변경
                System.out.println("결과: " + formatNumber(result));
                printResults(calc.getResults());
            } catch (ArithmeticException e) {
                System.out.println("[ERROR] " + e.getMessage());
                continue;
            }

            calc.maxValueResultsDesc();
            calc.maxValue();

            // 5️⃣ 계속할지 여부
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료): ");
            if (sc.nextLine().equalsIgnoreCase("exit")) break;

            System.out.println();
        }
        sc.close();
        System.out.println("프로그램을 종료합니다.");
    }

    // 입력값을 적절한 타입으로 변환 (정수/실수 자동 판별)
    private static Number parseNumber(String input) {
        if (input.contains(".")) {
            return Double.parseDouble(input);   //"3.5" -> Double
        } else {
            return Integer.parseInt(input); //"3" -> Integer
        }
    }

    // Number를 적절한 형식으로 출력
    private static String formatNumber(Number num) {
        if (num instanceof Integer) {
            return String.valueOf(num.intValue());
        }
        double d = num.doubleValue();
        return (d % 1 == 0)
                ? String.valueOf((int) d) //5.0 -> "5"
                : df.format(d); //2.5 -> "2.5" , 1.12345 -> "1.12345"
    }

    // 결과 출력
    private static void printResults(List<Number> results) {  //List<Number>로 변경
        System.out.print("저장된 연산 결과: ");
        for (int i = 0; i < results.size(); i++) {
            System.out.print(formatNumber(results.get(i)));  //formatNumber 사용
            if (i < results.size() - 1) System.out.print(", ");
        }
        System.out.println();
    }
}
