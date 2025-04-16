/*
 * 가장 첫 줄에는 테스트 케이스의 총 수가 주어진다. 
 * 그 다음 줄부터 각 테스트 케이스가 주어지며, 각 테스트 케이스는 2줄로 구성된다. 
 * 각 테스트 케이스의 첫째 줄에는 나무의 개수 N이 주어진다. 
 * 다음 줄에는 나무들의 높이가 N개의 자연수로 주어진다
 */

/*
 * 출력의 각 줄은 ‘#x’로 시작하고, 공백을 한 칸 둔 다음 가능한 최소 날짜 수를 출력한다. 
 * 단, x는 테스트 케이스의 번호이다.
 */

import java.io.*;
import java.util.*;

public class Solution_14520_나무높이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        System.out.println(sb);
    }
    
    public static int solve() throws Exception {
        int N;
        int[] trees;
        int maxTree = 0;
        int result = 0;
        int odd = 0, even = 0;

        N = Integer.parseInt(br.readLine());
        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            maxTree = Math.max(maxTree, trees[i]);
        }

        // 차이 저장
        for (int i = 0; i < N; i++) {
            even += (maxTree - trees[i]) / 2;
            odd += (maxTree - trees[i]) % 2;
        }
        
        int temp = Math.max(even - odd, 0) / 3;

        even -= temp;
        odd += temp * 2;
        int oddEvenMin = Math.min(odd, even);
        result	= oddEvenMin * 2 + Math.max((odd - oddEvenMin) * 2 - 1, 0) + (even - oddEvenMin) / 2 * 3 + (even - oddEvenMin) % 2 * 2;

        return result;
    }
}
