import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 모든 곳을 거쳐서 가는 방법중 가장 짧은 방법을 고르는 것으로
 * 순열을 통해 순서를 고른 후 거리를 더하면서 계산해야 함
 * 까다롭지만 풀 수 있는 문제
 */
public class SWEA_1247_최적경로 {
    static int n;
    static Point clients[];
    static Point company, home;
    static int minLength = Integer.MAX_VALUE;

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getInstance(Point o){
            return Math.abs(this.x-o.x) + Math.abs(this.y-o.y);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            n = parseInt(br.readLine());
            clients = new Point[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            company = new Point(parseInt(st.nextToken()), parseInt(st.nextToken()));
            home = new Point(parseInt(st.nextToken()), parseInt(st.nextToken()));
            minLength = Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                clients[i] = new Point(parseInt(st.nextToken()), parseInt(st.nextToken()));
            }

            // dfs + backtrack
            dfs(0, company, 0, 0);

            sb.append("#"+t+" "+minLength+"\n");
        }
        System.out.println(sb);
    }
    public static void dfs(int cnt, Point prev, int sum, int isVisit){
        // 백트랙을 이용해서 sum이 minLength보다 커지면 멈추기
        if(sum>=minLength) return;

        //기저조건
        if(cnt==n){
            // 마지막 고객과 집을 연결 
            int toHome = sum+home.getInstance(prev);
            // 최소값 갱신
            minLength = Math.min(toHome, minLength);
            return;
        }
        
        // 모든 경우를 다 생각해야 해서 순열로 모든 곳에 가는 경우를 구해야함(이미 간 곳은 안감)
        for(int i=0;i<n;i++){
            // 방문을 했으면 pass
            if((isVisit & 1 <<i)!=0) continue;
            // 내가 갈 다음 연산을 위한 조정
            dfs(cnt+1, clients[i], sum+prev.getInstance(clients[i]), isVisit|1<<i);
        }
    }
}
