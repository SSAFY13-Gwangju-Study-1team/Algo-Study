import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 26,880 kb
 * 시간 783 ms
 * 난이도 상..
 * 처음에 조합을 먼저 구하고 순열을 구하려고 했는데 잘 되지 않았다
 * 그래서 순열을 먼저하고 조합을 구하는 로직을 구현했다
 * 순열의 리스트가 나오면 바로 조합을 통해 왼쪽과 오른쪽에 배치하는 순서가 조건에 통과되는 것만 남겨 카운팅을 했다 
 */
public class Main_3234_준환이의양팔저울 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] weight;
    static int cnt=0;
    static boolean isVisited[];
    static ArrayList<int[]> permArr;
    public static void main(String[] args) throws Exception{
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            sb.append("#"+t+" ");
            solve();
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void solve() throws Exception{
        cnt=0;
        n = parseInt(br.readLine());
        weight = new int[n];
        isVisited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            weight[i] = parseInt(st.nextToken());
        }
        permArr = new ArrayList<>();
        perm(0, new int[n]); // 모든 순열을 구하고 그 다음 조합을 구함
        sb.append(cnt);
    }
    static private void perm(int depth, int[] arr){
        if(depth==n){
            comb(0,0,0, arr);
            return;
        }
        for(int i=0;i<n;i++){
            if(isVisited[i]) continue;
            isVisited[i] = true;
            arr[depth] = weight[i];
            perm(depth+1, arr);
            isVisited[i] =false;
        }
    }
    static private void comb(int depth, int leftW, int rightW, int[] arr){
        if(leftW<rightW) return;

        if(depth==n){ //depth가 끝까지 돌았을 때
            // 조합 완료
            cnt++;
            return;
        }

        // 추를 왼쪽에 달았을 경우
        comb(depth + 1, leftW + arr[depth], rightW, arr);
        // 추를 오른쪽에 달았을 경우
        comb(depth + 1, leftW, rightW + arr[depth], arr);

    }


}
