import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_30021_순열선물하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static boolean[] check;
    static boolean[] isNotPrime;
    static Deque<Integer> dq = new ArrayDeque<>();
    // static List<Integer> result;
    public static void main(String[] args) throws Exception {
        // long sTime = System.currentTimeMillis();
        N = Integer.parseInt(br.readLine());
        check = new boolean[N+1];

        int primeLength = (N * (N + 1)) / 2;
        isNotPrime = new boolean[primeLength+1];
        
        isNotPrime [0] = isNotPrime [1] = true;
            
        for(int i=2; i*i<=primeLength; i++){
            if(!isNotPrime[i]){
                for(int j=i*i; j<=primeLength; j+=i) {
                    isNotPrime[j] = true;                
                }
            }        
        }

        backtrack(0, 0);

        System.out.println("NO");

        // long eTime = System.curretTimeMillis();
        // System.out.println(eTime - sTime);
    }

    public static void backtrack(int sum, int depth) {
        if (!isNotPrime[sum]) {
            return;
        }

        if (depth == N) {
            sb.append("YES\n");
            for (int i : dq) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 1; i <= N; i++) {
            if (!check[i]) {
                check[i] = true;
                dq.offerLast(i);
                backtrack(sum + i, depth + 1);
                dq.pollLast();
                check[i] = false;
            }
        }
    }
}
