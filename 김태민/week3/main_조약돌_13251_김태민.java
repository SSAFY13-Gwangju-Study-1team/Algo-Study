import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 색상의 개수 M 입력
        int M = Integer.parseInt(br.readLine());

        // 색상별 조약돌 개수 배열
        int[] stones = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }

        // 뽑을 개수 K 입력
        int K = Integer.parseInt(br.readLine());

        // 전체 조약돌 개수 구하기
        int totalStones = 0;
        for (int stone : stones) {
            totalStones += stone;
        }

        // 확률 계산
        double probability = 0;
        for (int i = 0; i < M; i++) {
            if (stones[i] >= K) { // K개 이상 있어야 선택 가능
                double prob = 1;
                for (int j = 0; j < K; j++) {
                    prob *= (double) (stones[i] - j) / (totalStones - j);
                }
                probability += prob;
            }
        }

        System.out.println(probability);
    }
}
