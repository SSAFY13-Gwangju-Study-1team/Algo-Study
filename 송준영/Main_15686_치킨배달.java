import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_15686_치킨배달 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int result = Integer.MAX_VALUE;
    // static int[][] chickenMap;
    static List<int[]> homes = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        // chickenMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = parseInt(st.nextToken());
                if(temp == 1) {
                    homes.add(new int[] { i , j });
                } else if (temp == 2) {
                    chickens.add(new int[] { i, j });
                }
            }
        }

        backtrack(M, 0, -1, new boolean[chickens.size()]);

        System.out.println(result);
    }

    // 집, 치킨집 위치 구하기 N * N = 2500
    // 치킨집 M개의 조합으로 각 집 거리 최소값을 다 더하기 -> 도시의 치킨거리
    // 가장 작은얘로 설정

    public static void backtrack(int M, int depth, int step, boolean[] check) {
        if (M == depth) {
            int min_sum = 0;
            for (int[] home : homes) {
                int temp_min = Integer.MAX_VALUE;
                for (int i = 0; i < check.length; i++) {
                    if (check[i]) {
                        temp_min = Math.min(temp_min, calChickenDist(home, chickens.get(i)));
                    }
                }
                min_sum += temp_min;
            }

            result = Math.min(result, min_sum);
            return;
        }

        for (int i = 0; i < chickens.size(); i++) {
            if (!check[i] && step < i) {
                check[i] = true;
                backtrack(M, depth + 1, i, check);
                check[i] = false;
            }
        }
    }

    public static int calChickenDist(int[] home, int[] chicken) {
        return Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
    }
    
}
