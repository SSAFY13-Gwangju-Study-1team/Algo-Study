/* 메모리 35728kb, 시간 316ms
 * 문제 풀이 아이디어: 슬라이딩 윈도우를 활용
 * 첫 창문을 만든 뒤, 한 칸씩 이동하면서 전 값 제거 + 다음 값 추가
 * 체감 난이도: 3/10
 */

import java.util.*;
import java.io.*;

public class Main_21921_블로그_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 블로그 시작하고 지난 일 수
        int X = Integer.parseInt(st.nextToken());   // 확인 기간

        int[] arrN = new int[N];    // 날짜별 방문 수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrN[i] = Integer.parseInt(st.nextToken());
        }

        int window = 0; // 구간 합

        // 첫 창문 만들기
        for (int i = 0; i < X; i++) {
            window += arrN[i];
        }

        int max = 0;    // 가장 많이 들어온 방문자 수
        max = Math.max(max, window);
        int maxNum = 1; // 가장 많이 들어온 방문자 수 기간 갯수
        int last = 0;   // 마지막 창문의 첫 인덱스

        // 슬라이딩 윈도우
        for (int i = X; i < N; i++) {
            window += arrN[i];      // 다음 값 추가
            window -= arrN[last];   // 전 값 제거
            last++;                 // 인덱스 저장

            if(window > max) {  // 현재 창문의 값이 누적 합보다 크면
                max = window;   // 업데이트
                maxNum = 1;     // 기간 갯수 1로 초기화
            } else if (window == max) { // 현재 창문의 값이 누적 합과 같으면
                maxNum++;   // 기간 갯수 + 1
            }
        }

        if(max == 0) {
            bw.append("SAD");
        } else {
            bw.append(Integer.toString(max)).append("\n");
            bw.append(Integer.toString(maxNum));
        }

        bw.flush();
        bw.close();
        br.close();
    }	// End of Main
}
