import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 1. 지역들을 선거구 2개로 나누기
 * 2. 배정된 지역들이 연결되어 있는지 확인
 * 3. 최소값 갱신 or -1
 */
public class Main_17471_게리맨더링 {
    static int n;
    static int[] people;
    static int[][] map;
    static boolean[] isChoice;
    static int moveCnt;
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        people = new int[n+1];
        map = new int[n+1][n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            people[i] = parseInt(st.nextToken());
        }

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            int neighborCnt = parseInt(st.nextToken());
            for(int j=1;j<=neighborCnt;j++){
                int link = parseInt(st.nextToken());
                map[i][link] = 1;
            }
        }

        // 조합을 골라야 함 - 어떤 선거구에 포함시킬지
        // 1개 이상 선택해야하며 n-1까지 선택 가능하다
        // 발표에 의하면 A,B를 나누는데 절반이 겹치기 때문에 n/2까지 봐도 됨
        for(int r=1;r<=n/2; r++) {
            isChoice = new boolean[n+1];
            combination(1, 0, r);
        }

        res = (res==Integer.MAX_VALUE)?-1:res;
        System.out.println(res);

    }

    // 모든 지역을 선거구 A 혹은 선거구 B에 1개 이상 배정할 수 있는 모든 경우의 수
    // 조합 활요 -> 해당 지역을 선택한 경우(A 선거구에 배치) or 선택하지 않는 경우(B에 배치)
    public static void combination(int start, int depth, int r){
        if(depth==r){
            // 같은 선거구로 배정된 지역이 모두 인접한지 확인해야함
            ArrayList<Integer> choiceList = new ArrayList<>();
            ArrayList<Integer> notChoiceList = new ArrayList<>();

            int choiceSum = 0;
            int notChoiceSum = 0;

            boolean isConnected = true;
            for(int i=1;i<=n;i++){
                // 선택을 했으면 A에 포함되는 것
                if(isChoice[i]){
                    choiceList.add(i);
                    choiceSum+=people[i]; // 선택한 지역의 사람만큼 더해준다
                }else{
                    notChoiceList.add(i);
                    notChoiceSum+=people[i]; // 선택하지 않은 지역의 사람만큼 더해준다
                }
            }

            // 선거구 A로 배정된 지역을 dfs로 어디까지 갈 수 있는지 확인
            // 연결되어 있는지 확인
            // 연결된 노드들을 방문하며 총 몇개인지 확인
            moveCnt=1;
            boolean[] isVisited = new boolean[n+1];
            // 처음 노드를 먼저 넣고 시작
            isVisited[choiceList.get(0)] =true;
            dfs(choiceList.get(0), choiceList, isVisited);
            if(moveCnt!=choiceList.size()) isConnected =false;

            // 선거구 B로 배정된 지역을 dfs로 어디까지 갈 수 있는지 확인
            moveCnt=1;
            isVisited = new boolean[n+1];
            // 처음 노드를 먼저 넣고 시작
            isVisited[notChoiceList.get(0)] =true;
            dfs(notChoiceList.get(0), notChoiceList, isVisited);
            if(moveCnt!=notChoiceList.size()) isConnected =false;

            if(isConnected){
                res = Math.min(res, Math.abs(choiceSum-notChoiceSum));
            }

            return;
        }
        for(int i=start; i<=n;i++){
            isChoice[i] = true; //A 선거구에 배치
            combination(i+1, depth+1, r);
            isChoice[i] =false; //B 선거구에 배치
        }
    }

    //배정된 지역을 dfs로 어디까지 갈 수 있는지 확인
    private static void dfs(int node, ArrayList<Integer> visitList, boolean[] isVisited) {
        for(int i=1;i<=n;i++){
            // 현재 노드랑 연결되어 있고 아직 방문하지 않았다면
            if(map[node][i] == 1 && !isVisited[i]){
                // 내가 가지고 있는 리스트에 그 노드가 있다면
                for(int j=0;j<visitList.size();j++){
                    if(visitList.get(j)==i) {
                        isVisited[i] = true;
                        moveCnt++;
                        dfs(i, visitList, isVisited);
                    }
                }
            }
        }
    }
}
