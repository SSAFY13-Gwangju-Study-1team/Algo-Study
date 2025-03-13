import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_2343_기타레슨_송준영
 * 난이도 5/10
 * 이분탐색 파라매트릭서치
 * 284ms 23mb
 * 
 * 이분탐색을 이용하여 문제를 해결했다
 * 뭐를 기준으로 해야할지 고민하다가 강의 길이를 기준으로 하고 그 길이가 최대가 되겎므 나눴을 때 M개 이하로 나눠지면
 * 그 길이를 줄이고 M개 이상으로 나눠지면 그 길이를 늘려가면서 최소 길이를 찾아냈다
 * 그런데 마지막 길이가 무조건 최소? 가 안될 수 있다고 한다 -> AI 도움
 * 그래서 result를 따로 만들고 mid의 값이 만족할 때 result를 갱신해주면서 최소값을 찾아냈다
 */
public class Main_2343_기타레슨_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;        // 강의 수, 블루레이 수
    static int[] lessons;   // 강의 길이 배열

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        // 강의 길이 입력 및 최대, 최소 범위 설정
        lessons = new int[N];
        long l = 0, r = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lessons[i] = parseInt(st.nextToken());
            l = Math.max(l, lessons[i]);    // 최소 범위 = 가장 긴 강의의 길이
            r += lessons[i];                // 최대 범위 = 모든 강의를 한 장에 담는 경우
        }

        // 결과값 초기화
        long result = r;

        // 이분탐색 실행
        while (l <= r) {
            long mid = (l + r) / 2;

            // mid 길이로 나눴을 때 M개 이하로 나눠지면 r을 줄이고 M개 이상으로 나눠지면 l을 늘려간다
            if (cal(mid) > M) {
                l = mid + 1;
            } else {
                result = Math.min(result, mid); // mid 길이가 만족할 때 result 갱신
                r = mid - 1;
            }
        }

        // System.out.println(l);
        System.out.println(result); // 결과 출력
    }

    /**
     * size 길이로 나눴을 때 몇 개로 나눠지는지 확인하는 메서드
     * @param size  길이
     * @return      나눠진 갯수
     */
    public static int cal(long size) {
        int cnt = 1;    // 블루레이 갯수
        long temp = 0;  // 블루레이에 담긴 강의 길이
        for (int i = 0; i < N; i++) {
            // temp에 강의 길이를 더했을 때 size보다 작으면 temp에 더해주고
            // 그렇지 않으면 temp를 강의 길이로 초기화하고 블루레이 갯수를 증가시킨다
            if (temp + lessons[i] <= size) {
                temp += lessons[i];
            } else {
                temp = lessons[i];
                cnt++;
            }
        }

        // 블루레이 갯수 반환
        return cnt;
    }
}
