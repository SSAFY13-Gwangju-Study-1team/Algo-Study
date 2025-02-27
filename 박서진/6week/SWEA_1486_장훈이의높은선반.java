import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 26,112 kb 메모리, 시간 87 ms
 * 조합으로 풀 수 있었다. 몇 명의 직원을 선택해야 하는지가 없기에 선택하거나 안하거나의 집합 요소를 활용하였다
 * 뭔가 가방 문제 같아서 dp로도 풀 수 있을 것 같았는데 나는 못했다..ㅋㅋ 다시 풀어봐야겠다
 */
public class SWEA_1486_장훈이의높은선반 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            sb.append("#"+t+" ");
            solve();
        }
        System.out.println(sb);

    }
    static int n, b;
    static int[] staff;
    static int minRes = Integer.MAX_VALUE;
    private static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        b = parseInt(st.nextToken());
        staff = new int[n];
        minRes = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            staff[i] = parseInt(st.nextToken());
        }

        backtrack(0, 0, 0);
        sb.append(minRes-b+"\n");
    }

    private static void backtrack(int depth, int index, int sum) {
        if(depth==n){
            if(sum<b) return;
            minRes = Math.min(sum, minRes);
            return;
        }
        if(index==n) return;

        // 인덱스 직원을 선택하던지
        backtrack(depth+1, index+1, sum+staff[index]);
        // 인덱스 직원을 선택하지 않던지
        backtrack(depth+1, index+1, sum);
    }
}
