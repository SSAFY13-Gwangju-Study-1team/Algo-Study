import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class SWEA_1267_작업순서 {
    static int v, e;
    static int[][] map;
    static int[] depth;
    static ArrayDeque<Integer> q =new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t = 1;t<=10;t++){
            sb.append("#"+t+" ");
            st = new StringTokenizer(br.readLine());
            v = parseInt(st.nextToken());
            e = parseInt(st.nextToken());
            map = new int[v+1][v+1];
            depth = new int[v+1];
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<e;i++){
                int start = parseInt(st.nextToken());
                int end = parseInt(st.nextToken());
                map[start][end] = 1;
                depth[end] += 1;
            }
            for(int i=1;i<=v;i++){
                if(depth[i]==0){
                    q.add(i);
                }
            }
            bfs(q);
            sb.append("\n");

        }
        System.out.println(sb);
    }

    private static void bfs(ArrayDeque<Integer> q) {
        while(!q.isEmpty()){
            int current = q.poll();
            sb.append(current+" ");
            for(int c=1;c<=v;c++){
                if(map[current][c]==1){
                    depth[c] -= 1; // 차수를 하나 내려준다
                    if(depth[c]==0){
                        q.add(c);
                    }
                }
            }
        }
    }
}
