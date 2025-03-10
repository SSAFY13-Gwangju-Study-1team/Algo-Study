package Week5;

import java.io.*;
import java.util.*;

public class Main_1940_주몽_김태민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] map = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(map);
        int start =0, end = n-1;
        int cnt = 0, sum = 0;
        while(start<end){
            sum = map[start]+map[end];
            if(sum == m){
                cnt++;
                end--;
                start++;
            } else if(sum>m){
                end--;
            } else {
               start++;
            }
        }
        System.out.println(cnt);

    }
}
