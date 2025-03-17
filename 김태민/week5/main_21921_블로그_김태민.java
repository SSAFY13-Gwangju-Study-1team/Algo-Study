package Week5;

import java.io.*;
import java.util.*;

public class main_21921_블로그_김태민 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] map = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = x-1, cnt = 1, result = 0;
        int sum = 0;

        for(int i = 0; i<x; i++){
            result+=map[i];
        }
        sum = result;
        cnt = 1;

        while (end<n-1){
            sum += map[++end]-map[start++];

            if(sum>result){
                result = sum;
                cnt=1;
            } else if(sum == result){
                cnt++;
            }



        }
        if(result == 0){
            System.out.println("SAD");
        } else {
            System.out.println(result);
            System.out.println(cnt);
        }


    }
}
