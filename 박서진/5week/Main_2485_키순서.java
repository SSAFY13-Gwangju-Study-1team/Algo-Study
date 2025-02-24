import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.reverse;

public class Main_2485_키순서 {
    static ArrayList<Integer> shorter[];
    static ArrayList<Integer> taller[];
    static int n, m;
    static boolean isVisited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());

        shorter = new ArrayList[n+1];
        taller = new ArrayList[n+1];


        for(int i=1;i<=n;i++) {
            shorter[i] = new ArrayList<>();
            taller[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int start = parseInt(st.nextToken());
            int end = parseInt(st.nextToken());
            shorter[start].add(end);
            taller[end].add(start);
        }

        int res=0;
        for(int i=1; i<=n;i++) {
            isVisited = new boolean[n+1];
            // 나보다 작은 키 세기
            int shorterCnt = findShorterTarget(i);
            isVisited = new boolean[n+1];
            // 나보다 큰 키 세기
            int longerCnt = findTallerTarget(i);
            if(shorterCnt+longerCnt==n-1){
                res++;
            }
        }

        System.out.println(res);



    }
    public static int findShorterTarget(int index) {
        isVisited[index] = true;
        int cnt = 0;
        for(int v:shorter[index]) {
            if (!isVisited[v]) {
                cnt += findShorterTarget(v)+1;
            }
        }
        return cnt;
    }

    public static int findTallerTarget(int index) {
        isVisited[index] = true;
        int cnt = 0;
        for(int v:taller[index]) {
            if (!isVisited[v]) {
                cnt += findTallerTarget(v)+1;
            }
        }
        return cnt;

    }
}
