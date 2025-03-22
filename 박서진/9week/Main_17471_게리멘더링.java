import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_17471_게리멘더링 {
    static int n;
    static ArrayList<Integer> A;
    static ArrayList<Integer> B;
    static int[] people;
    static int[][] matrix;
    static int connectCnt;
    static int minPeople = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        people = new int[n+1];
        matrix = new int[n+1][n+1];
        minPeople = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            people[i] = parseInt(st.nextToken());
        }
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            int neighborCnt = parseInt(st.nextToken());
            for(int j=0;j<neighborCnt;j++){
                int connect = parseInt(st.nextToken());
                // 양쪽으로 연결해 줍니다
                matrix[i][connect] = 1;
                matrix[connect][i] = 1;
            }
        }
        // 1부터 n-1까지 조합을 구해야 함
        // 반을 잘랐을 때 대칭이기 때문에 반까지만 구해도 됨
        for(int i=1;i<=n/2;i++){
            combination(0, 1, i, new boolean[n+1]);
        }
        if(minPeople==Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(minPeople);
        }
    }

    // 중복이 되지 않는 조합을 구한다
    private static void combination(int depth, int start, int r, boolean[] isSelected) {
        if(depth==r){
            // 조합이 만들어 졌다면 선택이 된 지역은 A에 넣고 
            // 나머지는 B에 
            // 함께 각 사람들 수도 구해준다
            int peopleA = 0, peopleB=0;
            A = new ArrayList<>();
            B = new ArrayList<>();
            for(int i=1;i<=n;i++){
                if(isSelected[i]){
                    A.add(i);
                    peopleA+=people[i];
                }else{
                    B.add(i);
                    peopleB+=people[i];
                }
            }
            //dfs를 통해 지역들이 모두 연결되어 있는지 확인한다
            boolean[] isVisited = new boolean[n+1];
            connectCnt=1;
            dfs(isVisited,A.get(0), A);
            int resA = connectCnt;
            isVisited = new boolean[n+1];
            connectCnt=1;
            dfs(isVisited,B.get(0), B);
            int resB = connectCnt;

            boolean allConnected = false;
            if(resA==A.size() && resB==B.size()){
                allConnected = true;
            }
            // 다 연결 되지 않으면 return
            if(!allConnected) return;

            int peopleDiff = Math.abs(peopleA - peopleB);
            minPeople = Math.min(peopleDiff, minPeople);

            return;
        }
        
        for(int i=start;i<=n;i++){
            isSelected[i] =true;
            combination(depth+1, i+1, r, isSelected);
            isSelected[i] =false;
        }

    }

    private static void dfs(boolean[] isVisited, int start, ArrayList<Integer> list) {
        if(isVisited[start]) return;
        isVisited[start] = true;
        for(int j=1;j<=n;j++){
            if(matrix[start][j] == 1 &&!isVisited[j]){
                if(list.contains(j)){
                    connectCnt++;
                    dfs(isVisited, j, list);
                }
            }
        }

    }
}
