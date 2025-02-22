import java.io.*;
import java.util.*;

public class Main_1940_주몽 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());    // 재료의 개수 N
        int M = Integer.parseInt(br.readLine());    // 갑옷을 만드는데 필요한 수 M
        int[] arrN = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) {
            arrN[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arrN);

        int ans = 0;
        int pointer1 = 0;   // 왼쪽 끝 시작 포인터
        int pointer2 = N-1; // 오른쪽 끝 시작 포인터

        int m;
        while(pointer1 < pointer2) {
            m = arrN[pointer1] + arrN[pointer2];

            if(m < M) {
                pointer1++;
            } else if(m > M) {
                pointer2--;
            } else {
                ans++;
                pointer1++;
                pointer2--;
            }
        }

        System.out.println(ans);
        br.close();
    }
}
