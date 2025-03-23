import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

/**
 * 시간 1,281 ms
 * 중복 순열 + dfs + 맵 다시 조정하기
 * 맵 사용에 있어서 새로운 배열로 다시 재대입하는 것이 어려웠다 -> 깊은 복사의 활용을 높이자
 */
public class SWEA_5656_벽돌깨기 {
    static int n, w, h;
    static int[][] map;
    static int minBrick = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1; t<=tc;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            w = parseInt(st.nextToken());
            h = parseInt(st.nextToken());
            map = new int[h][w];
            minBrick = Integer.MAX_VALUE;

            for(int i=0;i<h;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<w;j++){
                    map[i][j] = parseInt(st.nextToken());
                }
            }
            
            // 중복 순열로 n만큼의 col 뽑기
            perm(0, new int[n]);
            sb.append("#"+t+" "+minBrick+"\n");
        }
        System.out.println(sb);
    }
    public static void perm(int depth, int[] arr){
        if(depth==n){
            // 다른 순열로 다시 dfs를 돌릴 때 map을 다시 원상 복구 시켜줘야 함 -> 중요 중요 
            int[][] originalMap = new int[h][w]; // 원본 저장
            for (int i = 0; i < h; i++) {
                originalMap[i] = map[i].clone(); // 깊은 복사 수행
            }
            // n만큼의 중복 순열을 다 골랐으면 (col값)
            // col중 가장 위에 있는 row를 뽑아 dfs 전달
            // 차례로 각각의 위치를 연쇄적으로 파괴하고 공백을 매꾸는 일을 반복한다
            for(int i=0;i<n;i++){
                boolean[][] isVisited  = new boolean[h][w];
                int row=-1;
                for(int r=0;r<h;r++){
                    if(map[r][arr[i]] != 0){
                        row = r;
                        break;
                    }
                }
                // 열에 벽돌이 없을 수도 있음
                if(row!=-1) {
                    dfs(row, arr[i], isVisited);
                    remakeMap(isVisited);
                }
            }
            // n번의 remake를 지났으면 남아있는 1을 샌다
            int brickCnt = 0;
            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    if(map[i][j] !=0){
                        brickCnt++;
                    }
                }
            }
            
            //최소의 벽돌 개수를 구해야 한다
            minBrick = Math.min(minBrick,brickCnt);
            
            // 다음 순열의 dfs를 들어가기 전에
            // 맵 다시 원상 복구 시켜주기
            for (int i = 0; i < h; i++) {
                map[i] = originalMap[i].clone();
            }
            return;
        }

        for(int i=0;i<w;i++){
            arr[depth] = i;
            perm(depth+1, arr);
        }
    }

    // 아래로 벽돌 이동시키기
    private static void remakeMap(boolean[][] isVisited) {  
        int[][] newMap = new int[h][w];
        //새로운 map에 숫자 채우기(0은 버린다)
        // 열우선탐색
        // 큐를 사용할 수도 있음
        for(int j=0;j<w;j++){
            int index = h-1;
            for(int i=h-1;i>=0;i--){
                if(map[i][j] != 0){
                    newMap[index--][j] = map[i][j];
                }
            }
        }
        //맵에 새로운 맵을 다시 할당한다
        // 깊은 복사를 해야함
        // 새로운 맵을 이용해서 다음 순열의 행위를 한다
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = newMap[i][j]; // 직접 값을 복사
            }
        }


    }

    private static void dfs(int r, int c, boolean[][] isVisited) {
        int[] dr = {-1, 1, 0, 0}; // 상하좌우
        int[] dc = {0, 0, -1, 1};
        isVisited[r][c] = true;
        for(int d=0;d<4;d++){ // 방향을 먼저 잡고 이동 크기 만큼 이동하면서 dfs 돌리기
            for(int i=0;i<map[r][c]-1;i++){
                int nr = r+dr[d] * (i+1);
                int nc = c+dc[d] * (i+1);
                if(nr>=0 && nc>=0 && nr<h && nc<w && !isVisited[nr][nc]){
                    dfs(nr, nc, isVisited);
                }
            }
        }

        // dfs 끝났으면 0으로 바꿔버리기 
        // 맵 아래로 떙기기 할 때 사용하기 위해
        map[r][c] = 0;
    }
}
