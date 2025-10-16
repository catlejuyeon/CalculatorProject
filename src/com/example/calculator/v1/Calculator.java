package com.example.calculator.v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {
    //연산결과 저장 컬렉션(캡슐화 : private로 선언)
    private final List<Double> results = new ArrayList<Double>();

    public double calculate(int num1, int num2, String operator) {
        double result = switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> {
                if (num2 == 0) {
                    throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                }
                yield (double) num1 / num2;
            }
            default -> throw new IllegalArgumentException("잘못된 연산 기호입니다. (+, -, *, / 만 하나만 입력하세요.");
        };

        results.add(result); //연산결과를 저장
        return result;
    }

    //getter
    //외부에서 결과를 읽을 수 있도록 게터 추가
    public List<Double> getResults() {
        return Collections.unmodifiableList(results);
    }

    //setter
    //
    public void clearResults() {
        results.clear();
    }
}
