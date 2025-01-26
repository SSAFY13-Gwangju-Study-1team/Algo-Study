/**
 * 5개의 문자열을 받아와서, 제일 긴 길이를 구했습니다.
 * maxLength보다 짧은 문자열의 뒤에는 " "(공백)을 추가하였습니다.
 * 이중 for문을 돌며 " "(공백)이 아닌 것들만 세로로 읽어내 StringBuilder에 추가하였습니다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10798_세로읽기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = 5;
        int maxLength = 0;
        String[] lines = new String[N];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            lines[i] = br.readLine();
            maxLength = Math.max(maxLength, lines[i].length());
        }

        for (int i = 0; i < N; i++) {
            if (lines[i].length() < maxLength) {
                lines[i] = lines[i] + " ".repeat(maxLength - lines[i].length());
            }
        }

        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < N; j++) {
                if (lines[j].charAt(i) != ' ') {
                    sb.append(lines[j].charAt(i));
                }
            }
        }

        System.out.println(sb);

        br.close();
    }
}
