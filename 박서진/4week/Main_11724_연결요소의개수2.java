package PACKAGE_NAME;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_11724_연결요소의개수2 {
    static ArrayList<Integer>[] A;
    static boolean isVisited[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken()); // 정점 수
        int m = parseInt(st.nextToken()); // 간점 수
        A = new ArrayList[n+1];
        isVisited = new boolean[n+1];

        for(int i=1;i<=n;i++){
            A[i] = new ArrayList<Integer>(); //A의 모든 인덱스 값을 리스트로 초기화
        }

        // 연결 요소 찾기
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = parseInt(st.nextToken());
            int end = parseInt(st.nextToken());
            A[start].add(end);
            // 방향이 없는 그래프 이므로 반대도 넣어 주기
            A[end].add(start);
        }

        int res=0;

        for(int i=1;i<=n;i++){
            if(!isVisited[i]){
                res++;
                DFS(i);
            }
        }

        System.out.println(res);
    }

    public static void DFS(int v){
        if (isVisited[v]) return;
        isVisited[v] = true;
        for(int i: A[v]){
            if(!isVisited[i]){
                DFS(i);
            }
        }
    }
}
