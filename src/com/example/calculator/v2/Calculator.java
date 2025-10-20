package com.example.calculator.v2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                System.out.println("아직 계산 결과가 없습니다.");
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
    }
}
    /*
    //사칙연산 종류를 정의한 enum
    public enum OperatorType {
        Sum, Minus, Multiply, Divide
    }

    //제네릭 계산기 클래스
    //T는 Number의 하위 타입(Integer, Double등)
    public static class ArithmeticCalculator<T extends Number> {
        private final List<Double> results = new ArrayList<>();  //계산 결과 저장하는 리스트
        //문자열을 T타입으로 변환하는 메서드

        //문자열 입력을 받아 계산 수행(내부적으로 T로 변환)
        public Double calculate(T num1, T num2, String operatorSymbol) {
            double a = num1.doubleValue();
            double b = num2.doubleValue();

            OperatorType operator = parseOperator(operatorSymbol);
            double result = switch (operator) {
                case Sum -> a + b;
                case Minus -> a - b;
                case Multiply -> a * b;
                case Divide -> {
                    if (b == 0)
                        throw new ArithmeticException("나눗셈 연산에서 0으로 나눌 수 없습니다!");
                    yield a / b;
                }
            };

            // 결과 저장
            saveResult(result);
            return result;

        }
        private int decimalPlaces = 0; //소수점 자리수 설정
//
//        //실제 계산 로직 : T타입 숫자 2개와 연산자 기호를 받아 계산
//        public T calculate(T num1, T num2, String operatorSymbol) {
//            OperatorType operator = parseOperator(operatorSymbol); // 문자열 -> enum변환
//
//            // doubleValue()로 변환해서 계산 (정수든 실수든 가능)
//            double a = num1.doubleValue();
//            double b = num2.doubleValue();
//
//            double result = switch (operator) {
//                case Sum -> a + b;
//                case Minus -> a - b;
//                case Multiply -> a * b;
//                case Divide -> {
//                    if (b == 0) throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
//                    yield a / b;
//                }
//            };

            //계산 결과를 다시 T타입으로 변환
            T finalResult;
            // 타입 체크로 분기
            if (num1 instanceof Integer) {
                finalResult = parser.apply(String.valueOf((int) result));
            } else if (num1 instanceof Double) {
                finalResult = parser.apply(String.valueOf(result));
            } else {
                throw new IllegalArgumentException("지원하지 않는 숫자 타입입니다.");
            }

            removeRequest(finalResult); // 현재 인스턴스 -> 결과 저장
            return finalResult;
        }

        //소수점 자리수 설정(0~15까지 허용)
        public void setDecimalPlaces(int decimalPlaces) {
            if (decimalPlaces >= 0 && decimalPlaces <= 15) {
                this.decimalPlaces = decimalPlaces;
            }
        }

        //DecimalFormat 패턴 반환 (예: "0.######")
        public String getDecimalFormatPattern() {
            return decimalPlaces == 0 ? "0" : "0." + "#".repeat(decimalPlaces);
        }

        // 문자열 연산자 → enum 변환
        public OperatorType parseOperator(String input) {
            return switch (input.trim()) {
                case "+" -> OperatorType.Sum;
                case "-" -> OperatorType.Minus;
                case "*" -> OperatorType.Multiply;
                case "/" -> OperatorType.Divide;
                default -> throw new IllegalArgumentException("지원하지 않는 연산자입니다.");
            };
        }

        // 결과 저장 (최대 2개까지 유지)
        public void removeRequest(T result) {
            if (results.size() >= 2) {
                results.remove(0);  //가장 오래된 결과 제거
            }
            results.add(result); //새 결과 저장
        }

        //외부에서 결과 리스트를 읽을 수 있도록 제공(읽기전용)
        public List<T> getResults() {
            return Collections.unmodifiableList(results);
        }
    }
}

//    // 문자열 연산자 → enum 변환
//    public OperatorType parseOperator(String input) {
//        return switch (input.trim()) {
//            case "+" -> OperatorType.Sum;
//            case "-" -> OperatorType.Minus;
//            case "*" -> OperatorType.Multiply;
//            case "/" -> OperatorType.Divide;
//            default -> throw new IllegalArgumentException("지원하지 않는 연산자입니다.");
//        };
//    }

//    // 결과 저장
//    public void removeRequest(double result) {
//        if (results.size() >= 2) {
//            results.remove(0);
//        }
//        results.add(result);
//    }
//
//    public List<Double> getResults() {
//        return Collections.unmodifiableList(results);
//    }

//    public void setDecimalPlaces(int decimalPlaces) {
//        if (decimalPlaces >= 0 && decimalPlaces <= 15) {
//            this.decimalPlaces = decimalPlaces;
//        }
//    }

//    public String getDecimalFormatPattern() {
//        return decimalPlaces == 0 ? "0" : "0." + "#".repeat(decimalPlaces);
//    }

//    // App에서 호출할 수 있도록 calculate 메서드 추가
//    public double calculate(int num1, int num2, String operatorSymbol) {
//        OperatorType operator = parseOperator(operatorSymbol);
//        ArithmeticCalculator<Integer> calc = new ArithmeticCalculator<>();
//        return calc.calculate(num1, num2, operator);
//    }

*/