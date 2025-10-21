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
// 계산기 생성 (Number 타입) -> Integer/Double 모두 가능
ArithmeticCalculator<Number> calc = new ArithmeticCalculator<>();

// 계산 수행
Number result1 = calc.calculate(10, 5, "+");  // 15
Number result2 = calc.calculate(5.0, 2, "*"); // 10.0
Number result3 = calc.calculate(6.0, 2.0, "/"); // 3

// 결과 조회
List<Number> allResults = calc.getResults();  // [15, 10.0, 3]

// 결과 출력
allResults.forEach(r -> System.out.println("결과: " + r));

// 통계 기능
calc.maxValue();              
calc.maxValueResultsDesc();
```
💡 포인트
- 제네릭은 Number로 잡아 정수/실수 모두 처리 가능
- calculate() 반환 타입도 Number로 바뀌었음
- 통계 기능(maxValue, maxValueResultsDesc)은 동일하게 사용 가능
## 🏗️ 프로젝트 구조
```
com/example/calculator/v2/
├── Calculator.java
│   └── ArithmeticCalculator<T extends Number>
│       ├── calculate(T num1, T num2, String operatorSymbol) : Number
│       ├── maxValue() : void
│       ├── maxValueResultsDesc() : void
│       ├── getResults() : List<Number>
│       ├── (private) calculateInteger(int a, int b, OperatorType operator) : Number
│       ├── (private) calculateDouble(double a, double b, OperatorType operator) : Number
│       ├── (private) saveResult(double result) : Number
│       └── (private) formatNumber(Number num) : String
│
├── OperatorType.java
│   └── enum OperatorType { SUM, MINUS, MULTIPLY, DIVIDE }
│       └── fromSymbol(String symbol) : OperatorType
│
└── App.java
    ├── main(String[] args)
    ├── parseNumber(String input) : Number
    └── printResults(List<Number> results) : void
```
## 🔑클래스/메서드 요약 
### 1️⃣ ArithmeticCalculator<T extends Number>
### 주요 메서드
- calculate(T num1, T num2, String operatorSymbol) : 계산 수행
- maxValue() : 최근 결과보다 큰 값 중 최댓값 출력
- maxValueResultsDesc() : 최근 결과보다 큰 값 내림차순 출력
- getResults() : 저장된 결과 반환
### 내부(private) 메서드
- calculateInteger(int a, int b, OperatorType operator) : 정수 연산 처리
- calculateDouble(double a, double b, OperatorType operator) : 실수 연산 처리
- saveResult(double result) : Integer/Double 판단 후 저장
- formatNumber(Number num) : 포맷 처리
### 2️⃣ OperatorType (enum)
- SUM, MINUS, MULTIPLY, DIVIDE
- fromSymbol(String symbol) : 문자열 → enum 변환
### 3️⃣ App
- 사용자 입출력 처리
- main(), parseNumber(), printResults()
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
List<Number> maxValueResult = results.stream()
        .filter(r -> r.doubleValue() > lastResult.doubleValue())
        .sorted(Comparator.comparingDouble(Number::doubleValue).reversed())
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
