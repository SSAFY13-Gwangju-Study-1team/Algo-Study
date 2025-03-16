import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * hset으로 푸는것보다 0.1초 정도 빠르다, 메모리도 덜 쓴다
 */
public class Main_4158_CD {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = parseInt(st.nextToken());
            int m = parseInt(st.nextToken());
            if(n==0 && m==0) break;

            int[] sang = new int[n];
            int[] seon = new int[m];
            for(int i=0;i<n;i++){
                sang[i] = parseInt(br.readLine());
            }

            for(int i=0;i<n;i++){
                seon[i] = parseInt(br.readLine());
            }

            int sang_idx = 0;
            int seon_idx = 0;
            int cnt=0;
            while(true){
                if(sang_idx>=n || seon_idx>=m) break;
                if(sang[sang_idx]==seon[seon_idx]){
                    sang_idx++;
                    seon_idx++;
                    cnt++;
                }else if(sang[sang_idx]>seon[seon_idx]){
                    seon_idx++;
                }else {
                    sang_idx++;
                }
            }
            System.out.println(cnt);
        }


    }
}
