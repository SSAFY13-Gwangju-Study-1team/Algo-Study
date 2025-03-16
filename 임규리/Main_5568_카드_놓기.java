import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main_5568_카드_놓기 {
    private static int n;
    private static int k;
    private static int[] cards;
    private static int[] temp;
    private static boolean[] visited;
    private static HashSet<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        cards = new int[n];
        temp = new int[k];
        visited = new boolean[n];
        set = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(br.readLine());
        }
        
        permutation(0);

        System.out.println(set.size());
    }
    
    private static void permutation(int depth) {
        if (depth == k) {
            StringBuilder sb = new StringBuilder();
            
            for (int t : temp) {
                sb.append(t);
            }
            
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[depth] = cards[i];

                permutation(depth + 1);

                visited[i] = false;
            }
        }
    }
}