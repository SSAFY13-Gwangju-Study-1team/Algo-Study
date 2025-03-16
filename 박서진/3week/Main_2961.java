import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_2961 {
    static List<Integer> res = new ArrayList<>();
    static int n;
    static int[] s_arr;
    static int[] b_arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = parseInt(br.readLine());
        s_arr = new int[n];
        b_arr = new int[n];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int s = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            s_arr[i] = s;
            b_arr[i] = b;
        }
        backTracking(0,1,0,0);
        // 곱셈의 초기값은 1

        sb.append(Collections.min(res));
        System.out.println(sb);
        br.close();
    }

    public static void backTracking(int depth, int s, int b, int selectedCnt) {
        if (depth == n) {
            if (selectedCnt>0) { // 무조건 하나 이상은 추가해야 함
                res.add(Math.abs(s-b));
            }
            return;
        }
        // depth가 인덱스가 되어 접근 가능
        // 사용하거나
        backTracking(depth+1, s*s_arr[depth], b+b_arr[depth], selectedCnt+1);
        // 사용하지 않거나
        backTracking(depth+1, s, b, selectedCnt);
    }

}
