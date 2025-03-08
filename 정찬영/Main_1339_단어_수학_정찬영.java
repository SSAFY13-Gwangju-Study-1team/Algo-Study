/* 메모리 14348kb, 시간 104ms
 * 문제 풀이 아이디어: 처음에는 모든 순열을 구하고 알파벳에 숫자를 대입하여 값을 구했는데 시간초과가 났음
 * -> 인터넷을 검색하여 알파벳마다 가중치를 주고 가중치가 높은 순으로 9, 8, 7... 을 곱함
 * 가중치는 두 가지로 1. 가장 많이 나오는 경우, 2.가장 높은 자리에 위치한 순서 두 가지로 부여한다.
 * 1. 10의 자리면 10, 100의 자리면 100, ... 을 가중치로 부여
 * 2. 1번을 반복함
 * 체감 난이도: 9/10 (인터넷 참고함)
 */

import java.io.*;
import java.util.*;

public class Main_1339_단어_수학_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    /* ----- 입력 ----- */
    static int N;
    static int[] abc = new int[26];
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());    // 단어 개수

        StringBuilder word = new StringBuilder();   // 단어 저장하는 변수
        char c; // 단어에서 알파벳 추출

        for(int i=0; i<N; i++) {
            word.setLength(0);          // 스트링빌더 초기화
            word.append(br.readLine()); // 단어 업데이트

            // 단어에서 한 글자씩 빼서 가중치 더함
            for(int j=0; j<word.length(); j++) {
                c = word.charAt(j);
                abc[c-'A'] += Math.pow(10, word.length()-1-j);
            }
        }

        Arrays.sort(abc);       // 배열 정렬
        int i = abc.length-1;   // 정방향으로 정렬했으므로 역순으로 탐색할 것임
        int j = 9;              // 곱할 숫자
        int sum = 0;

        while(abc[i] != 0) {    // 0이 나오기 전까지 계속 반복
            sum += abc[i] * j;  // 현재 값에 수 곱함
            i--;                // 인덱스와 곱할 수 -1
            j--;
        }
        sb.append(sum);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}