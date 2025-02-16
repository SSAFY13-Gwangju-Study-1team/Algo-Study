import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 친구 관계 깊이가 4인 곳을 발견 하면 되는 dfs 문제
 * 모든 곳에서 친구 관계를 파악 하여야 하기 때문에 방문 배열을 계속 초기화 해 주거나
 * 혹은 다시 돌아 왔을 때 방문 처리를 false로 바꿔 주는 방법 중 선택하면 된다.
 * 나는 전자로 풀었고 왜이리 dfs 문제 풀 때 리스트 초기화 하는거 계속 까먹는지 모르겠다.
 */
public class Main_13023_친구관계파악하기 {
    static ArrayList<Integer>[] A; //친구관계 리스트를 담을 리스트
    static boolean isVisited[]; // 방문 처리를 위해 배열
    static int n; // 사람 수
    static boolean flag;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        int e = parseInt(st.nextToken());
        A = new ArrayList[n+1]; // 뼤먹지 말기!!!! A 먼저 생성자 선언!!!
        isVisited = new boolean[n+1];

        // 친구 관계 리스트 초기화
        for(int i=0;i<n;i++){
            A[i] = new ArrayList<Integer>();
        }

        // 친구관계 입력 받아 A 배열에 넣기
        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int start = parseInt(st.nextToken());
            int end = parseInt(st.nextToken());
            A[start].add(end);
            A[end].add(start);
        }

        // 친구관계 파악하기
        // a-> b, b->c ...d->e인 관계 찾기
        for(int i=0;i<n;i++){
            isVisited = new boolean[n+1];
            DFS(i, 0); // 모든 경우에서 dfs 돌려야 함 따라서 방문 했던 것을 계속 초기화 해줘야 함
            if(flag){ // 만약
                break;
            }
        }

        if(flag){
            System.out.println(1);
        }else {
            System.out.println(0);
        }

    }

    /**
     * 깊이가 4까지 도달 했다면 조건 만족!
     * @param start // 시작 노드 값
     * @param depth // 깊이
     */
    public static void DFS(int start, int depth){
        if(isVisited[start]) return;
        isVisited[start] = true;

        if(depth==4){ // 깊이가 4가 되면 flag를 true로 바꾸고 리턴
            flag = true;
            return;
        }

        for(int i:A[start]){
            if(!isVisited[i]){
                DFS(i, depth+1);
            }
        }
    }
}
