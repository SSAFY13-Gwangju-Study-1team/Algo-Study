import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_3234_준환이의양팔저울2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            sb.append("#"+t+" ");
            solve();
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static int n;
    static int[] weights;
    static boolean[] isVisited;
    static int ans=0;
    
    public static void solve() throws Exception{
        n = parseInt(br.readLine());
        isVisited = new boolean[n];
        weights = new int[n];
        ans=0;
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            weights[i] = parseInt(st.nextToken());
        }
        // 먼저 순열을 통해서 순서를 결정하고 조합을 처리해 주도록
        perm(0, new int[n]);
        sb.append(ans);
    }
    
    // 무게의 순서를 먼저 정해줘야 함
    public static void perm(int depth ,int[] arr){
        if(depth==n){
            comb(0, arr, 0,0 );
            return;
        }

        for(int i=0;i<n;i++){
            if(isVisited[i]) continue;
            isVisited[i] = true;
            arr[depth] = weights[i];
            perm(depth+1, arr);
            isVisited[i] = false;
        }
        
    }
    public static void comb(int depth, int[] arr, int leftWeight, int rightWeight){
        if(leftWeight<rightWeight) return;
        if(depth==n){
            ans++;
            return;
        }
        // 왼쪽에 넣거나
        comb(depth+1, arr, leftWeight+arr[depth], rightWeight);
        // 오른쪽에 넣거나
        comb(depth+1, arr, leftWeight, rightWeight+arr[depth]);
    }
}
