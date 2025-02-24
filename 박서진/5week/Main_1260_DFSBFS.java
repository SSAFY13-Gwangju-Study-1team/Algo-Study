import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 쉬운 문제!! DFS와 BFS만 정의하면 되는 문제
 * 조건 1 -> 작은 숫자부터 방문한다
 * 이를 위해 정렬을 써서 먼저 A리스트를 정렬해 줬다
 */
public class Main_1260_DFSBFS {
    static int n, m, k; // 정점 개수, 간선 개수, 시작 개수
    static ArrayList<Integer>[] A;
    static boolean isVisited[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        k = parseInt(st.nextToken());

        // A 배열 초기화
        A = new ArrayList[n+1];
        isVisited = new boolean[n+1];

        for(int i=0;i<=n;i++){
            A[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = parseInt(st.nextToken());
            int end = parseInt(st.nextToken());
            A[start].add(end);
            A[end].add(start);
        }

        // 정렬 시켜버리기
        for(int i=0;i<=n;i++){
            Collections.sort(A[i]);
        }

        DFS(k);
        sb.append("\n");
        isVisited = new boolean[n+1];

        BFS(k);
        System.out.println(sb);
        
    }

    public static void DFS(int k) {
        if(isVisited[k]) return;
        isVisited[k] = true;
        sb.append(k).append(" ");
        for(int i:A[k]){
            if(!isVisited[i]){
                DFS(i);
            }
        }
    }
    public static void BFS(int k) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        if(isVisited[k]) return;
        isVisited[k] = true;
        q.add(k);
        while (!q.isEmpty()){
            int h = q.poll();
            sb.append(h).append(" ");
            for(int i : A[h]){
                if(!isVisited[i]){
                    q.add(i);
                    isVisited[i] =true;
                }
            }
        }
    }
}
