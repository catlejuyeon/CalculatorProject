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
        private final List<Double> results = new ArrayList<>();
        private Double lastResult=null; //최근 계산 결과 저장

        // 실제 계산 수행 (정수/실수 상관없이)
        public Double calculate(T num1, T num2, String operatorSymbol) {
            double a = num1.doubleValue();
            double b = num2.doubleValue();

            OperatorType operator = parseOperator(operatorSymbol);
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

            // 결과 저장
            saveResult(result);
            lastResult = result;
            return result;
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

        // 결과 저장 (저장 제한 없음)
        private void saveResult(double result) {
            results.add(result);
        }

        public List<Double> getResults() {
            return Collections.unmodifiableList(results);
        }

        //람다+스트림 사용 : 최근 결과보다 큰 값 중 최댓값 출력하기
        public void maxValue(){
            if(lastResult==null){
                System.out.println("아직 저장된 연산 결과가 없습니다.");
                return;
            }

            DecimalFormat df = new DecimalFormat("0.######");

            results.stream()
                    .filter(r->r>lastResult)
                    .max(Double::compare)
                    .ifPresentOrElse(
                            max ->{
                                String formatMax = (max % 1 == 0)
                                        ? String.valueOf(max.intValue())
                                        : df.format(max);
                                System.out.println("연산 결과 중 최댓값: " +formatMax);
                            },
                            () -> {
                                String formatLast = (lastResult % 1==0)
                                        ?String.valueOf(lastResult.intValue())
                                        : df.format(lastResult);
                                System.out.println("최근 연산 결과(" + formatLast +")보다 큰 값이 없습니다.");
                            }
                    );
        }

        //저장된 연산 결과들 중 최근 연산 결과보다 큰 값들을 내림차순으로 보여주기
        public  void maxValueResultsDesc(){
            DecimalFormat df = new DecimalFormat("0.#######");

            if(lastResult==null || results.isEmpty()){
                return;
            }

            List<Double> maxValueResult = results.stream()
                    .filter(r -> r > lastResult)
                    .sorted(Comparator.reverseOrder())//내림차순
                    .toList();

            if(maxValueResult.isEmpty()){
                return;
            }

            String formatLast = (lastResult % 1 == 0)
                    ? String.valueOf(lastResult.intValue())
                    : df.format(lastResult);

            String formatList = maxValueResult.stream()
                    .map(r -> (r%1==0)
                            ? String.valueOf(r.intValue())
                            :df.format(r))
                            .collect(Collectors.joining(", "));
            System.out.println("최근 연산 결과(" + formatLast + ")보다 큰 값들 (내림차순): "+formatList);
        }

    }
}
