package 스터디;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어 : 슬라이딩 윈도우
 * 메모리 : 38220 KB
 * 시간 : 256 ms
 */
public class Main_21921_블로그_임규리 {

    static int N;   // 전체 일수
    static int X;   // 기간
    static int[] people;    // 방문자 수
    static int max; // 최대값
    static int count;   // 최대값 등장 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        X = parseInt(st.nextToken());
        people = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            people[i] = parseInt(st.nextToken());
        }

        // 초기값
        int temp = 0;
        for (int i = 0; i < X; i++) {
            temp += people[i];
        }
        max = Math.max(max, temp);
        count = 1;

        // 슬라이딩 윈도우
        for (int i = 1; i < N - X + 1; i++) {
            temp -= people[i - 1];      // 앞의 수 하나 빼기
            temp += people[i + X - 1];  // 뒤의 수 하나 빼기

            if (temp == max) {  // 현재 값과 최대값이 같다면
                count++;    // 카운팅
            } else if (temp > max) {    // 현재 값이 최대값보다 크다면
                max = temp; // 최대값 업데이트
                count = 1;  // 카운팅 초기화
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(count);
        }


    }
}
