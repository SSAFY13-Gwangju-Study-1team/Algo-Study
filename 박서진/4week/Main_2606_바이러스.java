import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_2606_바이러스 {
    static int n, m; // 컴퓨터 수, 연결 된 컴퓨터 수
    static ArrayList<Integer>[] A; // 컴퓨터 네트워트
    static boolean[] isVisited;
    static int res; //1번 컴퓨터 포함

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n=parseInt(br.readLine());
        m=parseInt(br.readLine());

        A = new ArrayList[n+1];

        // List 초기화
        for(int i=1;i<n+1;i++){
            A[i] = new ArrayList<Integer>();
        }

        isVisited = new boolean[n+1];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int r = parseInt(st.nextToken());
            int v = parseInt(st.nextToken());
            A[r].add(v);
            A[v].add(r);
        }

        DFS(1);
        System.out.println(res);
    }

    private static void DFS(int node){
        if(isVisited[node]) return;

        isVisited[node] = true;
        for(int i: A[node]){
            if(!isVisited[i]){
                res++;
                DFS(i);
            }
        }
    }
}
