import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class SWEA_1247_최적경로3 {
    static int n, resDist;
    static Point work, home;
    static Point[] customer;
    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t = 1;t<=tc;t++){
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            work = new Point(parseInt(st.nextToken()),parseInt(st.nextToken()));
            home = new Point(parseInt(st.nextToken()),parseInt(st.nextToken()));
            customer = new Point[n];
            resDist = Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                customer[i] = new Point(parseInt(st.nextToken()),parseInt(st.nextToken()));
            }
            // 먼저 순열을 고른다
            perm(0, work, 0, new boolean[n]);
            sb.append("#"+t+" "+resDist).append("\n");
        }
        System.out.println(sb);
    }

    private static void perm(int depth, Point prev, int dist, boolean[] isVisited) {
        if(dist>resDist) return;
        if(depth==n){
            int toHome = dist + calcDist(prev, home);
            resDist = Math.min(toHome, resDist);
            return;
        }

        for(int i=0;i<n;i++){
            if(isVisited[i]) continue;
            isVisited[i] =true;
            int newDist = calcDist(prev, customer[i]);
            perm(depth+1, customer[i], dist+newDist, isVisited);
            isVisited[i] =false;
        }
    }

    private static int calcDist(Point prev, Point point) {
        return Math.abs(prev.r - point.r) + Math.abs(prev.c-point.c);
    }

}
