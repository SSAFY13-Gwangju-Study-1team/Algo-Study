import java.util.*;
import java.io.*;

public class Main_1743_음식물_피하기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static int[][] map;
    static int big = 0;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};   // 상하좌우
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로 길이
        M = Integer.parseInt(st.nextToken());   // 가로 길이
        K = Integer.parseInt(st.nextToken());   // 음식물 쓰레기 개수

        map = new int[N+1][M+1];    // 인덱스 편의를 위해 1씩 증가
        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        visited = new boolean[N+1][M+1];
        for(int i=1; i<N+1; i++) {  // 인덱스 편의를 위해 크기를 N+1로 했으므로 인덱스 0은 사용하지 않음
            for(int j=1; j<M+1; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    big = Math.max(big, dfs(i, j)); // dfs 할 때마다 최댓값 갱신
                }
            }
        }

        System.out.println(big);

    } // End of Main

    public static int dfs(int y, int x) {
        visited[y][x] = true;
        int ny, nx;
        int size = 1;   // 쓰레기 크기

        for(int k=0; k<4; k++) {
            ny = y + dy[k];
            nx = x + dx[k];
            if(ny >= 1 && ny < N+1 && nx >= 1 && nx < M+1 && map[ny][nx] == 1 && !visited[ny][nx]) {
                size += dfs(ny, nx);    // dfs할 때마다 1씩 누적
            }
        }

        return size;    // 쓰레기 크기 반환
    }
}