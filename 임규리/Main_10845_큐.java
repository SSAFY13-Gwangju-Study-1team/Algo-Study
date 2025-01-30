/**
 * queue는 FIFO 구조이기에 맨 마지막 숫자를 어떻게 구할지 고민함
 * => for문을 순회하며 last 변수를 업데이트해준 후 출력하는 방법 선택
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_10845_큐 {
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String[] command = br.readLine().split(" ");

            switch (command[0]) {
                case "push":
                    push(Integer.parseInt(command[1]));
                    break;
                case "pop":
                    pop();
                    break;
                case "size":
                    size();
                    break;
                case "empty":
                    empty();
                    break;
                case "front":
                    front();
                    break;
                case "back":
                    back();
                    break;
            }

        }
    }

    // queue에 정수 X 넣기
    static void push(int X) {
        queue.add(X);
    }

    // queue의 가장 앞에 있는 정수를 빼고 출력
    // 정수가 없다면 -1 출력
    static void pop() {
        if (queue.isEmpty()) {
            System.out.println("-1");
        } else {
            System.out.println(queue.poll());
        }
    }

    // queue에 들어있는 정수의 개수 출력
    static void size() {
        System.out.println(queue.size());
    }

    // queue가 비어있으면 1, 아니면 0 출력
    static void empty() {
        if (queue.isEmpty()) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    // queue의 가장 앞에 있는 정수 출력
    // 정수가 없다면 -1 출력
    static void front() {
        if (queue.isEmpty()) {
            System.out.println("-1");
        } else {
            System.out.println(queue.peek());
        }
    }

    // queue의 가장 뒤에 있는 정수 출력
    // 정수가 없다면 -1 출력
    static void back() {
        if (queue.isEmpty()) {
            System.out.println("-1");
        } else {
            int last = 0;
            for (int n : queue) {
                last = n;
            }
            System.out.println(last);
        }
    }
}
