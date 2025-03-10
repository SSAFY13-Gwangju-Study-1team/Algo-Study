import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_17471_게리멘더링0308 {
    static int n;
    static int[] people;
    static int[][] map; //연결되어 있다면 1을 넣어주기
    static int connectedCnt;
    static int minDiffPeople = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        people = new int[n+1];
        map = new int[n+1][n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            people[i] = parseInt(st.nextToken());
        }

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            int neighborCnt = parseInt(st.nextToken());
            for(int j=0;j<neighborCnt;j++){
                map[i][parseInt(st.nextToken())] = 1;
            }
        }
        
        // 조합을 구해야 함
        // r의 개수를 늘려가면서 콤비네이션
        for(int i=1;i<=n/2;i++){
            comb(0, 1, i, new boolean[n+1]);
        }

        if(minDiffPeople==Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(minDiffPeople);
        }


    }

    private static void comb(int depth, int start, int r, boolean[] isSelected) {
        // 기저 조건
        if(depth==r){
            ArrayList<Integer> aList = new ArrayList<>();
            ArrayList<Integer> bList = new ArrayList<>();
            int sumA=0;
            int sumB=0;
            // isSeleted라면 aList에 속하고 선택되지 않았으면 bList에 속한다
            // 사람의 수까지 더해주면 좋음!!
            for(int i=1;i<=n;i++){
                if(isSelected[i]){
                    aList.add(i);
                    sumA+=people[i];
                }else {
                    bList.add(i);
                    sumB+=people[i];
                }
            }

            boolean allConnected = true;
            // 모든 A의 리스트가 연결되어 있는지 확인하기
            boolean[] isVisited = new boolean[n+1];
            connectedCnt = 1;
            dfs(aList.get(0), aList, isVisited);
            if(connectedCnt!=aList.size()){
                allConnected = false;
            }

            // 모든 B의 리스트가 연결되어 있는지 확인하기
            isVisited = new boolean[n+1];
            connectedCnt = 1;
            dfs(bList.get(0), bList, isVisited);
            if(connectedCnt!=bList.size()){
                allConnected = false;
            }

            if(allConnected){
                minDiffPeople = Math.min(minDiffPeople, Math.abs(sumA-sumB));
            }
            return;
        }

        // 조합을 구성
        for(int i=start; i<=n;i++){
            isSelected[i] = true;
            comb(depth+1, i+1, r, isSelected);
            isSelected[i] = false;
        }
    }

    private static void dfs(int node, ArrayList<Integer> list, boolean[] isVisited) {
        isVisited[node] =true;
        for(int j=1;j<=n;j++){
            if(map[node][j] == 1 && !isVisited[j]){
                if(list.contains(j)) {
                    connectedCnt++;
                    dfs(j, list, isVisited);
                }
            }
        }
    }
}
