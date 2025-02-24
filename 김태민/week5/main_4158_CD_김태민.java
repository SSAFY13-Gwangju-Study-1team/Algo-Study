package Week5;

import java.io.*;
import java.util.*;

public class main_4158_CD_김태민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

//        HashSet<Integer> set = new HashSet<>();
        int[] Nmap = new int[n];
        int[] Mmap = new int[m];
        for(int i = 0; i<n; i++){
            Nmap[i]=Integer.parseInt(br.readLine());
        }
        for(int i = 0; i<m; i++){
            Mmap[i]=Integer.parseInt(br.readLine());
        }
        st = new StringTokenizer(br.readLine());

        int start = 0, end = 0, cnt=0;

        while(start<n &&end<m){

            if(Nmap[start] == Mmap[end]){
                cnt++;
                start++;
                end++;
            } else if(Nmap[start]>Mmap[end]){
                end++;
            } else {
                start++;
            }

        }


        System.out.println(cnt);

    }
}
