import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 18,328 KB
 * 시간 : 216 ms
 * -----------------------------------------------------------
 * 문제 이해를 못해서 G쌤 도움 받앗으요 ^_^...
 * 1. 각 열별로 A, C, G, T의 등장 횟수 세기
 * 2. 가장 많이 등장한 문자를 sb.append()
 * 3. 전체 N에서 가장 많이 등장한 횟수를 뺀 값을 hamming distance에 더함
 */
public class Main_1969_DNA_임규리 {

    static int N;   // DNA의 수
    static int M;   // 문자열의 길이
    static String[] dna;    // 문자열 배열
    static int[] count;     // 문자 등장 횟수 카운팅
    static int hamming;     // Hamming Distance

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        dna = new String[N];
        for (int i = 0; i < N; i++) {
            dna[i] = br.readLine();
        }

        count = new int[4]; // A, C, G, T 4가지
        for (int i = 0; i < M; i++) {
            Arrays.fill(count, 0);
            for (int j = 0; j < N; j++) {
                if (dna[j].charAt(i) == 'A') count[0]++;
                if (dna[j].charAt(i) == 'C') count[1]++;
                if (dna[j].charAt(i) == 'G') count[2]++;
                if (dna[j].charAt(i) == 'T') count[3]++;
            }

            int idx = -1;
            int max = Arrays.stream(count).max().getAsInt();
            hamming += N - max;
            for (int j = 0; j < 4; j++) {
                if (count[j] == max) {
                    idx = j;
                    break;
                }
            }

            if (idx == 0) sb.append("A");
            if (idx == 1) sb.append("C");
            if (idx == 2) sb.append("G");
            if (idx == 3) sb.append("T");
        }

        System.out.println(sb);
        System.out.println(hamming);
    }
}
