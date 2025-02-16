import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 최소 거리를 보장해야 하기 때문에 BFS 문제로 푸는 것이 좋음
 */
public class Main_2178_미로탐색 {
    static int map[][] ;
    static boolean isVisited[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());

        map = new int[n+1][m+1];
        isVisited = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++){
            char[] line = br.readLine().toCharArray();
            for(int j=1;j<=m;j++){
                map[i][j] = parseInt(String.valueOf(line[j-1]));
            }
        }
        BFS(1, 1, n, m);

        System.out.println(map[n][m]);
    }

    public static void BFS(int r, int c, int n, int m){
        if(isVisited[r][c]) return;
        int[][] dir = {{-1,0},{1,0},{0,1},{0, -1}};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        while (!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for(int i=0;i<4;i++){
                int nx = x+dir[i][0];
                int ny = y+dir[i][1];
                if(nx>0 && nx<=n && ny>0 && ny<=m){
                    if(map[nx][ny]!=0 &&!isVisited[nx][ny]){
                        isVisited[nx][ny] = true;
                        map[nx][ny]=map[x][y]+1;
                        q.add(new int[]{nx,ny});
                    }
                }
            }
        }

    }
}
