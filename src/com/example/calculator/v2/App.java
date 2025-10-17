package com.example.calculator.v2;

import com.example.calculator.v1.Calculator;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        com.example.calculator.v1.Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        //얘를 세터로 빼버림
        //소수점 뒷자리 0 제거, 최대 6자리
        //원래 반복문에 있었는데 밖에 빼도 잘 작동함
        DecimalFormat df = new DecimalFormat(calculator.getDecimalFormatPattern());

        while (true) {
            try {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                int num1 = Integer.parseInt(scanner.nextLine());

                System.out.print("두 번째 숫자를 입력하세요: ");
                int num2 = Integer.parseInt(scanner.nextLine());

                System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
                String operator = scanner.nextLine();

                double result = calculator.calculate(num1, num2, operator);
                calculator.removeRequest(result);


                System.out.print("저장된 연산 결과: ");
                List<Double> results = calculator.getResults();

                for(int i=0; i<results.size(); i++){
                    double r = results.get(i);
                    //정수면 정수로 출력, 소수면 소수점으로 출력
                    if(r%1==0){
                        System.out.print((int)r);
                    }else{
                        System.out.print(df.format(r));
                    }
                    //마지막 값이 아니면 쉼표 추가
                    if (i < results.size() - 1) {
                        System.out.print(", ");
                    }
                }
                //줄바꿈 해줘야 더 계산하시겠습니까가 안 붙어서 나옴
                System.out.println();


            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 정확히 입력해주세요.");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 연산 기호를 정확히 입력해주세요.");
                continue;
            } catch (ArithmeticException e) {
                System.out.println("[ERROR] " + e.getMessage());
                continue;
            }

            // 계산 끝난 후 종료 여부 묻기
            //println으로하니 enter입력하면서 너무 간격이 멀어져 print로 작성
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료, 아니면 Enter): ");
            String exitInput = scanner.nextLine();
            if (exitInput.equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            System.out.println(); // 다음 계산을 위한 줄바꿈
        }
    }
}
