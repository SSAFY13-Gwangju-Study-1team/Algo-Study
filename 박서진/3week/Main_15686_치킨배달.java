import java.util.*;
import java.io.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

/**
 * backtracking 문제
 * 치킨과 집 간 거리의 모든 경우의 수를 돌면서 최소 값 갱신
 * 치킨집을 기준으로 앞에서 고르지 않았던 치킨집만 골라야 시간 초과 나지 않음
 * 최소 값만 구하면 되는 문제니 어떤 치킨집을 골라야 하는 지는 중요하지 않음
 */
public class Main_15686_치킨배달 {
    static List<int[]> arr1 = new ArrayList<>();; // 집 위치 정보 리스트
    static List<int[]> arr2 = new ArrayList<>();; // 치킨집 위치 정보 리스트
    static boolean[] isVisited;
    static int m;
    static int chicken_res = MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());



        for(int i=0;i<n;i++){
            String[] line = br.readLine().split(" ");
            for(int j=0;j<n;j++){
                int input = parseInt(line[j]);
                if(input == 1){
                    arr1.add(new int[]{i, j});
                }else if (input==2){
                    arr2.add(new int[]{i, j});
                }
            }
        }
        isVisited = new boolean[arr2.size()];
        backtrack(0,0);
        System.out.println(chicken_res);

    }

    /**
     * start를 기준으로 m만큼의 치킨집을 선택하며 각각의 선택에서의 치킨 거리가 구해짐
     * @param start
     * @param depth
     */
    public static void backtrack(int start, int depth){
        // 종료 조건문
        if(depth==m){
            int min_sum=0;
            for(int i=0;i<arr1.size();i++){
                int temp_sum = MAX_VALUE;
                for(int j=0;j<arr2.size();j++){
                    if(isVisited[j]) {
                        int length = calcLength(arr2.get(j), arr1.get(i));
                        temp_sum = Math.min(temp_sum, length);
                    }
                }
                min_sum+=temp_sum;
            }
            chicken_res = Math.min(min_sum,chicken_res);
            return;
        }

        // start 안 넣고 모든 경우의 수 다 보면 시간초과
        for(int i=start;i<arr2.size();i++){
            isVisited[i]=true;
            backtrack(i+1, depth+1);
            isVisited[i]=false;
        }
    }

    /**
     * 치킨집과 집 사이의 거리를 반환
     * @param home
     * @param chicken
     * @return
     */
    public static int calcLength(int[] home, int[] chicken){
        return Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
    }
}
