import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 102 ms 시간
 * 모래시계 푸는 것 처럼 풀면 되는 쉬운 구현 문제
 */
public class SWEA_2805_농작물수확하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 입력
        int tc = parseInt(br.readLine());
        for(int t = 1;t<=tc;t++) {
            int n = parseInt(br.readLine());
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                char[] temp = br.readLine().toCharArray();
                for (int j = 0; j < n; j++) {
                    map[i][j] = parseInt(String.valueOf(temp[j]));
                }
            }

            // 농장 수확물 마름모 증가 부분 -- like 모래시계 문제
            int index = n / 2;
            int start = index;
            int end = index;
            int sum = 0;
            for (int j = 0; j < n / 2+1; j++) {
                for (int i = start; i <= end; i++) {
                    sum += map[i][j];
                }
                start--;
                end++;
            }
            start++;
            start++;
            end--;
            end--;
            // 농장 수확물 마름모 감소 부분
            for (int j = n / 2 + 1; j < n; j++) {
                for (int i = start; i <= end; i++) {
                    sum += map[i][j];
                }
                start++;
                end--;
            }
            sb.append("#"+t+" ").append(sum).append("\n");
        }
        System.out.println(sb);


    }
}
