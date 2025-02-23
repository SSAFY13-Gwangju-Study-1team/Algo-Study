package 스터디;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 힌트 받아서 풀이
 * 아이디어 : 투 포인터
 * 메모리 : 305840 KB
 * 시간 : 900 ms
 */
public class Main_4158_CD_임규리 {

    static int N, M;    // 상근이, 선영이 CD 개수
    static int[] arr1;  // 상근이 배열
    static int[] arr2;  // 선영이 배열
    static int point1;  // 상근이 포인터
    static int point2;  // 선영이 포인터
    static int count;   // 결과값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = parseInt(st.nextToken());
            M = parseInt(st.nextToken());

            if (N == 0 && M == 0) break;    // 종료 조건

            arr1 = new int[N];
            arr2 = new int[M];
            point1 = 0;
            point2 = 0;
            count = 0;

            for (int i = 0; i < N; i++) {
                arr1[i] = parseInt(br.readLine());
            }

            for (int i = 0; i < M; i++) {
                arr2[i] = parseInt(br.readLine());
            }

            while (point1 != N && point2 != M) {    // point1이나 point2가 끝까지 가면 종료

                if (arr1[point1] == arr2[point2]) { // 두 값이 같으면 카운팅 후 포인터 이동
                    count++;
                    point1++;
                    point2++;
                } else if (arr1[point1] < arr2[point2]) {   // 상근이 값이 작으면 상근이 포인터만 이동
                    point1++;
                } else if (arr1[point1] > arr2[point2]) {   // 선영이 값이 작으면 선영이 포인터만 이동
                    point2++;
                }
            }

            System.out.println(count);
        }
    }
}
