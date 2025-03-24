import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2075_N번째큰수 {
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        n = parseInt(br.readLine());
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                pq.add(parseInt(st.nextToken()));
            }
        }
        int res = 0;
        for(int i=0;i<n;i++){
            res = pq.poll();
        }
        System.out.println(res);

    }
}
