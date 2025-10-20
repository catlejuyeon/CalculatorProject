# CalculatorProject
Java 제네릭을 활용한 사칙연산 계산기 프로젝트
## 📌 개요
이 프로젝트는 제네릭(Generics) 을 활용하여 정수, 실수 등 모든 Number 타입을 지원하는 고급 계산기입니다.
사칙연산 결과를 자동으로 저장하고, 다양한 통계 기능을 제공합니다.
## ✨ 주요 기능
### 1. 제네릭 기반 계산

- Integer, Double, Long, Float 등 모든 Number 타입 지원
- 타입에 관계없이 자동으로 Double로 변환
- 타입 안정성 확보

### 2. 사칙연산

- 덧셈(+), 뺄셈(-), 곱셈(*), 나눗셈(/)
- 0으로 나누기 에러 처리

### 3. 결과 저장 및 관리

- 모든 계산 결과 자동 저장
- 최근 계산 결과 추적
- 저장된 결과 조회 기능

### 4. 통계 기능

- maxValue() : 최근 결과보다 큰 값 중 최댓값 출력
- maxValueResultsDesc() : 최근 결과보다 큰 모든 값을 내림차순으로 출력

### 5. 스마트 포맷팅

- 정수는 정수로 표시 (10.0 → 10)
- 소수는 최대 5자리까지 표시 (3.141592 → 3.14159)

## 🎮 사용 방법
### 기본사용
```java
// 계산기 생성 (Integer 타입)
Calculator.ArithmeticCalculator<Integer> calc = 
    new Calculator.ArithmeticCalculator<>();

// 계산 수행
Double result = calc.calculate(10, 5, "+");  // 15.0

// 결과 조회
List<Double> allResults = calc.getResults();  // [15.0]

// 통계 기능
calc.maxValue();              
calc.maxValueResultsDesc();
```
## 🏗️ 프로젝트 구조
```
com/example/calculator/v2/
├── Calculator.java
│   └── ArithmeticCalculator<T extends Number>
│       ├── calculate(T, T, String) : Double
│       ├── maxValue() : void
│       ├── maxValueResultsDesc() : void
│       ├── getResults() : List<Double>
│       └── (private) convertToDouble(T) : Double
│       └── (private) formatNumber(Double) : String
│
└── App.java (사용자 입출력 처리)
    ├── main(String[])
    └── parseNumber(String) : Number
```
## 🔑핵심 기술
### 제네릭 타입 매개변수
public static class ArithmeticCalculator<T extends Number>
- T는 Number 클래스 또는 그 하위 클래스
- 컴파일 시점에 타입 안정성 확보
### Switch 표현식
```java
double result = switch (operator) {
    case SUM -> a + b;
    case MINUS -> a - b;
    case MULTIPLY -> a * b;
    case DIVIDE -> {
        if (b == 0) throw new ArithmeticException(...);
        yield a / b;
    }
};
```
### Stream 활용
```java
List<Double> maxValueResult = results.stream()
        .filter(r -> r > lastResult)
        .sorted(Comparator.reverseOrder())
        .toList();
```
## 💻 실행 예시
```
첫 번째 숫자를 입력하세요: 3
두 번째 숫자를 입력하세요: 2
사칙연산 기호를 입력하세요 (+, -, *, /): +
저장된 연산 결과: 5
최근 연산 결과(5)보다 큰 값이 없습니다.
더 계산하시겠습니까? (exit 입력 시 종료): 

첫 번째 숫자를 입력하세요: 3
두 번째 숫자를 입력하세요: 2
사칙연산 기호를 입력하세요 (+, -, *, /): -
저장된 연산 결과: 5, 1
최근 연산 결과(1)보다 큰 값들 (내림차순): 5
연산 결과 중 최댓값: 5
더 계산하시겠습니까? (exit 입력 시 종료): 

첫 번째 숫자를 입력하세요: 3
두 번째 숫자를 입력하세요: 2
사칙연산 기호를 입력하세요 (+, -, *, /): *
저장된 연산 결과: 5, 1, 6
최근 연산 결과(6)보다 큰 값이 없습니다.
더 계산하시겠습니까? (exit 입력 시 종료): 

첫 번째 숫자를 입력하세요: 26
두 번째 숫자를 입력하세요: 14
사칙연산 기호를 입력하세요 (+, -, *, /): /
저장된 연산 결과: 5, 1, 6, 1.85714
최근 연산 결과(1.85714)보다 큰 값들 (내림차순): 6, 5
연산 결과 중 최댓값: 6
더 계산하시겠습니까? (exit 입력 시 종료):
```
## 📚 학습 포인트
- 제네릭의 실제 활용 (타입 제약 조건)
- Stream 숙달
- 함수형 프로그래밍 패러다임
- 예외 처리 및 입출력 처리
- 코드 재사용성 향상
