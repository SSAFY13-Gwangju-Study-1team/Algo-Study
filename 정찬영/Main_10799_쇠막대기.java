/*
 * 체감 난이도: 3/10
 * 알고리즘 분류는 스택인데, 정작 스택의 특성은 활용하지 않고 풀어서 아쉬움
 *
 * 괄호를 이용한 막대기와 레이저 절단 문제 풀이입니다.
 * 1. "("로 시작하면 막대기 개수를 증가시키고, ")"일 경우 직전 기호에 따라 처리합니다.
 * 2. 직전 기호가 "("이면 레이저로 간주해 현재 막대기의 개수를 더하고, 이후 개수를 감소시킵니다.
 * 3. 직전 기호가 ")"이면 막대기가 끝난 것으로 간주해 개수를 감소시키고, 잘린 막대기 하나를 더합니다.
 * 4. 최종적으로 잘린 막대기의 총합을 출력합니다.
 */

import java.io.*;
public class Main_10799_쇠막대기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] S = br.readLine().split("");

        int Cur_stick = 0;  // 현재 남아있는 막대기 수
        int Cut_stick = 0;  // 잘린 막대기의 총합

        for(int s=0; s<S.length; s++) {
            // 여는 괄호일 때
            if(S[s].equals("(")) {
                Cur_stick++;        // 막대기 수 증가
            }

            // 닫는 괄호일 때
            else {
                // 이전 기호가 여는 괄호였다면 레이저이므로
                if(S[s-1].equals("(")) {
                    Cur_stick--;                // 증가했던 막대기 -1
                    Cut_stick += Cur_stick;     // 현재 막대기들은 잘렸으므로 막대기 수만큼 총합에 +
                }
                else {
                    Cur_stick--;    // 막대기가 끝이 났으므로 -1
                    Cut_stick++;    // 잘리고 남은 잔여 막대기가 있으므로 +1
                }
            }
        }
        System.out.print(Cut_stick);
        br.close();
    }
}