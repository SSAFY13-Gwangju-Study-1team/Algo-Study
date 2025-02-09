import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_5568_카드_놓기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int k;
    static int[] card;
    static Set<Integer> nums;
    static boolean[] visited;
    public static void main(String args[]) throws IOException {
        n = Integer.parseInt(br.readLine());    // 카드 개수
        k = Integer.parseInt(br.readLine());    // 선택하는 수

        card = new int[n];  // 카드 저장
        for(int i=0; i<n; i++) {
            card[i] = Integer.parseInt(br.readLine());
        }

        nums = new HashSet<>();     // 만든 정수를 저장하는 set
        visited = new boolean[n];   // 중복 방지용 방문 확인
        dfs(0, new StringBuilder());

        System.out.println(nums.size());
    }

    public static void dfs(int count, StringBuilder make) {
        if(count == k) {    // 만약 k개의 카드를 사용했다면
            int num = Integer.parseInt(make.toString());
            nums.add(num);  // set에 현재 번호를 저장(중복이라도 set이라서 허용됨)
        }

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                StringBuilder tmpMake = new StringBuilder(make.toString());
                tmpMake.append(card[i]);
                visited[i] = true;
                dfs(count+1, tmpMake);
                visited[i] = false; // 백트래킹
            }
        }


    }
}
