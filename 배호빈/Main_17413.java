
import java.util.*;
import java.io.*;

public class Main_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (c == '<') {
                if (!deque.isEmpty()) {  // 태그들 사이에 있는 문자열을 출력하기 한다.
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollLast()); // deque에 저장된 문자열을 뒤에서부터 출력한다.
                    }
                    System.out.print(sb);
                    sb.setLength(0);
                }

                while (c != '>') { // c가 '<'일때 '>'가 나올 때까지 반복해서 deque에 추가한다.
                    c = S.charAt(i++);
                    deque.addLast(c);
                }
                i--; // i를 다시 '>'의 인덱스 값으로 만들어 다음 문자를 건너뛰지 않도록 만들어줌

                while (!deque.isEmpty()) { // 태그가 다 만들어졌으면 순서대로 출력해준다.
                    sb.append(deque.pollFirst());
                }
                System.out.print(sb);
                sb.setLength(0);

            } else if (c == ' ') { // 문자열 사이에 공백이 있으면 공백 전 문자열을 뒤집어 준다.
                while (!deque.isEmpty()) {
                    sb.append(deque.pollLast());
                }
                System.out.print(sb);
                System.out.print(" ");
                sb.setLength(0);
            } else {
                deque.addLast(c);
            }

        }

        if (!deque.isEmpty()) { // 마지막 출력되지 않은 deque에 남아 있는 문자열을 뒤집어서 출력한다.
            while (!deque.isEmpty()) {
                sb.append(deque.pollLast());
            }
            System.out.print(sb);
        }
    }
}
