import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 시간 140 ms 메모리 39,204 kb
 * 풀이 버전 1에서는 조합을 만들고 그 안에 선택 되면 안되는 것이 있는지 확인했지만 
 * 2번째 풀이에서는 고른 조합에 선택되면 안되는 것이 있으면 조합 생성을 멈추게 하였다
 * 이렇게 푸니 시간을 0.5초나 줄일 수 있었다
 */
public class SWEA_3421_수제버거장인2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static boolean[][] impo;
    static int totalcnt = 0;

    public static void main(String[] args) throws Exception {
        int tc = parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            sb.append("#" + t + " ");
            solve();
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        impo = new boolean[n + 1][n + 1]; // 안어울리는 재료 조합을 저장할  배열
        totalcnt = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());
            impo[x][y] = true;
            impo[y][x] = true;
        }

        comb(1, new ArrayList<Integer>());
        sb.append(totalcnt);
    }

    public static void comb(int cnt, ArrayList<Integer> pickArr) {
        if (cnt > n) {
            totalcnt++;
            return;
        }
        comb(cnt + 1, pickArr);

        // 햄버거에 포함되는 경우
        // 내가 고른 조합에 안되는 조합이 있는지
        for (int i : pickArr) {
            if (impo[i][cnt]) {
                return;
            }
        }

        // 햄버거에 포함되지 않는 경우
        pickArr.add(cnt);
        comb(cnt + 1, pickArr);
        pickArr.remove(pickArr.size() - 1);

    }
}