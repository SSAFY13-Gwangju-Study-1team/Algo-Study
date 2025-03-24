package 슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 1 -> 시간 초과
 * - StringBuilder 사용 : O(P)
 * - 비밀번호 조건 확인 함수 사용 : O(P)
 * 풀이 2 -> 성공
 * - String을 만들지 않고 바로 int[]로 카운트
 * - 슬라이딩 윈도우 적용
 */
public class Main_12891_DNA_비밀번호_임규리 {

    static int S;       // DNA 문자열 길이
    static int P;       // 부분문자열 길이
    static char[] arr;  // DNA 문자열 배열
    static int[] need;  // 부분문자열에 포함되어야 할 {‘A’, ‘C’, ‘G’, ‘T’}의 최소 개수
    static int[] cur;   // 현재 카운팅된 {‘A’, ‘C’, ‘G’, ‘T’}의 개수
    static int count;   // 비밀번호 종류의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        arr = br.readLine().toCharArray();
        need = new int[4];
        cur = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            need[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 설정
        for (int i = 0; i < P; i++) {
            addChar(arr[i]);
        }
        if (isPassword()) count++;  // 조건 만족 시 카운트

        for (int i = P; i < S; i++) {   // P부터 끝까지 탐색
            addChar(arr[i]);    // 현재 문자 추가
            removeChar(arr[i - P]); // 맨 앞 문자 삭제

            if (isPassword()) count++;  // 조건 만족 시 카운트
        }

        System.out.println(count);
    }

    // 문자 추가
    private static void addChar(char c) {
        if (c == 'A') cur[0]++;
        else if (c == 'C') cur[1]++;
        else if (c == 'G') cur[2]++;
        else cur[3]++;
    }

    // 문자 삭제
    private static void removeChar(char c) {
        if (c == 'A') cur[0]--;
        else if (c == 'C') cur[1]--;
        else if (c == 'G') cur[2]--;
        else cur[3]--;
    }

    // 비밀번호 조건 확인
    private static boolean isPassword() {
        for (int i = 0; i < 4; i++) {
            if (cur[i] < need[i]) return false;
        }

        return true;
    }
}
