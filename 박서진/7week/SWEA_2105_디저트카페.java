import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

/**
 * 시간: 353 ms
 * 백트래킹과 dfs를 잘 사용해야 한다
 * 왔던 길을 다시 돌아가면 안되고 저번 방향을 기준으로 회전하며 돌아야되기 떄문에 dir 변수를 사용해야 한다
 * dfs의 여러 선택이 있기 떄문에 방문처리를 잘 해주어야 한다
 */
public class SWEA_2105_디저트카페 {
    static int n;
    static int[][] map;
    static int maxEat = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            int tc = parseInt(br.readLine());
            for(int t=1; t<=tc;t++){

            n = parseInt(br.readLine());
            maxEat = Integer.MIN_VALUE;
            map = new int[n][n];
            for(int i=0;i<n;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++) {
                    map[i][j] = parseInt(st.nextToken());
                }
            }

            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    dfs(i, j, i, j, 0, new HashSet<>(), 1, new boolean[n][n]);
                }
            }
            maxEat = (maxEat==Integer.MIN_VALUE)?-1:maxEat;
            sb.append("#" + t+" " +maxEat).append("\n");
        }
        System.out.println(sb);

    }

    // 파라미터에 dir넣고 dir부터 방향을 탐색하는 것이 중요하다
    private static void dfs(int originR, int originC, int r, int c, int dir, Set<Integer> dessert, int cnt, boolean[][] isVisited) {
        int[] dr = {1, 1, -1, -1}; // 대각선 아래 방향부터 시작
        int[] dc = {1, -1, -1, 1};
        dessert.add(map[r][c]); // 첫번째 디저트도 넣어야 하기 때문에 for문 밖에 적어 주자
        isVisited[r][c] = true;
        // 대각선 탐색
        // 탐색을 이전 dir부터 시작한다(회전을 해서 4방향이 다 담겨야 함
        for(int i=dir;i<4;i++) {
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(nr>=0 && nc>=0 && nr<n && nc<n) {
                // 만약 다음 노드가 원래 노드 값과 같으면 성공
                if(cnt>=3 && nr == originR && nc==originC) {
                    maxEat = Math.max(maxEat, cnt);
                    // return 문을 사용하면 안된다.. dfs라서 당연히 return을 해야하는지 알았는데
                    // break를 걸어서 뒤에 디저트 값에서 제거하고 방문처리 false로 바꿔 주는 행위를
                    // 마지막 노드에서도 해줘야 한다
                    break;
                }
                if(!isVisited[nr][nc] && !dessert.contains(map[nr][nc])) {
                    // dir이 내가 왔던 방향부터 회전하는 느낌으로 돌아야 함
                    dfs(originR, originC, nr, nc, i, dessert, cnt+1, isVisited);
                }
            }

        }
        dessert.remove(map[r][c]);
        isVisited[r][c] = false;
    }

}