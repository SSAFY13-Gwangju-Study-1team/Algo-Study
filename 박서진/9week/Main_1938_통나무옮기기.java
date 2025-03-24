import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1938_통나무옮기기 {
    static class Point{
        int logDir; // 통나무 방향 수직: 0, 수평: 1
        int coreR; // 통나무 중심 좌표
        int coreC;
        int time; // 움직인 횟수

        public Point(int logDir, int coreR, int coreC, int time) {
            this.logDir = logDir;
            this.coreR = coreR;
            this.coreC = coreC;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "logDir=" + logDir +
                    ", coreR=" + coreR +
                    ", coreC=" + coreC +
                    ", time=" + time +
                    '}';
        }
    }
    private static int[] aroundDr = { 0, 0, -1, 1, 1, 1, -1, -1 };
    private static int[] aroundDc = { -1, 1, 0, 0, 1, -1, 1, -1 };
    private static int[] dr = { -1, 1, 0, 0 };
    private static int[] dc = { 0, 0, -1, 1 };
    static int n;
    static int[][] map;
    static Point goal;
    static Point start;
    static boolean[][][] isVisited;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        map = new int[n][n];
        for(int i=0;i<n;i++){
            String[] temp = br.readLine().split("");
            for(int j=0;j<n;j++){
                switch (temp[j]){
                    case "B":
                        map[i][j] = 'B';
                        break;
                    case "0":
                        map[i][j] = 0;
                        break;
                    case "E":
                        map[i][j] = 'E';
                        break;
                    case "1":
                        map[i][j] = 1;
                        break;
                }
            }
        }

        // map에서 통나무의 위치를 찾기
        OUT:
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]=='B'){
                    // 통나무가 가로로 있는 경우
                    if(isInBound(i, j+1) && map[i][j+1]=='B'){
                        start = new Point(1, i, j+1, 0);
                        break OUT;
                    }else if (isInBound(i+1, j) && map[i+1][j]=='B'){// 통나무가 세로로 있는 경우
                        start = new Point(0, i+1, j, 0);
                        break OUT;
                    }
                }
            }
        }

        // map에서 목표치의 위치를 찾기
        OUT:
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]=='E'){
                    // 통나무가 가로로 있는 경우
                    if(isInBound(i, j+1) && map[i][j+1]=='E'){
                        goal = new Point(1, i, j+1, 0);
                        break OUT;
                    }else if (isInBound(i+1, j) && map[i+1][j]=='E'){// 통나무가 세로로 있는 경우
                        goal = new Point(0, i+1, j, 0);
                        break OUT;
                    }
                }
            }
        }

        isVisited = new boolean[n][n][2]; // 수직 방향과 수평 방향에 대한 맵 설정
        int res = bfs();
        System.out.println(res);
    }

    private static int bfs() {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(start);
        isVisited[start.coreR][start.coreC][start.logDir] = true;
        while(!q.isEmpty()){
            Point temp = q.poll();
            int tempR = temp.coreR;
            int tempC = temp.coreC;
            int tempDir = temp.logDir;
            int tempTime = temp.time;
            if(tempR == goal.coreR && tempC == goal.coreC && tempDir==goal.logDir){
                return tempTime;
            }
            // 4방을 돌면서 가능하다면 큐에 넣는다
            for(int dir=0;dir<4;dir++){
                int nextR = tempR+dr[dir];
                int nextC = tempC+dc[dir];
                if(isMove(temp, dir) && !isVisited[nextR][nextC][tempDir]){
                    isVisited[nextR][nextC][tempDir] =true;
                    q.add(new Point(tempDir, nextR,nextC, tempTime+1));
                }
            }
            // 돌릴 수 있으면 돌리기 - 좌표가 바뀌지 않음(중심)
            if(isRotate(tempR, tempC) && !isVisited[tempR][tempC][(tempDir+1) % 2]){
                isVisited[tempR][tempC][(tempDir+1) % 2] =true;
                q.add(new Point((tempDir+1)%2, tempR,tempC, tempTime+1));
            }
        }

        return 0;
    }

    private static boolean isMove(Point temp, int dir) {
        int newCoreR = temp.coreR+dr[dir];
        int newCoreC = temp.coreC+dc[dir];
        if(!isInBound(newCoreR, newCoreC))
            return false;
        // 수직 방향인 경우
        if(temp.logDir==0){
            // 움직이는 방향이 수직
            if(dir == 0 || dir==1){
                int checkR = newCoreR+dr[dir]; // 나보다 두칸 위 or 아래
                int checkC = newCoreC+dc[dir];
                if(!isInBound(checkR, checkC) || map[checkR][checkC]==1)
                    return false;
            }else {
                // 수평으로 움직일 때
                // 중심의 대각선 방향까지 체크
                if(!isInBound(temp.coreR-1, newCoreC) || !isInBound(temp.coreR+1, newCoreC))
                    return false;
                if(map[temp.coreR-1][newCoreC] == 1 || map[temp.coreR][newCoreC] == 1 || map[temp.coreR+1][newCoreC] == 1)
                    return false;
            }
        }else{
            // 수평 방향인 경우
            if(dir == 2 || dir== 3){
                int checkR = newCoreR+dr[dir]; // 나보다 두칸 위 or 아래
                int checkC = newCoreC+dc[dir];
                if(!isInBound(checkR, checkC) || map[checkR][checkC]==1)
                    return false;
            }else {
                // 수직으로 움직일 때
                // 중심의 대각선 방향까지 체크
                if(!isInBound(newCoreR, temp.coreC-1) || !isInBound(newCoreR, temp.coreC+1))
                    return false;
                if(map[newCoreR][temp.coreC-1] == 1 || map[newCoreR][temp.coreC] == 1 || map[newCoreR][temp.coreC+1] == 1)
                    return false;
            }
        }
        
        return true;

    }

    // 범위 체트하는 함수
    private static boolean isInBound(int r, int c){
        return r>=0 && c>=0 && r<n && c<n;
    }

    // 회전이 가능한지 확인하는 함수
    private static boolean isRotate(int r, int c){
        for(int i=0;i<8;i++){
            // 경계를 벗어나거나 나무가 있다면
            if(!isInBound(r+aroundDr[i], c+aroundDc[i]) || map[r+aroundDr[i]][c+aroundDc[i]]==1){
                return false;
            }
        }
        return true;
    }
}
