package 스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1935_후위_표기식2_임규리 {

    static int N;       // 피연산자 개수
    static int[] arr;   // 피연산자에 대응하는 값
    static String exp;  // 식

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        exp = br.readLine();

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (char c : exp.toCharArray()) {
            // 문자면 대응하는 값을 스택에 추가 (double로 계산해야 하니 1.0 곱해줌)
            if (Character.isAlphabetic(c)) {
                stack.push(arr[c - 'A'] * 1.0);
            }
            // 연산자면 계산 처리
            else {
                switch(c) {
                    // 더하기 연산자 -> 스택 맨 위 두 값을 더해서 다시 넣어줌
                    case '+' :
                        stack.push(stack.pop() + stack.pop());
                        break;
                    // 빼기 연산자 -> 스택 맨 위 두 값을 빼서 다시 넣어줌 (순서가 반대니까 -1 곱해줌)
                    case '-' :
                       stack.push(-1 * (stack.pop() - stack.pop()));
                       break;
                    // 곱하기 연산자 -> 스택 맨 위 두 값을 곱해서 다시 넣어줌
                    case '*' :
                        stack.push(stack.pop() * stack.pop());
                        break;
                    // 나누기 연산자 -> 스택 맨 위 두 값을 나눠서 다시 넣어줌 (순서가 반대니까 1을 계산 값으로 나눈 값을 넣어주기)
                    case '/' :
                        stack.push(1 / (stack.pop() / stack.pop()));
                        break;
                }
            }
        }

        // 소숫점 둘째 자리까지 출력
        System.out.printf("%.2f", stack.pop());
    }
}
