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
        public Point(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1; t<=tc;t++){
            // ---- 입력 ---- //
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = parseInt(st.nextToken());
            int a = parseInt(st.nextToken());
            int res=0;
            int[] A = new int[m+1];
            int[] B = new int[m+1];
            AP[] aps = new AP[a]; // ap 리스트
            st = new StringTokenizer(br.readLine());
            Point aPoint = new Point(1, 1);
            Point bPoint = new Point(10, 10);
            for(int i=1;i<=m;i++){
                A[i] = parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=m;i++){
                B[i] = parseInt(st.nextToken());
            }
            for(int i=0;i<a;i++){
                st = new StringTokenizer(br.readLine());
                int x = parseInt(st.nextToken());
                int y = parseInt(st.nextToken());
                aps[i] = new AP(y,x, parseInt(st.nextToken()),parseInt(st.nextToken()));
            }
            // ---- 구현 ---- //

            for(int time = 0; time<=m ; time++){
                // 시간마다 a, b 좌표 값 초기화
                move(aPoint, A[time]);
                move(bPoint, B[time]);

                // A 좌표 구하기
                int aX = aPoint.x;
                int aY = aPoint.y;
                ArrayList<Integer> aList = new ArrayList<>();
                // B 좌표 구하기
                int bX = bPoint.x;
                int bY = bPoint.y;
                ArrayList<Integer> bList = new ArrayList<>();
                // A, B가 어떤 bc에 속해있는지 처리하기
                for(int i=0;i<a;i++){
                    int bcX = aps[i].y;
                    int bcY = aps[i].x;
                    int distanceA = Math.abs(bcX-aX) + Math.abs(bcY-aY);
                    int distanceB = Math.abs(bcX-bX) + Math.abs(bcY-bY);
                    if(distanceA <= aps[i].c){
                        aList.add(i);
                    }
                    if(distanceB <= aps[i].c){
                        bList.add(i);
                    }
                }
                // 충전량 결정 profit update 하기
                // 방문 처리 되어있는 곳에서 고르는데 모든 조합에서 더 이득인걸 골라야 함
                // A를 기준으로 모든 B의 경우를 loop를 돌려서 계산
                // 만약 값이 같다면(i==j) 절반씩 까서 넣어줌
                int maxCharge = 0;  // 두개의 합이 가장 클 때를 넣어줌, 둘 다 들어가지 않을 때를 위해 0으로 초기화
                for(int idxA:aList) {
                    for(int idxB : bList) {
                        if(idxA==idxB) { // 만약에 같으면
                            maxCharge = Math.max(maxCharge, (aps[idxA].p + aps[idxB].p)/2);
                        }else { // 같은 ap를 사요하지 않으면
                            maxCharge = Math.max(maxCharge, aps[idxA].p + aps[idxB].p);
                        }
                    }
                }
                // A만 있을 때
                for(int idxA:aList) {
                    maxCharge = Math.max(maxCharge, aps[idxA].p);
                }

                //  B만 있을 때
                for(int idxB:bList) {
                    maxCharge = Math.max(maxCharge, aps[idxB].p);
                }
                res += maxCharge;

            }

            sb.append("#"+t+" "+res).append("\n");
        }
        System.out.println(sb);
    }
    private static void move(Point Point, int dir) {
        switch(dir) {
            case 0:
                return;
            case 1: // 위로
                Point.y -=1;
                return;
            case 2: // 우로
                Point.x +=1;
                return;
            case 3: // 하로
                Point.y +=1;
                return;
            case 4: // 좌로
                Point.x -=1;
                return;
        }

    }
}
