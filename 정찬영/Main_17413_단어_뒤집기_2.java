/*
* Stack을 이용한 풀이입니다.
* 1. <>의 외부(거꾸로 출력)인지 내부(그대로 출력)인지 확인할 boolean 변수를 두었습니다.
* 2. 문자열의 길이만큼 반복문에서 charAt을 이용하여 한 글자씩 조회
* 3. 공백 또는 <가 나올 때까지 스택에 조회된 글자를 저장
* 4. 공백이 조회되었을 시 스택에 저장된 글자들을 모두 출력
* 5. <가 조회되었을 시 스택에 저장된 글자들을 모두 출력, check 변수 true로 변경
* 6. >가 나올 때까지 조회된 글자들을 그대로 출력, >가 나올 시 check 변수 false로 변경
* 7. 문자열의 마지막 글자까지 조회했을 시 최종적으로 저장된 스택 글자들을 모두 출력
*
* 첫 코드 리뷰라, 주석이 과해서 보기 힘들다거나 피드백 있으시면 말해주세요!,,
*/

import java.io.*;
import java.util.*;

public class Main_17413_단어_뒤집기_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        Stack<Character> stack = new Stack<>();
        boolean check = false;      // <> 확인용

        for(int i=0; i<S.length(); i++) {
            char s = S.charAt(i);   // 현재 글자 조회

            if(!check) {    // <> 외부
                // 현재 글자가 공백인 경우 스택의 글자를 모두 출력
                if(s == ' ') {
                    while(!stack.isEmpty())
                        System.out.print(stack.pop());
                    System.out.print(" ");
                }
                // <가 나온 경우 스택의 글자를 모두 출력, check 활성화
                else if(s == '<') {
                    while(!stack.isEmpty())
                        System.out.print(stack.pop());
                    check = true;
                    System.out.print("<");
                }
                // 일반 글자인 경우 스택에 추가
                else
                    stack.add(s);
            }

            else {  // <> 내부
                // >가 나온 경우 > 출력, check 비활성화
                if(s == '>') {
                    System.out.print(">");
                    check = false;
                }
                // 아닌 경우 출력
                else
                    System.out.print(s);
            }
        }

        // 스택에 남은 잔여 글자 출력
        while(!stack.isEmpty())
            System.out.print(stack.pop());

        br.close();
    }
}
