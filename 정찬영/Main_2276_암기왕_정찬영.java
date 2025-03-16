/* 메모리 228048kb, 시간 1800ms
 * 문제 풀이 아이디어: 이진 탐색을 이용한다.
 * 체감 난이도: 4/10
 */

import java.io.*;
import java.util.*;

public class Main_2276_암기왕_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            input();
            solve();
        }
        output();
    }

    /* ----- 입력 ----- */
    static int N;
    static int M;
    static int[] note1;
    static int[] note2;
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());    // 수첩 1에 적어 놓은 정수 개수

        note1 = new int[N]; // 수첩 1
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            note1[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());    // 수첩 1에 적어 놓은 정수 개수

        note2 = new int[M]; // 수첩 2
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            note2[i] = Integer.parseInt(st.nextToken());
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        Arrays.sort(note1);

        // 노트2에 적힌 숫자들을 하나씩 이진탐색하여 확인
        for(int i : note2) {
            sb.append(binarySearch(i)).append("\n");
        }
    }

    // 이진 탐색 함수
    static int binarySearch (int target) {
        int left = 0, right = note1.length -1;  // 좌우인덱스

        while (left <= right) { // 중간 값 계산
            int mid = left + (right - left) / 2;    // 오버플로우 방지를 위해 (left+right)/2를 다른 식으로 변환

            // 찾은 경우
            if (note1[mid] == target) {
                return 1;
            }
            // 중간 값이 목표 값보다 작음 = 오른쪽 탐색
            else if (note1[mid] < target) {
                left = mid+1;
            }
            // 중간 값이 목표 값보다 큼 = 왼쪽 탐색
            else right = mid-1;
        }
        return 0;
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}