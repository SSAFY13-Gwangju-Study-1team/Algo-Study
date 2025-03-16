import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 최소 거리를 보장해야 하기 때문에 BFS 문제로 푸는 것이 좋음
 * 맵에서 바로 가중치를 업데이트 하는 것이 어려운 것 같다
 * 노드로 cost를 저장해서 x, y과 n, m이 되었을 때 cost를 바로 res에 저장하도록 했다
 */
public class Main_2178_미로탐색_2 {
    public static class Node{
        public int r;
        public int c;
        public int cost;
        public Node(int r, int c, int cost){
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

    }
    static int map[][];
    static boolean isVisited[][];
    static int res=1;
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

        System.out.println(res);
    }

    public static void BFS(int r, int c, int n, int m){
        if(isVisited[r][c]) return;
        int[][] dir = {{-1,0},{1,0},{0,1},{0, -1}};
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(r,c,0));
        while (!q.isEmpty()){
            Node temp = q.poll();
            int x = temp.r;
            int y = temp.c;

            for(int i=0;i<4;i++){
                int nx = x+dir[i][0];
                int ny = y+dir[i][1];
                if(nx>0 && nx<=n && ny>0 && ny<=m){
                    if(map[nx][ny]!=0 &&!isVisited[nx][ny]){
                        isVisited[nx][ny] = true;
                        if(nx==n && ny == m){
                            res+=temp.cost+1;
                            break;
                        }
                        Node newN = new Node(nx,ny,temp.cost+1);
                        q.add(newN);
                    }
                }
            }
        }

    }
}
