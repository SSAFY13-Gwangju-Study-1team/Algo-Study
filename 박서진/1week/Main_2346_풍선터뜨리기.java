import java.io.*;
import java.util.*;
public class Main_2346_풍선터뜨리기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
//        Deque<int[]> q = new LinkedList<>(); //큐를 활용 할 연결리스트
        // 이렇게 했더니 메모리 초과 뜸
        // ArrayDeque로 바꿔줘야 함
        Deque<int[]> q = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            q.offer(new int[] {i , Integer.parseInt(st.nextToken())});
        }

        StringBuffer sb = new StringBuffer();
        // 첫번째 해결
        int[] front = q.poll();
        int now_index = front[0];
        sb.append(now_index+1).append(" ");

        int next = front[1];

        while(!q.isEmpty()){
            if(next>=0){
                for (int i=0;i<next-1;i++){
                    int[] temp = q.poll();
                    q.offer(temp);
                }
                front = q.poll();
            }else{
                for (int i=0;i<Math.abs(next)-1;i++){
                    int[] temp = q.pollLast();
                    q.offerFirst(temp);
                }
                front = q.pollLast();
            }
            now_index = front[0];
            sb.append(now_index+1).append(" ");
            next = front[1];
        }

        System.out.println(sb.toString());
    }
}
