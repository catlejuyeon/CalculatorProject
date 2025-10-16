package com.example.calculator.v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {
    //연산결과 저장 컬렉션(캡슐화 : private로 선언)
    //List를 사용해 여러개의 결과를 순서대로 저장
    //ArrayList는 동적으로 크기가 늘어나며, 중복된 값도 허용
    private final List<Double> results = new ArrayList<Double>();

    public double calculate(int num1, int num2, String operator) {
        return switch (operator) {
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
    }

    //getter
    //외부에서 결과를 읽을 수 있도록 게터 추가
    //ollections.unmodifiableList 읽기 전용으로 만들어
    //외부에서 add/remove등으로 리스트를 수정하지 못하게 막음
    public List<Double> getResults() {
        return Collections.unmodifiableList(results);
    }

    //setter 역할을 하는 메서드
    //저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능 FIFO
    //외부에서 값을 입력받고 연산결과를 전달받아 내부리스트에 저장하는 진입점
    public void removeRequest(double result) {
        removeResults(result); // 내부 기능 호출
    }
    //저장된 결과가 2개 이상이면 가장 오래된 결과(인덱스 0)를 삭제
    //내부에서만 사용하는 저장 로직 메서드
    //새로 전달된 결과를 리스트에 추가
    private void removeResults(double result) {
        if (results.size() >= 2) {
            results.remove(0);
        }
        results.add(result); // 새 연산 결과를 리스트에 추가
    }
}
