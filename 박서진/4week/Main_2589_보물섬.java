import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2589_보물섬 {
    static char map[][];
    static boolean isVisited[][];
    static int n, m;

    public static class Node {
        public int x;
        public int y;
        public int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        map = new char[n][m];
        isVisited = new boolean[n][m];
        int res=0;


        for(int i=0;i<n;i++){
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if (map[i][j]=='L'){
                    if(!isVisited[i][j]){
                        isVisited = new boolean[n][m];
                        res = Math.max(res, BFS(i, j));
                    }
                }
            }
        }
        System.out.println(res);
    }


    public static int BFS(int r, int c){
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        ArrayDeque<Node> dq = new ArrayDeque<>();
        int max_distance=0;
        isVisited[r][c] = true;
        dq.add(new Node(r,c,0)); // 첫 노드의 cost는 0입니다

        while(!dq.isEmpty()){
            Node temp = dq.poll();
            for(int i=0;i<4;i++){
                int nx = temp.x+dr[i];
                int ny = temp.y+dc[i];
                if(nx>=0 && nx<n && ny>=0 && ny<m)
                    if( map[nx][ny]=='L'&& !isVisited[nx][ny]){
                        isVisited[nx][ny] = true;
                        dq.add(new Node(nx, ny, temp.cost+1));
                        max_distance = Math.max(max_distance, temp.cost+1);
                }
            }
        }


        return max_distance;

    }
}



