import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 나보다 키가 작은 애들과 키가 큰 애들의 합이 n-1이면 된다
 * 나보다 키가 작은 애들을 입력 받으면 양방향 연결 리스트를 위해서 taller에도 넣어준다
 * 나머지는 dfs로 구현하면 되는데 cnt를 셀 때 그 전에 값에서 1개씩 더해가면서 마지막에 cnt를 뽑아주면 된다
 */
public class SWEA_5643_키순서 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int tc = parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            sb.append("#" + t + " ");
            solve();
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static int n;
    static int m;
    static boolean[] isVisited;
    static ArrayList<Integer>[] shorter;
    static ArrayList<Integer>[] taller;
    public static void solve() throws Exception{
        n = parseInt(br.readLine());
        m = parseInt(br.readLine());
        shorter = new ArrayList[n+1];
        taller = new ArrayList[n+1];
        int res = 0;
        for(int i=1;i<=n;i++){
            shorter[i] = new ArrayList<Integer>();
            taller[i] = new ArrayList<Integer>();
        }
        // 입력 받기
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int s1 = parseInt(st.nextToken());
            int s2 = parseInt(st.nextToken());
            shorter[s1].add(s2); // 나보다 키가 작은 애들을 리스트에 넣어주고
            taller[s2].add(s1); // 나보다 키가 큰 애들을 리스트에 넣어준다
        }

        for(int i=1;i<=n;i++){
            isVisited = new boolean[n+1];
            int shortCnt = findShorter(i); // 나보다 작은 친구들이 몇 명인지 센다
            isVisited = new boolean[n+1];
            int tallCnt = findTaller(i); // 나보다 큰 친구들이 몇 명인지 센다
            if(shortCnt+tallCnt==n-1){
                res++;
            }
        }
        sb.append(res);
        
    }
    
    // dfs 구현 방식으로 연결된 애들이 몇개인지 파악한다
    public static int findShorter(int start){
        isVisited[start] =true;
        int cnt =0;
        for(int i:shorter[start]){
            if(!isVisited[i])
                cnt += findShorter(i)+1;
        }
        return cnt;
    }

    public static int findTaller(int start){
        isVisited[start] =true;
        int cnt =0;
        for(int i:taller[start]){
            if(!isVisited[i])
                cnt+=findTaller(i)+1;
        }
        return cnt;
    }
    

}
