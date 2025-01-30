/**
 * Stack 활용
 * Stack을 하나만 활용하려고 해보았습니다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_12605_단어순서_뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Stack<String> stack = new Stack<>();


        for (int i = 0; i < N; i++) {
            stack.clear();

            for (String s : br.readLine().split(" ")) {
                stack.push(s);
            }

            sb.append("Case #" + (i + 1) + ": ");

            while (!stack.isEmpty()) {
                sb.append(stack.pop() + " ");
            }

            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
        br.close();
    }
}
