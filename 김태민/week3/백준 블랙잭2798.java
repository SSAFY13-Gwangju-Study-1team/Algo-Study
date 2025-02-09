package argoStudy;

import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] num;
    static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        // 백트래킹: 시작 인덱스 0, 합 0, 선택한 카드 갯수 0
        backtrack(0, 0, 0);
        System.out.println(maxSum);
    }

    // start: 다음에 선택할 카드의 시작 인덱스
    // start 인덱스를 해야 중복 조합을 피할 수 있다.
    
    public static void backtrack(int start, int sum, int cnt) {
        if (sum > m) return;
        if (cnt == 3) {
            maxSum = Math.max(maxSum, sum);
            return;
        }
        for (int i = start; i < n; i++) {
            backtrack(i + 1, sum + num[i], cnt + 1);
        }
    }
}
