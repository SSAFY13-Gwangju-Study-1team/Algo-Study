/*
 * 백준 2504 괄호의 값 문제
 * 개인 체감 난이도 9/10
 * 개인적으로 아주 어려웠던 문제였습니다...
 * 스택을 사용하지 않았지만 배열을 역순으로 조회하여 LIFO(후입선출)과 같은 효과를 구현했습니다.
 * 역순으로 조회하면서 괄호가 닫히는 조건을 확인하고, 괄호가 올바르게 쌍을 이루면 현재 누적 값을 계산하여 총합에 더합니다.
 * 괄호의 종류에 따라 곱셈이 다르고, 괄호가 올바르게 닫힌 경우에만 값을 누적합니다.
 * 예시: )]] = 3 * 3 * 2 (대괄호는 3배수, 소괄호는 2배수로 계산)
 * ] 다음에 [가 나오거나 ) 다음에 (가 나왔다면 잘못된 괄호로 간주하고 계산을 종료합니다.
 */

import java.util.*;
import java.io.*;

public class Main_2504_괄호의_값 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        // 입력된 괄호 문자열을 문자 배열로 분리
        String[] S = br.readLine().split("");

        int small = 0;  // 닫히지 않은 소괄호 개수 (여는 괄호 "(" 갯수 추적)
        int big = 0;    // 닫히지 않은 대괄호 개수 (여는 괄호 "[" 갯수 추적)

        int ans = 0;    // 최종 계산된 값
        int multi = 1;  // 곱셈 누적 변수 (괄호마다 2배, 3배를 곱함)

        String cur = "";   // 현재 확인 중인 괄호 문자
        String last = "";  // 마지막으로 확인한 괄호 문자
        boolean check = false;  // 잘못된 괄호가 있는지 확인하는 플래그

        for(int i = S.length-1; i>=0; i--) {    // 괄호 문자열을 역순으로 조회
            cur = S[i];

            /*------ 현재 닫는 괄호일 경우 ------*/
            if(cur.equals("]")) {
                multi *= 3;     // 대괄호는 3배수로 계산
                big++;
            }
            else if(cur.equals(")")) {
                multi *= 2;     // 소괄호는 2배수로 계산
                small++;
            }

            /*------ 현재 여는 괄호일 경우 ------*/
            // 대괄호
            else if(cur.equals("[")) {
                big--;                      // 열린 대괄호 수 하나 감소
                multi /= 3;                 // 이전에 곱해진 3배수를 되돌리기
                if(last.equals("]")) {      // []으로 정상적으로 괄호가 닫혔다면
                    ans += (multi * 3);     // 괄호가 정상적으로 닫혔을 때 값 추가
                }
                else if(last.equals(")")) { // [)로 비정상적인 괄호라면
                    check = true;           // 잘못된 괄호 발견
                    break;                  // 바로 종료
                }
            }
            // 소괄호
            else if(cur.equals("(")) {
                small--;                    // 열린 소괄호 수 하나 감소
                multi /= 2;                 // 이전에 곱해진 2배수를 되돌리기
                if(last.equals("]")) {      // (]로 비정상적인 괄호라면
                    check = true;           // 잘못된 괄호 발견
                    break;                  // 바로 종료
                }
                else if(last.equals(")")) { // ()로 정상적으로 괄호가 닫혔다면
                    ans += (multi * 2);     // 괄호가 정상적으로 닫혔을 때 값 추가
                }
            }

            // 모든 괄호가 닫힌 상태라면 곱셈 초기화
            if(big == 0 && small == 0) {
                multi = 1;  // 곱셈 값 초기화
            }
            last = cur;  // 마지막 확인한 괄호를 업데이트

            // 여는 괄호보다 닫는 괄호가 많으면 잘못된 괄호로 처리
            if(big < 0 || small < 0) {
                check = true;   // 잘못된 괄호 발견
                break;          // 바로 종료
            }
        }

        // 모든 괄호를 검사한 후 여는 괄호가 남아있으면 잘못된 괄호
        if(big != 0 || small != 0) {
            check = true;   // 잘못된 괄호 발견
        }

        // 잘못된 괄호가 있었다면 0을 출력
        if(check) {
            System.out.print(0);
        }
        else {
            // 정상적으로 괄호가 닫혔다면 계산된 총합 출력
            System.out.println(ans);
        }
        br.close();
    }
}
