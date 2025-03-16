package 스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 피연산자 : 바로 StringBuilder에 추가
 * 연산자 : 우선순위에 따라 스택에 넣고 처리
 * 괄호 : 특별 처리
 *      - 여는 괄호는 스택에 넣기
 *      - 닫는 괄호가 나오면 여는 괄호까지 스택에서 연산자를 꺼내 출력
 * 연산자 우선순위 : * / >> + -
 *      - 우선순위가 더 높은 연산자는 먼저 출력
 */
public class Main_1918_후위_표기식_임규리 {

    static String exp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        exp = br.readLine();

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : exp.toCharArray()) {
            // 문자일 때
            if (Character.isAlphabetic(c)) {
                sb.append(c);
            }
            // 여는 괄호 '(' 일 때
            else if (c == '(') {
                stack.push(c);
            }
            // 닫는 괄호 ')' 일 때
            else if (c == ')') {
                // 여는 괄호가 나올 때까지 스택 내의 연산자 출력
                while(!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }

                stack.pop();    // 여는 괄호 빼주기
            }
            // 연산자일 때
            else {
                // 연산자 우선순위를 비교하여 우선순위가 더 높은 것부터 출력
                while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                    sb.append(stack.pop());
                }

                stack.push(c);
            }
        }

        // 스택에 남아있는 연산자 처리
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }

    private static int priority(char c) {
        if (c == '*' || c == '/') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return 0;
        }
    }
}
