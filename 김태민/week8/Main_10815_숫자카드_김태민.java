package Week8;

import java.io.*;
import java.util.*;

/**
 * 조건
 * - 주어진 숫자들 중에서 상근이가 가지고 있는 숫자카드 인지 아닌지 구해야함
 * - 숫자카드의 갯수<=500,000인데 이걸 그냥 완탐하면 100억이 나옴 그래서 이분탐색을 써서
 *      시간을 줄여야함 이분탐색을 쓸시 33번이 탐색이 끝나게 된다.
 *
 * 설계
 *  - 상근이의 숫자를 정렬 시킨다음 주어진 숫자들을 map1에서 탐색하면서 있으면 1
 *      없으면 0을 출력하기
 *
 */

public class Main_10815_숫자카드_김태민 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] map1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            map1[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        Arrays.sort(map1);
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            int result = Arrays.binarySearch(map1, Integer.parseInt(st.nextToken()));
            if(result>=0){
                sb.append(1+" ");
            } else {
                sb.append(0+" ");
            }
        }

        System.out.println(sb);


    }

}



















