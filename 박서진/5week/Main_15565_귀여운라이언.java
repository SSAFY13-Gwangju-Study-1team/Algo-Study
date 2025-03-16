import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * 투포인터를 이용해서 라이언이 k개가 있는지 확인하기
 * 이중에 가장 작은 집합의 크기 출력
 * 처음에 범위 체크하는 부분에서 계속 틀렸다.
 * 라이언이 k가 될 때 rear가 늘어날때는 최소가 가장 처음이기 때문에 min_length를 update할 필요가 없다
 * front가 증가할 때만 계속해서 min_length를 update 해주기!!
 */
public class Main_15565_귀여운라이언 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parseInt(st.nextToken());
        int k = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[n];

        for(int i=0;i<n;i++){
            A[i] = parseInt(st.nextToken());
        }
        int front =0, rear =0;
        int total_lion = (A[0]==1) ? 1:0;
        int min_length = Integer.MAX_VALUE; // 길이가 가장 작은 슬라이딩 윈도우 구하기

        while(rear<n){
            if(total_lion<k){
                rear++;
                if(rear<n && A[rear]==1)
                    total_lion++;
            }else{
                min_length = Math.min(min_length, rear-front+1); // 라이언이 k가 되면 front를 줄이면서 최소 길이 찾기
                if(A[front]==1)
                    total_lion--;
                front++;
            }
        }

        // 결과가 있다면 출력 아니면 -1
        if(min_length!=Integer.MAX_VALUE) {
            System.out.println(min_length);
        }else{
            System.out.println(-1);
        }

    }
}
