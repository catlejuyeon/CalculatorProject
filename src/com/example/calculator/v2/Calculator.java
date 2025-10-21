package com.example.calculator.v2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {
    public enum OperatorType { SUM, MINUS, MULTIPLY, DIVIDE }

    // 제네릭 계산기 클래스
    public static class ArithmeticCalculator<T extends Number> {
        // Number 타입으로 Integer/Double 모두 저장
        private final List<Number> results = new ArrayList<>();
        private Number lastResult=null; //최근 계산 결과 저장
        private static final DecimalFormat df = new DecimalFormat("0.#####"); //소수점 5자리까지만 출력

        // 실제 계산 수행
        public Number calculate(T num1, T num2, String operatorSymbol) {
            OperatorType operator = parseOperator(operatorSymbol);

            // 📌 핵심: 둘 다 정수인지 확인
            // 나눗셈 제외: 정수 나눗셈은 정수가 나올 수 도 있지만, 실수가 나올 경우 소수점을 버리므로 -> 나눗셈은 항상 실수 연산 수행
            if (num1 instanceof Integer && num2 instanceof Integer && operator != OperatorType.DIVIDE) {
                // 정수끼리 계산
                int a = num1.intValue();
                int b = num2.intValue();

                int result = switch (operator) {
                    case SUM -> a + b;
                    case MINUS -> a - b;
                    case MULTIPLY -> a * b;
                    default -> throw new IllegalStateException("정수 연산은 나눗셈을 지원하지 않습니다.");
                };

                Integer boxedResult = result;  // int를 Integer로 박싱
                results.add(boxedResult);
                lastResult = boxedResult;
                return boxedResult; // Integer 반환
            }

            // 하나라도 실수이거나 나눗셈인 경우
            else {
                double a = num1.doubleValue();
                double b = num2.doubleValue();

                double result = switch (operator) {
                    case SUM -> a + b;
                    case MINUS -> a - b;
                    case MULTIPLY -> a * b;
                    case DIVIDE -> {
                        if (b == 0)
                            throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                        yield a / b;
                    }
                };

                // 결과가 정수값이면 Integer로 저장 (예: 6.0 / 2.0 = 3)
                Number finalResult = (result % 1 == 0) ? Integer.valueOf((int) result) : result;
                results.add(finalResult);
                lastResult = finalResult;
                return finalResult; // Integer 또는 Double 반환
            }
        }

        // 연산자 문자열 → enum 변환
        private OperatorType parseOperator(String input) {
            return switch (input.trim()) {
                case "+" -> OperatorType.SUM;
                case "-" -> OperatorType.MINUS;
                case "*" -> OperatorType.MULTIPLY;
                case "/" -> OperatorType.DIVIDE;
                default -> throw new IllegalArgumentException("지원하지 않는 연산자입니다.");
            };
        }

        public List<Number> getResults() {
            return Collections.unmodifiableList(results);
        }

        // 람다+스트림 사용: 최근 결과보다 큰 값 중 최댓값 출력하기
        public void maxValue() {
            if (lastResult == null) {
                System.out.println("아직 저장된 연산 결과가 없습니다.");
                return;
            }

            double lastValue = lastResult.doubleValue();

            results.stream()
                    .filter(r -> r.doubleValue() > lastValue)   //필터링
                    .max(Comparator.comparingDouble(Number::doubleValue))  //최댓값
                    .ifPresentOrElse(
                            max -> {
                                String formatMax = formatNumber(max);
                                System.out.println("연산 결과 중 최댓값: " + formatMax);
                            },
                            () -> {
                                String formatLast = formatNumber(lastResult);
                                System.out.println("최근 연산 결과(" + formatLast + ")보다 큰 값이 없습니다.");
                            }
                    );
        }

        // 저장된 연산 결과들 중 최근 연산 결과보다 큰 값들을 내림차순으로 보여주기
        public void maxValueResultsDesc() {
            if (lastResult == null || results.isEmpty()) {
                return;
            }

            double lastValue = lastResult.doubleValue();

            List<Number> maxValueResult = results.stream()
                    .filter(r -> r.doubleValue() > lastValue)
                    .sorted(Comparator.comparingDouble(Number::doubleValue).reversed())
                    .toList();

            if (maxValueResult.isEmpty()) {
                return;
            }

            String formatLast = formatNumber(lastResult);
            String formatList = maxValueResult.stream()
                    .map(this::formatNumber)
                    .collect(Collectors.joining(", "));

            System.out.println("최근 연산 결과(" + formatLast + ")보다 큰 값들 (내림차순): " + formatList);
        }

        // Number를 적절한 형식으로 포맷
        private String formatNumber(Number num) {
            if (num instanceof Integer) {
                return String.valueOf(num.intValue());
            }
            double d = num.doubleValue();
            return (d % 1 == 0) ? String.valueOf((int) d) : df.format(d);
        }
    }
}
