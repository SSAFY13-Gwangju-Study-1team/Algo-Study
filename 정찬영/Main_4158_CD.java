/* 문제풀이 아이디어) 알고리즘 수업에서 배운 투포인터 활용
 * 281736kb 968ms
 * 체감 난이도: 4/10
 */

import java.io.*;
import java.util.*;

public class Main_4158_CD {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        while(true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 상근이 cd 수
            int M = Integer.parseInt(st.nextToken());   // 선영이 cd 수

            if(N == 0 && M == 0)
                break;

            // 상근이 cd 저장
            int[] arrN = new int[N];
            for(int n=0; n<N; n++) {
                arrN[n] = Integer.parseInt(br.readLine());
            }

            // 선영이 cd 저장
            int[] arrM = new int[M];
            for(int m=0; m<M; m++) {
                arrM[m] = Integer.parseInt(br.readLine());
            }

            // 투포인터를 위한 정렬
            Arrays.sort(arrN);
            Arrays.sort(arrM);

            int ans = 0;    // 같은 cd 수
            int nP = 0;     // 상근이 cd 포인터
            int mP = 0;     // 선영이 cd 포인터
            while(nP < N && mP < M) {
                if(arrN[nP] < arrM[mP]) {   // 비교한 상근이 cd가 작으면 포인터+1
                    nP++;
                } else if(arrN[nP] > arrM[mP]) {    // 선영이가 작으면 포인터+1
                    mP++;
                } else {    // 같으면 정답+1, 양 포인터+1
                    ans++;
                    nP++;
                    mP++;
                }
            }

            System.out.println(ans);
        }

    }
}
