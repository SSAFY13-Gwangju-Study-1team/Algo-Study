package PACKAGE_NAME;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 연결된 L 간의 가장 긴 길을 찾는 문제
 * 모든 구간에서의 BFS를 계산하면 됨
 * 모든 연결된 L 구간에서 isVisited를 새로 만들어서 최단 거리 중 최대값을 찾으면 되는 문제
 */
public class Main_2589_보물섬 {
    static char map[][]; // 입력 받을 map
    static boolean isVisited[][]; // 방문 처리를 위한 이차원 배열
    static int n, m;

    /**
     * x좌표와 y좌표 cost를 지정해줌 
     */
    public static class Node {
        public int x; // 노드의 x 좌표
        public int y; // 노드이 y 좌표
        public int cost; // 노드 간 거리 - 거리 한칸에 한 시간임

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
        int res=0; // 최종 결과인 최단 거리의 max 값

        for(int i=0;i<n;i++){
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if (map[i][j]=='L'){ // Land를 만나면 BFS를 돌려서 그 지점에서의 최대값을 찾으면 됨
                    isVisited = new boolean[n][m]; // bfs 시작할 때마다 방문 배열을 초기화 해 주어야 각각의 지점에서 탐색이 가능함
                    res = Math.max(res, BFS(i, j));
                }
            }
        }
        System.out.println(res);
    }

    /**
     * 
     * @param r x 좌표
     * @param c y 좌표
     * @return 최대의 거리를 return 함(최소거리)
     */

    public static int BFS(int r, int c){
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        ArrayDeque<Node> dq = new ArrayDeque<>();
        int max_distance=0;
        isVisited[r][c] = true;
        dq.add(new Node(r,c,0)); // 첫 노드의 cost는 0입니다

        while(!dq.isEmpty()){
            Node temp = dq.poll();
            for(int i=0;i<4;i++){ // 4방을 돌면서 L가 있는지를 확인하는 BFS 문제
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

