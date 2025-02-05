import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_5568 {
    static boolean[] visited;
    static int n;
    static int k;
    static int[] nums;
    static List<String> res = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        k = parseInt(br.readLine());
        nums = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            nums[i] = parseInt(br.readLine());
        }

        backtrack(0, "");
        System.out.println(res.size());

    }
    static void backtrack(int depth, String resNum) {
        if (depth == k) {
            if(!res.contains(resNum)) {
                res.add(resNum);
            }
            return;
        }

        for(int i=0;i<n;i++){
            if(!visited[i]){
                visited[i] = true; // 방문
                backtrack(depth+1, resNum+nums[i]); // 다음 Depth의 상태공간 트리 탐색
                visited[i] = false; // 탐색 종료 후 원래 상태로 변경
            }

        }

    }
}
