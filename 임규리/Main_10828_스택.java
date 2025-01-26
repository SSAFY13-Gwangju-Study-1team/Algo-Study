/**
 * 문제의 의도가 이게 아닌 것 같지만 Stack을 활용하여 풀이하였습니다.
 * 중간에 이상한 오류가 있어서 해결하느라 좀 걸렸지만, 왠지 똑같은 코드로 다시 시도해보니 성공했습니당...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_10828_스택 {
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String command = br.readLine();

            if (command.startsWith("push")) {
                int X = Integer.parseInt(command.substring(5));
                push(X);
            } else if (command.startsWith("pop")) {
                pop();
            } else if (command.startsWith("size")) {
                size();
            } else if (command.startsWith("empty")) {
                empty();
            } else {
                top();
            }
        }

        br.close();
    }

    // push X
    static void push(int X) {
        stack.push(X);
    }

    // pop
    static void pop() {
        if (stack.isEmpty()) {
            System.out.println("-1");
        } else {
            System.out.println(stack.pop());
        }
    }

    // size
    static void size() {
        System.out.println(stack.size());
    }

    // empty
    static void empty() {
        if (stack.isEmpty()) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    // top
    static void top() {
        if (stack.isEmpty()) {
            System.out.println("-1");
        } else {
            System.out.println(stack.peek());
        }
    }
}
