import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_12764_싸지방에_간_준하_임규리 {

    static class User implements Comparable<User> {
        int start;
        int end;

        public User(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(User o) {
            return this.start - o.start;
        }

        @Override
        public String toString() {
            return "User{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    static class Computer implements Comparable<Computer> {
        int idx;
        int end;

        public Computer(int idx, int end) {
            this.idx = idx;
            this.end = end;
        }

        @Override
        public int compareTo(Computer o) {
            if (this.end == o.end) {
                return this.idx - o.idx;
            }

            return this.end - o.end;
        }

        @Override
        public String toString() {
            return "Computer{" +
                    "idx=" + idx +
                    ", end=" + end +
                    '}';
        }
    }

    static int N;   // 사람 수
    static int P;   // 시작 시간
    static int Q;   // 종료 시간
    static List<Integer> list;  // 사용자 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        list = new ArrayList<>();

        PriorityQueue<User> pqUser = new PriorityQueue<>();
        PriorityQueue<Computer> pqComputerInUse = new PriorityQueue<>();
        PriorityQueue<Integer> pqComputerFree = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            P = parseInt(st.nextToken());
            Q = parseInt(st.nextToken());

            pqUser.add(new User(P, Q));
        }

        int idx = 1;
        pqComputerInUse.add(new Computer(idx++, pqUser.poll().end));
        list.add(1);
        while (!pqUser.isEmpty()) {
            User curUser = pqUser.poll();

            while (!pqComputerInUse.isEmpty() && curUser.start >= pqComputerInUse.peek().end) {
                pqComputerFree.add(pqComputerInUse.poll().idx);
            }

            if (!pqComputerFree.isEmpty()) {
                int tempIdx = pqComputerFree.poll();
                pqComputerInUse.add(new Computer(tempIdx, curUser.end));
                list.set(tempIdx - 1, list.get(tempIdx - 1) + 1);
            } else {
                pqComputerInUse.add(new Computer(idx++, curUser.end));
                list.add(1);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size());
        sb.append(System.lineSeparator());
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
