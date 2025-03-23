import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 시간: 96 ms
 * 구현 문제
 * for문 잘쓰는 문제
 * 조건에 맞지 않으면 처내는 방식으로 구현하였습니다
 * 활주로가 겹치는 구간에서 처리하는 것이 까다로웠습니다
 */
public class SWEA_4014_활주로건설 {
    static int n, x, res;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            x = parseInt(st.nextToken());
            map = new int[n][n];
            res = 0;
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    map[i][j] = parseInt(st.nextToken());
                }
            }
            
            //<------ 구현 ------->//
            //1. 가로 탐색
            OUT:
            for(int r = 0;r<n;r++){
                int currentCnt=1;
                int c=0;
                while(c<n){
                    if(c==n-1){
                        res++; // 마지막에 도달했을 때 res++ 추가
                        break;
                    }else{
                        if(map[r][c]==map[r][c+1]){
                            currentCnt++;
                        }else if(map[r][c]<map[r][c+1]){
                            if(Math.abs(map[r][c] - map[r][c+1])!=1) continue OUT;
                            if(currentCnt<x){
                                continue OUT;
                            }
                            currentCnt = 1;
                        }else{
                            if(Math.abs(map[r][c] - map[r][c+1])!=1) continue OUT;
                            if(c+x>=n) continue OUT;
                            for(int i=1;i<x;i++){
                                if(map[r][c+1]!=map[r][c+1+i]) continue OUT;
                            }
                            c += x-1;
                            if(c==n-1){
                                res++;
                            }
                            currentCnt = 0;
                        }
                    }
                    c++;
                }

            }
//            System.out.println(res);
            //2. 세로 탐색
            OUT:
            for(int c = 0;c<n;c++){
                int currentCnt=1;
                int r=0;
                while(r<n){
                    if(r==n-1){
                        res++; // 마지막에 도달했을 때 res++ 추가
                        break;
                    }else{
                        if(map[r][c]==map[r+1][c]){
                            currentCnt++;
                        }else if(map[r][c]<map[r+1][c]){
                            if(Math.abs(map[r][c] - map[r+1][c])!=1) continue OUT;
                            if(currentCnt<x){
                                continue OUT;
                            }
                            currentCnt = 1;
                        }else{
                            if(Math.abs(map[r][c] - map[r+1][c])!=1) continue OUT;
                            if(r+x>=n) continue OUT;
                            for(int i=1;i<x;i++){
                                if(map[r+1][c]!=map[r+1+i][c]) continue OUT;
                            }
                            r += x-1;
                            if(r==n-1){
                                res++;
                            }
                            currentCnt = 0;
                        }
                    }
                    r++;
                }

            }
            sb.append("#"+t+" "+res).append("\n");
        }
        System.out.println(sb);
    }
}
