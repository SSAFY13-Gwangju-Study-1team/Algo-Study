/*
 * 체감 난이도 1/10
 * 스택의 후입선출 특성을 이용한 문제인데, split을 이용하여 분리하고 배열에 저장되었기 때문에
 * 스택이 아니라 배열의 인덱스를 역순으로 조회하는 식으로 풀이했습니다.
 *
 * 1. 각 테스트 케이스마다 문장을 공백 기준으로 단어로 나누어 배열에 저장합니다.
 * 2. 저장된 단어 배열을 역순으로 출력합니다.
 */

import java.io.*;
public class Main_12605_단어순서뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 1; n <= N; n++) {
            String[] S = br.readLine().split(" "); // 문장을 공백 기준으로 단어 배열로 분리

            System.out.print("Case #" + n + ": ");
            for (int i = S.length - 1; i >= 0; i--) { // 단어 배열을 역순으로 순회
                System.out.print(S[i] + " ");         // 단어를 순서대로 출력
            }
            System.out.println();
        }
        br.close();
    }
}