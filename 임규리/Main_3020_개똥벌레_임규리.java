import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * GPT와 함께...^_^
 *
 * 1. 입력 받기
 *  - 입력은 석순과 종유석이 번갈아 주어짐 (짝수 : 석순, 홀수 : 종유석)
 *  - 석순과 종유석 각각의 높이 별로 몇 개씩 있는지 bottom, top 배열에 저장
 * 2. 누적합 만들기
 *  - bottomCount[h] = h 이상 석순 개수
 *  - topCount[h] = h 이상 종유석 개수
 *  - 뒤에서부터 누적합 계산
 * 3. 각 높이(h)마다 부딪히는 장애물 계산
 *  - 개똥벌레가 높이 h로 날아간다면
 *      - bottomCount[h] : 그 높이 이상 석순 개수
 *      - topCount[H - h + 1] : 그 높이 이상 종유석 개수
 *  - h에서 부딪히는 총 장애물 수 = 두 개의 합
 * 4. 최솟값 및 그 개수 계산
 *  - 매번 최솟값을 갱신하면서, 동일한 최솟값이 나올 경우 count++
 */
public class Main_3020_개똥벌레_임규리 {

    static int N;   // 동굴 길이 ( = 장애물 개수)
    static int H;   // 동굴 높이
    static int[] bottom;    // 석순 높이별 개수
    static int[] top;       // 종유석 높이별 개수
    static int[] bottomCount;   // 석순 누적합 : i 이상인 석순의 개수
    static int[] topCount;      // 종유석 누적합 : i 이상인 종유석의 개수
    static int minDestroy;      // 파괴해야 하는 최소 장애물 개수
    static int countResult;     // 최소 파괴 구간 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        H = parseInt(st.nextToken());

        bottom = new int[H + 1];    // 1부터 시작
        top = new int[H + 1];       // 1부터 시작

        for (int i = 0; i < N; i++) {
            // 짝수 인덱스는 석순, 홀수 인덱스는 종유석
            if (i % 2 == 0) {
                bottom[parseInt(br.readLine())]++;
            } else {
                top[parseInt(br.readLine())]++;
            }
        }

        bottomCount = new int[H + 1];
        topCount = new int[H + 1];

        // 누적합 계산 : 뒤에서부터 쌓아올림
        for (int i = H; i >= 1; i--) {
            if (i == H) {
                // 가장 끝은 자기 자신의 값 저장
                bottomCount[i] = bottom[i];
                topCount[i] = top[i];
            } else {
                bottomCount[i] = bottom[i] + bottomCount[i + 1];
                topCount[i] = top[i] + topCount[i + 1];
            }
        }

        minDestroy = Integer.MAX_VALUE;
        countResult = 0;
        for (int h = 1; h <= H; h++) {
            int destroy = bottomCount[h] + topCount[H - h + 1];
            if (destroy < minDestroy) {
                minDestroy = destroy;
                countResult = 1;
            } else if (destroy == minDestroy) {
                countResult++;
            }
        }

        System.out.println(minDestroy + " "  + countResult);
    }
}
