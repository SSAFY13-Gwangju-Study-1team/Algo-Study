/**
 * 덱에 대해 공부한 후 풀이하였습니다.
 * 첫 번째 풀이에서 실패 : 1번, 2번 명령의 x값을 charAt(2)로 가져왔기 때문
 * - x는 1 ≤ x ≤ 100,000 인 숫자이기에 한 자리가 아닐 수도 있는데 이를 간과함
 * - 문제를 제대로 읽지 않았던 것 같습니다... ㅜ_ㅜ
 * 명령별로 함수를 구분하였고, switch-case를 사용하였습니다.
 * 출력의 효율을 위해 StringBuilder를 사용하였습니다.
 */

import java.util.*;
import java.io.*;

public class Main_28279_덱2 {
    static ArrayDeque<Integer> deque;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            String[] commands = line.split(" ");
            int command = Integer.parseInt(commands[0]);

            switch (command) {
                case 1:
                    command1(Integer.parseInt(commands[1]));
                    break;
                case 2:
                    command2(Integer.parseInt(commands[1]));
                    break;
                case 3:
                    command3();
                    break;
                case 4:
                    command4();
                    break;
                case 5:
                    command5();
                    break;
                case 6:
                    command6();
                    break;
                case 7:
                    command7();
                    break;
                case 8:
                    command8();
                    break;
            }
        }

        System.out.println(sb);
    }

    // 명령 1
    static void command1(int x) {
        deque.addFirst(x);
    }

    // 명령 2
    static void command2(int x) {
        deque.addLast(x);
    }

    // 명령 3
    static void command3() {
        if (deque.isEmpty()) {
            sb.append("-1");
            sb.append(System.lineSeparator());
        } else {
            sb.append(deque.removeFirst());
            sb.append(System.lineSeparator());
        }
    }

    // 명령 4
    static void command4() {
        if (deque.isEmpty()) {
            sb.append("-1");
            sb.append(System.lineSeparator());
        } else {
            sb.append(deque.removeLast());
            sb.append(System.lineSeparator());
        }
    }

    // 명령 5
    static void command5() {
        sb.append(deque.size());
        sb.append(System.lineSeparator());
    }

    // 명령 6
    static void command6() {
        if (deque.isEmpty()) {
            sb.append("1");
            sb.append(System.lineSeparator());
        } else {
            sb.append("0");
            sb.append(System.lineSeparator());
        }
    }

    // 명령 7
    static void command7() {
        if (deque.isEmpty()) {
            sb.append("-1");
            sb.append(System.lineSeparator());
        } else {
            sb.append(deque.peekFirst());
            sb.append(System.lineSeparator());
        }
    }

    // 명령 8
    static void command8() {
        if (deque.isEmpty()) {
            sb.append("-1");
            sb.append(System.lineSeparator());
        } else {
            sb.append(deque.peekLast());
            sb.append(System.lineSeparator());
        }
    }
}
