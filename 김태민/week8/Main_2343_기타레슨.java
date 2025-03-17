package Week8;

import java.io.*;
import java.util.*;

/**
 *
 * 시간 복잡도
 *  -
 *
 * 조건
 *  -
 *
 * 설계
 *  - 내가 구하고 싶은거는 m개로 적절하게 분배했을 때의 블루레이의 길이인데
 *  - 이값의 최솟값을 얻고 싶음
 *  - 이때 이 최솟값을 min이라고 하면 이 값은 강의 영상들 길이의 합으로 표현이 된다.
 *  - 그러면 이분탐색으로 블루레이의 길이를 탐색하는데
 *  - 이때 그 크기에 맞춰서 영상들을 집어 넣는다. 영상을 더했을 때 블루레이의 범위를
 *  - 넘어 선다면 카운트를 +1 해준다. 이떄 이 카운트는 블루레이 갯수가 되는데
 *  - 카운트가 m개보다 작다? 블루레이의 길이가 너무 큰거임  right = mid-1;
 *  - 카운트가 n개보다 같거나 크다? 넉넉한거임 이떄는 left = mid+1 해주면서 점점
 *  - 최솟값을 구해주는 거임
 *  - 끝내는 조건은 while(left<=right)떄 까지
 *
 * 출력
 *
 *
 *
 *
 */
//
//public class Main_2343_기타레슨 {
//    public static void main(String[] args) throws Exception {
//        public static void main(String[] args) throws{
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            StringTokenizer st = new StringTokenizer(br.readLine());
//
//            int N = Integer.parseInt(st.nextToken());
//            int M = Integer.parseInt(st.nextToken());
//
//            int[] lectures = new int[N];
//            st = new StringTokenizer(br.readLine());
//
//            long low = 0, high = 0;
//            for (int i = 0; i < N; i++) {
//                lectures[i] = Integer.parseInt(st.nextToken());
//                low = Math.max(low, lectures[i]);
//                high += lectures[i];
//            }
//
//            long answer = high;
//            while (low <= high) {
//                long mid = (low + high) / 2;
//                int count = 1;
//                long sum = 0;
//
//                for (int lecture : lectures) {
//                    if (sum + lecture > mid) {
//                        count++;
//                        sum = lecture;
//                    } else {
//                        sum += lecture;
//                    }
//                }
//
//                if (count <= M) {
//                    answer = mid;
//                    high = mid - 1;
//                } else {
//                    low = mid + 1;
//                }
//            }
//
//            System.out.println(answer);
//        }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//





