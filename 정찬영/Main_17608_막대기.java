/*
 * 체감 난이도 1/10
 * 간만에 숨 좀 돌리는 문제였습니다...
 * 역시나 LIFO 문제
 */

import java.io.*;
import java.util.*;

public class Main_17608_막대기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 막대기의 수 N
        int[] arrN = new int[N]; // 막대기 높이를 저장할 배열

        // 막대기 높이 저장
        for (int n = 0; n < N; n++)
            arrN[n] = Integer.parseInt(br.readLine());

        int cur = arrN[N - 1]; // 마지막으로 보인 막대기, 처음에는 맨 뒤의 막대기
        int ans = 1;           // 맨 뒤는 항상 보이므로 초기 값 1

        // 끝에서 두 번째 막대기부터 확인, 맨 뒤는 이미 보이므로 포함
        for (int n = N - 2; n >= 0; n--) {
            if (arrN[n] > cur) { // 현재 막대기가 이전에 보인 막대기보다 높다면 보이는 막대기
                ans++;           // 보이는 막대기의 개수 증가
                cur = arrN[n];   // 현재 막대기를 보인 막대기로 설정
            }
        }

        System.out.print(ans);
        br.close();
    }
}
