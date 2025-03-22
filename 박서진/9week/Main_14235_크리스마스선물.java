import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_14235_크리스마스선물 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = parseInt(br.readLine());
        // 내림차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int p = parseInt(st.nextToken());
            if(p==0) { //0이면 선물을 꺼내서 준다
                if(!pq.isEmpty()){
                    sb.append(pq.poll()).append("\n");
                }else{
                    // 큐가 비워져 있으면 -1을 넣어준다
                    sb.append(-1).append("\n");
                }
            }else{
                // 가장 큰 선물을 꺼내서 준다
                for(int j=0;j<p;j++){
                    pq.add(parseInt(st.nextToken()));
                }
            }
        }
        System.out.println(sb);

    }
}
