import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Integer>[] map;
    static boolean[] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        map = new ArrayList[n+1];
        visited = new boolean[n+1];

        for(int i = 1; i<=n; i++){
            map[i] = new ArrayList<>();
        }


        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            map[n1].add(n2);
            map[n2].add(n1);
        }
        visited[1] = true;
        dfs(1);
        System.out.println(cnt);

    }

    public static void dfs(int s){

        for(int i = 0; i<map[s].size(); i++){
            int next = map[s].get(i);
            if(!visited[next]){
                visited[next] = true;
                cnt++;
                dfs(next);
            }
        }
    }
}
