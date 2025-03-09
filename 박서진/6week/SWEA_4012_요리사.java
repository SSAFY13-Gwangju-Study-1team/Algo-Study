import java.io.*;
import java.util.*;

import static java.lang.Integer.*;

public class SWEA_4012_요리사 {
    static int n, sumA, sumB;
    static int minRes = Integer.MAX_VALUE;
    static int[] A;
    static int[] B;
    static int[][] C;
    static boolean[] isVisited;
    static boolean[] isSelected;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            n = parseInt(br.readLine());
            A = new int[n/2];
            B = new int[n/2];
            C = new int[n+1][n+1];
            isSelected = new boolean[n+1];
            isVisited=new boolean[n/2];
            minRes=Integer.MAX_VALUE;;
            sumA=0;
            sumB=0;
            for(int i=1;i<=n;i++){
                StringTokenizer st= new StringTokenizer(br.readLine());
                for(int j=1;j<=n;j++){
                    C[i][j] = parseInt(st.nextToken());
                }
            }
            // 조합을 구해준다 -> A, B 각각에 들어갈 식재료를 만들어준다
            comb(0, 1);
            sb.append("#"+t+" "+minRes+"\n");
        }
        System.out.println(sb);

    }

    // 중복을 혀용하지 않는 조합을 골라서 A에 넣고 나머지는 B에 넣는 작업을 한다
    public static void comb(int depth, int start){
        if(depth==n/2){
            //A조합 완성
            //B조합 만들어주기
            int index = 0;
            for(int i=1;i<=n;i++){
                if(isSelected[i]) continue;
                B[index++] = i;
            }
            // 순열을 통해 값의 차이 구하기
            // A 순열 먼저 구하기
            isVisited=new boolean[n/2];
            sumA=0;
            permA(0, new int[2]);
            // B 순열 구하기
            isVisited=new boolean[n/2];
            sumB=0;
            permB(0, new int[2]);
            minRes = min(minRes, Math.abs(sumA-sumB));
            return;
        }
        for(int i=start;i<=n;i++){
            A[depth] = i;
            isSelected[i] = true;
            comb(depth+1, i+1);
            isSelected[i] = false;
        }
    }

    private static void permA(int depth, int[] arr) {
        if(depth==2){
            int n1= arr[0];
            int n2 = arr[1];
            sumA += C[n1][n2];
            return;
        }

        for(int i=0;i<n/2;i++){
            if(isVisited[i]) continue;
            isVisited[i] = true;
            arr[depth] = A[i];
            permA(depth+1, arr);
            isVisited[i] =false;
        }


    }
    private static void permB(int depth, int[] arr) {
        if(depth==2){
            int n1= arr[0];
            int n2 = arr[1];
            sumB += C[n1][n2];
            return;
        }
        for(int i=0;i<n/2;i++){
            if(isVisited[i]) continue;
            isVisited[i] = true;
            arr[depth] = B[i];
            permB(depth+1, arr);
            isVisited[i] =false;
        }
    }
}
