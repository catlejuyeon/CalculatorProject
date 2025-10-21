package com.example.calculator.v2;

public enum OperatorType {
    SUM,
    MINUS,
    MULTIPLY,
    DIVIDE;

    //기호를 받아서 해당 연산 타입으로 변환
    public static OperatorType fromSymbol(String symbol) {
        return switch (symbol) {
            case "+" -> SUM;
            case "-" -> MINUS;
            case "*" -> MULTIPLY;
            case "/" -> DIVIDE;
            default -> throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + symbol);
        };
    }
}
