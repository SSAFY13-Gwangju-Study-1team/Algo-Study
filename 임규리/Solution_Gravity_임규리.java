package 배열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution_Gravity_임규리 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            int maxLength = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                maxLength = Math.max(maxLength, arr[i]);    // 가장 높게 쌓인 상자의 수 구하기
            }

            int[] count = new int[maxLength + 1];   // 낙차 카운팅 배열, 인덱스 0은 사용 X
            int max = arr[0];

            for (int i = 1; i < n; i++) {
                if (max < arr[i]) {
                    max = arr[i];
                } else {
                    // 현재 max가 7이고 arr[i]가 4라면 -> 5, 6, 7번 칸에 낙차 카운팅
                    for (int j = arr[i] + 1; j <= max; j++) {
                        count[j]++;
                    }
                }
            }

            System.out.println("#" + test_case + " " + Arrays.stream(count).max().getAsInt());
        }
    }
}