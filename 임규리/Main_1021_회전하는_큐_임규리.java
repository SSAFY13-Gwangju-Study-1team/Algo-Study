package 큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_1021_회전하는_큐_임규리 {

    static int N;   // 큐의 크기
    static int M;   // 뽑아내려고 하는 수의 개수
    static int[] target;    // 뽑아내려고 하는 수의 위치
    static int count;       // 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        target = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
           target[i] = Integer.parseInt(st.nextToken());
        }

        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        for (int t : target) {
            int left = q.indexOf(t);    // 왼쪽에서부터 몇 칸인지
            int right = q.size() - left;       // 오른족에서부터 몇 칸인지

            if (left <= right) {    // 왼쪽에서 더 가까울 때
                for (int i = 0; i < left; i++) {
                    q.addLast(q.pollFirst());
                    count++;
                }
            } else {
                for (int i = 0; i < right; i++) {   // 오른쪽에서 더 가까울 때
                    q.addFirst(q.pollLast());
                    count++;
                }
            }

            q.pollFirst();  // 찾던 수가 맨 앞으로 이동해 있을테니 뽑아내기
        }

        System.out.println(count);
    }
}
