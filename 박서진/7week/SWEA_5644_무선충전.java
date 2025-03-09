import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class SWEA_5644_무선충전 {
    public static class AP{
        int x;
        int y;
        int c;
        int p;

        public AP(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }
    public static class Point{
        int x;
        int y;
        int profit;

        public Point(int x, int y, int profit) {
            this.x = x;
            this.y = y;
            this.profit = profit;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1; t<=tc;t++){
            // ---- 입력---- //
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = parseInt(st.nextToken());
            int a = parseInt(st.nextToken());
            int[] A = new int[m];
            int[] B = new int[m];
            AP[] aps = new AP[a]; // ap 리스트
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<m;i++){
                A[i] = parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<m;i++){
                B[i] = parseInt(st.nextToken());
            }
            for(int i=0;i<a;i++){
                st = new StringTokenizer(br.readLine());
                aps[i] = new AP(parseInt(st.nextToken()),parseInt(st.nextToken()), parseInt(st.nextToken()),parseInt(st.nextToken()));
            }
            // ---- 구현 ---- //
            Point aPoint = new Point(1, 1, 0);
            Point bPoint = new Point(10, 10, 0);
            for(int time = 0; time<m ; time++){
                // A가 있는 곳을 체크하기
                int aX = aPoint.x;
                int aY = aPoint.y;
                int aStatus = 0;
                for(int i=0;i<a;i++){
                    int bcX = aps[i].x;
                    int bcY = aps[i].y;
                    int distance = Math.abs(bcX-aX) + Math.abs(bcY-aY);
                    if(distance==aps[i].c){
                        aStatus = aStatus | (1<<i);
                    }
                }
                // B가 있는 곳을 체크하기

                // 충전량 결정
                // 방문 처리 되어있는 곳에서 고르는데 모든 조합에서 더 이득인걸 골라야 함
                
            }
        }
    }
}
