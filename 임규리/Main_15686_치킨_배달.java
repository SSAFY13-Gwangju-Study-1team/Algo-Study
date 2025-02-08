/**
 * 문제를 제대로 이해하지 못해 블로그를 참고하였습니다.
 * https://velog.io/@kimmjieun/%EB%B0%B1%EC%A4%80-15686%EB%B2%88-%EC%B9%98%ED%82%A8-%EB%B0%B0%EB%8B%AC-Java-%EC%9E%90%EB%B0%94
 *
 * 치킨집을 M개만 남겨두고 폐업시킬 때, 도시의 치킨거리의 최솟값 구하기
 * = M개의 치킨집을 조합으로 뽑고, 각 경우의 최소 도시 치킨거리를 구해 결과 반환
 *
 * 서버 문제로 결과 확인 X...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15686_치킨_배달 {

    static int N, M;
    static int[][] city;
    static List<int[]> house;
    static List<int[]> chicken;
    static List<int[]> choice;
    static boolean[] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];
        house = new ArrayList<>();
        chicken = new ArrayList<>();
        choice = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());

                // 집 좌표 저장
                if (city[i][j] == 1) {
                    house.add(new int[]{i, j});
                }

                // 치킨집 좌표 저장
                if (city[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[chicken.size()];  // 치킨집 선택 여부
        result = Integer.MAX_VALUE;

        combination(0, 0);
        System.out.println(result);
    }

    private static void combination(int start, int depth) {
        // 치킨집 M개 선택
        if (depth == M) {
            int sum = 0;

            // 도시의 치킨거리 구하기
            for (int[] h : house) {
                int min = Integer.MAX_VALUE;

                // h의 치킨거리 구하기
                for (int[] c : choice) {
                    int dist = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                    min = Math.min(min, dist);
                }

                sum += min;
            }

            // 도시의 치킨거리 업데이트
            result = Math.min(sum, result);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                choice.add(chicken.get(i));
                combination(i + 1, depth + 1);
                choice.remove(choice.size() - 1);
                visited[i] = false;
            }
        }
    }
}
