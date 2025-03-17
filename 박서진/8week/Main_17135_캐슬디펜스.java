import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 난이도 7/10
 * 조합+탐색 +우선순위 큐를 사용해서 풀었습니다
 * 우선순위 큐를 사용하는 것이 힘들었습니다. 적을 움직이는 방식이 아니라 궁수를 아래서 위로 움직이게 만들었습니다.
 * 조합으로 먼저 열을 선택하고 해당 열에서 몇명을 죽일 수 있는지 하나씩 구하는 방식으로 구현하였습니다.
 * 다시 풀어봐야지~
 *
 */
public class Main_17135_캐슬디펜스 {
    static int n,m,d;
    static int map[][];
    static boolean isVisited[][];
    static int maxRes=Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        d = parseInt(st.nextToken());
        // 행을 하나 더 만들어서 궁수의 초기 위치를 정해줌
        map = new int[n][m];

        // 궁수를 0행에 위치 시킴
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = parseInt(st.nextToken());
            }
        }
        // 궁수가 위치할 열 3개를 조합으로 고르기
        combination(0, 0, new int[3]);
        System.out.println(maxRes);

    }

    private static void combination(int depth, int start, int[] cols) {
        if(depth==3){
            isVisited = new boolean[n][m];
            // 골라진 3가지 열 조합으로 공격하러 가기
            attack(cols);
            return;
        }

        for(int i=start;i<m;i++){
            cols[depth] = i;
            combination(depth+1, i+1, cols);
        }
    }

    private static void attack(int[] cols) {
        int cnt = 0; // 공격 횟수
        // d안에 있는 거리를 모두 가봐야 한다
        // 처음에 한 행에 한번만 접근해야 하는 줄 알았는데 거리가 2 이상인 경우 한행에 있는 2개의 적은 두번에 걸쳐서 죽일 수 있다
        // 궁수의 위치를 0행 c열에 둔다
        for(int t=n;t>=1;t--){ //총 n초가 걸림(행의 개수) // 초기의 궁수의 x위치를 -1로 둠
            //진행할 수 있는 행 1~d(앞으로 한칸은 무조건 이동해야함)
            HashSet<String> remove = new HashSet<>();
            for(int c : cols) {
                // 거리 우선, 왼쪽 우선 정렬을 위한 우선순위 큐
                PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                    if (o1[2] == o2[2]) return Integer.compare(o1[1], o2[1]); // 왼쪽 우선
                    return Integer.compare(o1[2], o2[2]); // 거리 우선
                });
                for(int r=1;r<=d;r++){
                    // 행이 r 만큼 움직였다면
                    if(t-r<0) continue;

                    for (int col = c - (d - r); col <= c + (d - r); col++) {
                        if (col < 0 || col >= m) continue;
                        if (map[t-r][col] == 1 && !isVisited[t-r][col]) {
                            int dist = Math.abs(n - (t - r)) + Math.abs(col-c) ; // 거리 값 (행과 열을 더한 값)
                            pq.add(new int[]{t - r, col, dist}); // (행, 열, 거리)
                        }
                    }
                }
                // 가장 가까운 적 1명 선택
                if (!pq.isEmpty()) {
                    int[] target = pq.poll();
                    remove.add(target[0] + "," + target[1]);
                }
            }
            // remove 안에 들어있는 가장 우선순위가 높은 행을 제거한다
            for(String temp : remove){
                String[] parts=temp.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                if(map[row][col] == 1){
                    cnt++;
                    isVisited[row][col] = true;
                }

            }
        }
        maxRes = Math.max(maxRes, cnt);
    }
}