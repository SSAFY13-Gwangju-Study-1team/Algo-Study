import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 시간 : 106 ms
 * 난이도: 4/10
 * 생각보다 쉬웠음 방향설정만 잘하고 bfs 잘 돌리면 되는 문제~
 */
public class SWEA_1953_탈주범검거 {
    static int n, m, mr, mc, l;
    static int[][] map;
    static boolean[][] isVisited;
    static int areaCnt=1; // 현재 맨홀 아래부터 1
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            // <---- 입력 ----> //
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            m = parseInt(st.nextToken());
            mr = parseInt(st.nextToken());
            mc = parseInt(st.nextToken());
            l = parseInt(st.nextToken());
            areaCnt=1;
            map = new int[n][m];
            isVisited = new boolean[n][m];
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<m;j++){
                    map[i][j] = parseInt(st.nextToken());
                }
            }

            // <---- 구현 ----> //
            // 현재 맨홀 바로 아래 1초부터 시작, (mr,mc), areaCnt = 1부터 시작
            bfs(1, mr, mc);
            sb.append("#"+t+" "+areaCnt+"\n");
        }
        System.out.println(sb);

    }
    public static void bfs(int time, int r, int c){
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r,c, time});
        isVisited[r][c] = true;
        while(!q.isEmpty()){
            int[] temp = q.poll();
            int currentR = temp[0];
            int currentC = temp[1];
            int currentTime = temp[2];
            int currentDir  = map[currentR][currentC];
            int[] dirArr = checkDir(currentDir); // 현재 어떤 종류의 터널이 들어있는지 확인 어떤 방향의 노드와 연결될건지 확인
            for(int i: dirArr){
                int nr = currentR + dr[i];
                int nc = currentC + dc[i];
                // 다음 노드와 pair가 맞는지 확인
                // i가 어떤 방향인지(상하좌우)에 따라 어떤 연결이 되어있는지 체크
                // 예를 들어 i가 0이었다면 map[nr][nc]가 1, 2, 5, 6
                if(nr>=0 && nc>=0 && nr<n && nc<m && (currentTime+1)<=l && !isVisited[nr][nc]) { // 범위 안에 있고 방문하지 않았고 방향의 연결이 되었을 때 시간안에 들어올 때
                    int nextDir = map[nr][nc];
                    if (allowNextTurnel(i).contains(nextDir)) {
                        areaCnt++;
                        isVisited[nr][nc] = true;
                        q.add(new int[]{nr, nc, currentTime + 1});
                    }
                }
            }
        }
    }

    private static ArrayList<Integer> allowNextTurnel(int i) {
        switch (i){
            case 0:
                return new ArrayList<Integer>(Arrays.asList(1, 2, 5, 6));
            case 1:
                return new ArrayList<Integer>(Arrays.asList(1, 2, 4, 7));
            case 2:
                return new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5));
            case 3:
                return new ArrayList<Integer>(Arrays.asList(1, 3, 6, 7));
        }
        return null;
    }

    public static int[] checkDir(int turnel) {
        switch (turnel){
            case 1:
                return new int[]{0, 1, 2, 3};
            case 2:
                return new int[]{0, 1};
            case 3:
                return new int[]{2, 3};
            case 4:
                return new int[]{0, 3};
            case 5:
                return new int[]{1, 3};
            case 6:
                return new int[]{1, 2};
            case 7:
                return new int[]{0, 2};

        }
        return null;
    }
}
