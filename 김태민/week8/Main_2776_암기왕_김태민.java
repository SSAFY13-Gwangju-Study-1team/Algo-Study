package Week8;

import java.util.*;
import java.io.*;

/**
 * 메모리: 230204kb
 * 실행시간: 1636ms
 *
 *
 */

public class Main_2776_암기왕_김태민 {
    static int n, m;
    static int[] map1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 0; t<T; t++){
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            map1 = new int[n];
            for(int i = 0; i<n; i++){
                map1[i] = Integer.parseInt(st.nextToken());
            }
            m = Integer.parseInt(br.readLine());
            Arrays.sort(map1);
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<m; i++){
                int a = Integer.parseInt(st.nextToken());
                if(find(a)){
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }

            }

        }
        System.out.println(sb.toString());
    }

    static boolean find(int k){
        int left = 0;
        int right = n-1;
        while(left<=right){
            int mid = (right+left)/2;
            if(map1[mid]==k){
                return true;
            }

            if(map1[mid]>k){
                right = mid-1;
            } else {
                left = mid+1;
            }

        }
        return false;
    }
}













