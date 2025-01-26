package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main_10845 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder
        ArrayDeque<Integer>dq = new ArrayDeque<>();

        for(int i = 0; i < N; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch(command){
                case "push": // deque에 정수를 추가해준다.
                    int value = Integer.parseInt(st.nextToken());
                    dq.addLast(value);
                    break;
                case "pop": //deque가 비어있지 않다면 앞에 있는 정수를 빼고 출력
                    if(!dq.isEmpty()){
                        sb.append(dq.pollFirst()).append("\n");
                    }else{
                        sb.append(-1).append("\n");
                    }
                    break;
                case "size": // deque의 크기를 출력
                    sb.append(dq.size()).append("\n");
                    break;
                case "empty": // deque가 비어있다면 1을 출력하고 아니면 0
                    if(dq.isEmpty()){
                        sb.append(1).append("\n");
                    }else{
                        sb.append(0).append("\n");
                    }
                    break;
                case "front": // deque의 맨앞을 출력만 함
                    if(!dq.isEmpty()){
                        sb.append(dq.peekFirst()).append("\n");
                    }else{
                        sb.append(-1).append("\n");
                    }
                    break;
                case "back": // deque의 맨뒤를 출력만 함
                    if(!dq.isEmpty()){
                        sb.append(dq.peekLast()).append("\n");
                    }else{
                        sb.append(-1).append("\n");
                    }
                    break;
            }
        }
        System.out.println(sb);

    }

}
