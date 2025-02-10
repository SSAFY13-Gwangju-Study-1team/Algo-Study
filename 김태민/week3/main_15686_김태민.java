package argoStudy;

import java.util.*;
import java.io.*;


public class Main {
    static int n,m;
    static int sum;
    static int hCnt = 0;
    static int cCnt = 0;

    static int[][] arr;
    static int[][] home;
    static int[][] chicken;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m= Integer.parseInt(st.nextToken());


        arr = new int[n][n];
        home = new int[n*n][2];
        chicken = new int[13][2];
        sum = Integer.MAX_VALUE;

        // 입력값 받기
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        // 집의 위치랑 치킨집 위치를 미리 저장
        for(int i = 0 ; i<n; i++){
            for(int j = 0; j<n; j++){
                if(arr[i][j] == 1){
                    home[hCnt][0] = i;
                    home[hCnt][1] = j;
                    hCnt++;
                } else if (arr[i][j] == 2) {
                    chicken[cCnt][0] = i;
                    chicken[cCnt][1] = j;
                    cCnt++;
                }

            }
        }
        visited = new boolean[cCnt];
        backtrack(0,0);
        if(sum == Integer.MAX_VALUE){
            System.out.println(0);
        } else  {
            System.out.println(sum);

        }



    }

    public static void backtrack(int start, int cnt){

        // 종료조건
        if (cnt == m){
            int res = 0;

            for(int i = 0; i<hCnt; i++){
                int temp = Integer.MAX_VALUE;  // 한 집에서 가장 가까운 치킨집 고르고 저장

                for(int j = 0; j<cCnt; j++){
                    if(visited[j]){
                        int dist = Math.abs(chicken[j][0] - home[i][0]) + Math.abs(chicken[j][1]-home[i][1]);
                        temp = Math.min(temp, dist);
                    }
                }
                res +=temp; // 모든 치킨 거리의 합을 저장
            }
            sum = Math.min(sum, res); // 지금까지 합중 최솟값이랑 비교해서 더 작은거 저장
            return;
        }

        for(int i = start; i<cCnt; i++){
            visited[i] = true;
            backtrack(i+1,cnt+1);
            visited[i] = false;
        }
    }



}






