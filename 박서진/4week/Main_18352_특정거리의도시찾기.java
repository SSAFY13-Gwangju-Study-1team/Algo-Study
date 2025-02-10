import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_18352_특정거리의도시찾기 {

    static int n, m, k, x;
    static ArrayList<Integer>[] A;
    static boolean[] isVisited;
    static Queue<int[]> q = new LinkedList<>();
    static ArrayList<Integer> res = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken()); // 도시의 개수
        m = parseInt(st.nextToken()); // 도로의 개수
        k = parseInt(st.nextToken()); // 거리정보
        x = parseInt(st.nextToken()); // 출발 도시의 번호

        A = new ArrayList[n+1];

        isVisited = new boolean[n+1];

        for(int i=1;i<n+1;i++) {
            A[i] = new ArrayList<Integer>(); // 도로 초기화
        }


        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int dep = parseInt(st.nextToken());
            int des = parseInt(st.nextToken());
            A[dep].add(des); // 도로 만들기
        }
        BFS(x);

        if (res.size()==0) System.out.println(-1);
        else {
            Collections.sort(res);
            for(int i : res)System.out.println(i);
        }

    }
    public static void BFS(int start) {
        isVisited[start] = true;
        q.add(new int[] {start, 0});
        while(!q.isEmpty()) {
            int[] current = q.poll();
            int node = current[0];
            int depth =current[1];

            if(depth==k) {
                res.add(node);
            }
            for(int nextV : A[node]) {
                if (!isVisited[nextV]) {
                    isVisited[nextV] = true;
                    q.add(new int[] {nextV, depth+1});
                }
            }
        }

    }

}
