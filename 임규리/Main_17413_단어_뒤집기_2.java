/**
 * Stack 활용
 * 생각보다 조건에 따른 처리를 고민하는 과정이 꽤 걸렸습니다.
 * 문장을 처음부터 끝까지 돌면서 '<'를 만난다면 '<'부터 '>'까지를 찾아 StringBuilder에 바로 append한 후 인덱스를 업데이트해주었습니다.
 * - 이 때 stack이 비어있지 않다면 (이전 단어가 남아있다면) 이를 처리해주었습니다.
 * 공백을 기준으로 분리되어야 하기에 공백을 만난다면 stack에 남아있는 단어를 처리해준 후 공백을 append하였습니다.
 * '<'도, 공백도 아니라면 stack에 push해주었습니다.
 * 마지막에 stack이 비어있지 않을 경우를 고려하여 따로 처리해주었습니다.
 */

import java.util.*;
import java.io.*;

public class Main_17413_단어_뒤집기_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '<') {
                // stack이 비어있지 않다면
                while(!stack.isEmpty()) {
                    result.append(stack.pop());
                }

                // '>'까지 찾아서 result에 추가 후 인덱스 업데이트
                int index = i;

                for (int j = i + 1; j < line.length(); j++) {
                    if (line.charAt(j) == '>') {
                        index = j;
                        break;
                    }
                }

                result.append(line.substring(i, index + 1));
                i = index;
            } else if (line.charAt(i) == ' ') {
                while(!stack.isEmpty()) {
                    result.append(stack.pop());
                }
                result.append(" ");
            } else {
                stack.push(line.charAt(i));
            }
        }

        while(!stack.isEmpty()) {
            result.append(stack.pop());
        }

        System.out.println(result);
    }
}