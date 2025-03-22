import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_15903_카드합체놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            pq.add(Long.parseLong(st.nextToken()));
        }
        for(int i=0;i<m;i++){
            long n1 = pq.poll();
            long n2 = pq.poll();
            long sum = n1 + n2;
            pq.add(sum);
            pq.add(sum);
        }
        long res = 0;
        while(!pq.isEmpty()){
            res += pq.poll();
        }
        System.out.println(res);

    }
}
