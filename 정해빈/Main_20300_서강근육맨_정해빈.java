package algo_study.week07;
import java.io.*;
import java.util.*;
public class Main_20300_서강근육맨_정해빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 기구 개수

        long[] loss = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            loss[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(loss); // 정렬

        long maxLoss = 0;

        if (N % 2 == 1) { // N이 홀수라면, 가장 큰 값은 단독 사용
            maxLoss = loss[N - 1]; // 마지막 값은 단독 사용
            N--; // 한 개를 단독으로 사용했으므로 나머지는 짝 맞춰서 처리
        }

        // 투 포인터 방식으로 가장 작은 값 + 가장 큰 값 짝지어 계산
        for (int i = 0; i < N / 2; i++) {
            maxLoss = Math.max(maxLoss, loss[i] + loss[N - 1 - i]);
        }

        System.out.println(maxLoss);
    }
}