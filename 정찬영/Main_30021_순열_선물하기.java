import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_30021_순열_선물하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static boolean[] prime;
    static boolean[] visited;
    static boolean check;

    public static void main(String args[]) throws IOException {
        N = Integer.parseInt(br.readLine());    // 선물 정수
        int Nsum = N*(N+1)/2;   // 1~N까지의 합
        find_prime(Nsum);

        visited = new boolean[N+1];
        dfs(0, new ArrayDeque<>());

        if(!check)
            System.out.print("NO");
    }

    public static void dfs(int count, ArrayDeque<Integer> Deque) {
        if(check)       // 이미 정답을 찾았으면 종료
            return;

        int sum = 0;    // 현재까지 준 선물의 합
        for(int num : Deque) {
            sum += num;
        }
        if(prime[sum])  // 만약 현재까지 준 선물의 합이 소수면 종료
            return;

        if(count == N) {    // 모든 선물을 줬다면
            check = true;   // 정답 여부 체크
            System.out.println("YES");
            while(!Deque.isEmpty()) {
                System.out.print(Deque.removeFirst() + " ");
            }
        }

        for(int i=1; i<=N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                ArrayDeque<Integer> Deque2 = new ArrayDeque<>(Deque);
                Deque2.addLast(i);
                dfs(count+1, Deque2);
                if(check)   // 이전 dfs에서 정답을 찾았으면 종료
                    return;

                visited[i] = false;   // 백트래킹
            }
        }
    }

    public static void find_prime(int n) {  // 에라토스테네스의 체로 소수를 구함
        prime = new boolean[n+1];   // 소수 판별 배열

        for(int i=0; i<n+1; i++) {  // boolean의 디폴트는 false -> true로 변환
            prime[i] = true;
        }

        prime[0] = prime[1] = false;    // 0, 1은 소수가 아님
        for(int i=2; i <= Math.sqrt(n); i++) {
            if(prime[i]) {
                for(int j=i*i; j<=n; j+=i) {
                    prime[j] = false;
                }
            }
        }
    }
}
