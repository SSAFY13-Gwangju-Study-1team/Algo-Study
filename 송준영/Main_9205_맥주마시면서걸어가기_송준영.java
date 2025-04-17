import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_9205_맥주마시면서걸어가기
 * 난이도 4/10
 * BFS
 * 132ms 14mb
 * 
 * 결국 거리가 1000 초과이면 못간다
 * 편의점이나 목적지까지 갈 수 있는지 BFS로 확인해 주면 된다
 * 목적지까지 가면 성공이고 아직 안 갔는데 queue가 비면 실패다
 * BFS는 queue를 사용해서 방문한 곳을 체크해 주고, 갈 수 있는 곳을 queue에 넣어준다
 */
public class Main_9205_맥주마시면서걸어가기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int t;           // 테케 수
    static int n;           // 편의점 수
    static int[] sang, rock;// 상근이, 락페스티벌 위치
    static int[][] conv;    // 편의점 위치

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        t = parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            solve();
            sb.append(bfs(sang[0], sang[1])).append('\n');
        }
        System.out.println(sb); // 출력
    }

    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        n = parseInt(br.readLine());
        sang = new int[2];
        rock = new int[2];
        conv = new int[n][2];
        for (int i = 0; i < n+2; i++) {
            st = new StringTokenizer(br.readLine());
            if (i == 0) {           // 상근이 위치
                sang[0] = parseInt(st.nextToken());
                sang[1] = parseInt(st.nextToken());
            } else if (i == n+1) {  // 락페스티벌 위치
                rock[0] = parseInt(st.nextToken());
                rock[1] = parseInt(st.nextToken());
            } else {                // 편의점 위치
                conv[i-1][0] = parseInt(st.nextToken());
                conv[i-1][1] = parseInt(st.nextToken());
            }
        }
    }

    /**
     * BFS 메서드
     * @param x 상근이 위치 x좌표
     * @param y 상근이 위치 y좌표
     * @return happy or sad
     */
    public static String bfs(int x, int y) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n]; // 편의점 방문 체크

        if (canGo(x, y, rock[0], rock[1])) {    // 상근이 위치에서 락페스티벌까지 갈 수 있는지 체크 -> 가면 바로 리턴
            return "happy";
        }

        for (int i = 0; i < n; i++) {           // 상근이 위치에서 편의점을 갈 수 있는지 체크 -> 갈수 있으면 q에 넣어주기 + 방문 처리
            if (canGo(x, y, conv[i][0], conv[i][1])) {
                visited[i] = true;
                q.offer(i);
            }
        }

        // BFS 시작
        while (!q.isEmpty()) {
            int temp = q.poll();

            if (canGo(conv[temp][0], conv[temp][1], rock[0], rock[1])) {    // 편의점에서 락페스티벌까지 갈 수 있는지 체크 -> 가면 happy 리턴
                return "happy";
            }

            for (int i = 0; i < n; i++) {
                if (i == temp) continue;    // 같은 편의점은 체크하지 않기

                if (!visited[i] && canGo(conv[temp][0], conv[temp][1], conv[i][0], conv[i][1])) {   // 편의점에서 다른 편의점까지 갈 수 있는지 체크 -> 갈 수 있으면 q에 넣어주기 + 방문 처리
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }

        return "sad";   // q가 비면 sad 리턴
    }

    /**
     * 거리 체크 메서드
     * @param x1    
     * @param y1
     * @param x2
     * @param y2
     * @return  true or false (1000m 이내면 true)
     */
    public static boolean canGo(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1 - x2) + Math.abs(y1 -y2)) <= 1000;
    }
}
