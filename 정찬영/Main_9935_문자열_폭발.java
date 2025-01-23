/*
 * 체감 난이도 7/10, GPT 사용...
 * 1차 시도 - 폭발문자열 제거 과정에서 substring 사용 = 메모리 초과
 * 2차 시도 - 폭발문자열 발견 과정에서 임시 StringBuilder 생성 - 메모리 초과
 * 3차 시도 - StringBuilder를 사용하지 않고 charAt으로 비교 - GPT 제안: "계속 메모리 초과되는데 그냥 StringBuilder 쓰지마"
 * - charAt을 이용한 방법으로 문제 해결
 * 
 * 괜히 StringBuilder를 이용해서 풀이해야겠다고 생각하고 너무 돌아간 느낌
 * charAt을 이용한 방법은 처음에도 생각했으나 StringBuilder로 풀이하면 코드가 더 깔끔할 것이라고 생각함
 * 하지만 매번 StringBuilder를 생성하는데 소요되는 메모리 비용을 간과함
 * 때로는 단순한 방법이 더 효율적인 것 같습니다.
 *
 * StringBuilder의 isEmpty()는 자바11버전에서 지원하지 않음, Java11로 제출 시 컴파일 에러
 * 백준에서 언어 설정 > 숨길 언어의 Java15로 설정하여 제출 시에는 가능 (경험으로 얻은 지식......)
 */

import java.io.*;
import java.util.*;

public class Main_9935_문자열_폭발 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        StringBuilder S = new StringBuilder(br.readLine()); // 문자열, 수시로 바뀌므로 StringBuilder
        String bomb = br.readLine();  // 폭탄
        int bombLen = bomb.length();  // 폭탄길이, 반복문에서 지속적으로 길이를 조회하기 때문에 조금이라도 절약하려고 선언

        StringBuilder ans = new StringBuilder();
        for(int i=0; i<S.length(); i++) {
            ans.append(S.charAt(i));    // 정답 문자열, 문제 문자열의 글자 한 글자씩 추가

            while(ans.length() >= bombLen) {    // 정답 문자열 길이가 폭탄 길이 이상이면
                boolean isBomb = true;          // 폭탄 확인 변수
                for(int j = 0; j<bombLen; j++) {
                    if(ans.charAt(ans.length() - bombLen + j) != bomb.charAt(j)) {  // 정답 문자열 끝에서 폭탄 길이만큼 확인
                        isBomb = false; // 아니면 확인 변수 false
                        break;
                    }
                }
                if(isBomb) {    // 폭탄이 있으면 폭탄 삭제하고 while문 반복 다시 시작
                    ans.setLength(ans.length() - bombLen);
                }
                else    // 없으면 탈출
                    break;
            }
        }


        // 출력
        //if(ans.isEmpty()) // Java 15에서 가능 
        if(ans.length() == 0)   // Java 11에서 사용
            System.out.print("FRULA");
        else
            System.out.print(ans);

        br.close();
        bw.close();
    }
}