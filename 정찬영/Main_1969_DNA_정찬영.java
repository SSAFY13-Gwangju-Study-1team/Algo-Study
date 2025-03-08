/* 메모리 14476kb, 시간 116ms
 * 문제 풀이 아이디어: 그리디를 이용한다.
 * 한 글자씩 모든 DNA의 해당 위치를 확인하여 각 글자의 수를 세고 가장 많은 것을 선택한다.
 * 선택되지 못한 나머지는 모두 Hamming Distance 값으로 추가한다.
 * 체감 난이도: 7/10 -> 문제는 쉬운데 문제를 어렵게 써놔서 이해가 더 힘들었다
 */

import java.io.*;
import java.util.*;

public class Main_1969_DNA_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int N, M;
    static char[][] dna;
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // DNA 수
        M = Integer.parseInt(st.nextToken());   // 문자열 길이

        dna = new char[N][M];   // DNA 종류
        for(int i=0; i<N; i++) {
            dna[i] = br.readLine().toCharArray();
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        char s; // 구하는 dna
        int minHD = 0;

        for(int i=0; i<M; i++) {    // 구하는 dna의 한 글자씩 채우기
            int T = 0, A = 0, C = 0, G = 0;
            for(int j=0; j<N; j++) {
                switch (dna[j][i]) { // 각 DNA의 i번째 글자 확인
                    case 'T': T++; break;
                    case 'A': A++; break;
                    case 'C': C++; break;
                    case 'G': G++; break;
                }
            }

            // 최대 빈도를 가진 뉴클레오타이드 찾기 (if문 순서에 따른 사전순)
            if (A >= C && A >= G && A >= T) {
                s = 'A';
                minHD += T + C + G; // 선택된 글자 빼고 나머지는 다 HD임
            } else if (C >= G && C >= T) {
                s = 'C';
                minHD += A + T + G;
            } else if (G >= T) {
                s = 'G';
                minHD += A + C + T;
            } else {
                s = 'T';
                minHD += A + C + G;
            }

            sb.append(s);   // 스트링빌더에 결정된 i번째 글자 입력
        }
        sb.append("\n").append(minHD);  // 마지막으로 HD 수 입력
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}