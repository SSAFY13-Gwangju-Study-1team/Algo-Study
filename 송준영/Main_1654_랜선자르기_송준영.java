import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_1654_랜선자르기_송준영
 * 난이도 4/10
 * 이분탐색, 파라매트릭 서치
 * 160ms 17mb
 * 
 * 랜선의 길이를 이분탐색으로 찾아주면 된다
 * 랜선의 길이를 기준으로 자른다면 몇개의 랜선이 나오는지 세어주는 함수를 만들어서 N개 이상이 나오면 랜선의 길이를 늘려주고
 * N개 이하라면 랜선의 길이를 줄여주면 된다
 * 
 * 다만 랜선의 길이가 int형 범위를 넘어갈 수 있으므로 long형으로 선언해 주어야 한다
 * l은 1부터 시작하고 r은 랜선의 최대 길이로 설정해주어야 하는데 그이유는
 * 랜선의 길이가 0이 될 수 없다.
 */
public class Main_1654_랜선자르기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int K, N;        // 랜선의 개수, 필요한 랜선의 개수
    static int[] cables;    // 랜선의 길이 배열

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        K = parseInt(st.nextToken());
        N = parseInt(st.nextToken());

        // 랜선의 길이 입력 및 l, r 초기화
        cables = new int[K];
        long l = 1, r = -1;
        for (int i = 0; i < K; i++) {
            cables[i] = parseInt(br.readLine());
            r = Math.max(r, cables[i]);
        }

        // 파라매트릭 서치 시작
        while ( l <= r ) {  // l이 r보다 커지면 종료 -> r이 최대 길이가 된다
            long mid = (l + r) / 2;     

            if (cutting(mid) >= N) {    // N개 이상이 나오면 랜선의 길이를 늘려준다, N 포함인 이유는 더 긴게 있을 수도 있으니까
                l = mid + 1;
            } else {                    // N개 미만이라면 랜선의 길이를 줄여준다
                r = mid - 1;
            }
        }

        // 출력
        System.out.println(r);
        
    }

    /**
     * 랜선을 length 만큼 잘라서 얼마만큼 나오는지 알려주는 메서드
     * @param length    랜선의 길이
     * @return          랜선의 개수
     */
    public static int cutting(long length) {
        int cnt = 0;    // 랜선의 개수

        for (int i = 0; i < K; i++) {
            cnt += cables[i] / length;  // 랜선의 길이로 나눈 몫이 랜선의 개수가 된다
        }

        return cnt; // 랜선의 개수 반환
    }
}
