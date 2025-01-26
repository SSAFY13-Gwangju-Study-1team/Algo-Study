package algo;

import java.util.*;
import java.io.*;

public class Main_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사람 N 명
        int K = Integer.parseInt(st.nextToken()); // K 번째 제거
        ArrayDeque<Integer>dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for(int i = 1; i <= N; i++){ // N명 만큼 deque에 사람을 넣어준다.
            dq.addLast(i);
        }

        int index = 1; // 인덱스 계산을 위한 index 변수
        while(!dq.isEmpty()){
            if(index % K == 0){ // index가 k의 배수가 되면 deque에 맨 앞을 뺀다.
                sb.append(dq.pollFirst()).append(", ");
            }else{ // index가 k의 배수가 아니라면 맨앞 사람을 맨뒤로 옮긴다.
                dq.addLast(dq.pollFirst());
            }
            index++;
        }
        sb.setLength(sb.length() - 2); // 출력에 불필요한 ", "을 제거한다.
        sb.append(">");
        System.out.println(sb);

    }
}
