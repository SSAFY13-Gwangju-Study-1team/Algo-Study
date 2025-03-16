import java.util.*;
import java.io.*;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;
/**
 *
 * core의 개수가 적으니 backtrack으로 풀 수 있다
 *
 */
public class SWEA_1767_프로세서연결하기 {
    static int n;
    static int[][] map;
    static boolean[][] isVisited;
    static ArrayList<int[]> processor;
    static int minLength = Integer.MAX_VALUE;
    static int coreCnt = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st ;
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++) {
            n = parseInt(br.readLine());
            map = new int[n][n];
            isVisited = new boolean[n][n];
            processor = new ArrayList<>();
            minLength = Integer.MAX_VALUE;
            coreCnt = 0;

            for(int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++) {
                    map[i][j] = parseInt(st.nextToken());
                }
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    // 만약 경계에 붙어있다면 백트랙 제외 임
                    if(map[i][j]==1 && i!=0 && i!=n-1 && j!=0 && j!=n-1)
                        processor.add(new int[]{i, j});
                }
            }

            backtrack(0, 0, 0);
            sb.append("#"+t+" "+minLength).append("\n");
        }
        System.out.println(sb);
    }

    public static void backtrack(int depth, int cnt, int sum) {
        // 기저 조건
        // 프로세스가 모두 연결되었다면
        if(depth==processor.size()) {
            if (coreCnt < cnt) {
                coreCnt = cnt;
                minLength = sum;
            } else if (coreCnt == cnt) {
                minLength = Math.min(sum, minLength);
            }

            return;
        }

        int r = processor.get(depth)[0];
        int c = processor.get(depth)[1];
        // 조합 느낌으로 백트래킹을 시도한다
        // 상하좌우 네 방향을 다 해준다
        for(int dir=0;dir<4;dir++){
            int lineLength = connect(dir, r, c);
            if(lineLength != -1){
                backtrack(depth+1, cnt+1, sum+lineLength);
                disconnect(dir, r, c);
            }

        }
        // 연결이 안되었을 때
        backtrack(depth+1, cnt, sum);

    }

    // 방향과 위치를 넣으면 그 라인에 프로세서나 선이 있는지 확인하는 함수
    // 0, 1, 2, 3 => 상, 하, 좌, 우
    public static int connect(int dir, int r, int c){
        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};
        int nr =r +dr[dir];
        int nc =c +dc[dir];
        int lineLength=0;
        while(nr>=0 && nr<n && nc>=0 && nc<n){
            if(map[nr][nc] == 1 || isVisited[nr][nc]){
                return  -1;
            }
            nr += dr[dir];
            nc += dc[dir];
            lineLength++;
        }
        nr =r +dr[dir];
        nc =c +dc[dir];
        // 끝까지 왔다면 방문처리 해줌
        while(nr>=0 && nr<n && nc>=0 && nc<n){
            isVisited[nr][nc] = true;
            nr += dr[dir];
            nc += dc[dir];
        }
        return lineLength;
    }

    public static void disconnect(int dir, int r, int c){
        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};
        int nr =r +dr[dir];
        int nc =c +dc[dir];

        // 끝까지 왔다면 방문처리 해줌
        while(nr>=0 && nr<n && nc>=0 && nc<n){
            isVisited[nr][nc] = false;
            nr += dr[dir];
            nc += dc[dir];
        }
    }

}
