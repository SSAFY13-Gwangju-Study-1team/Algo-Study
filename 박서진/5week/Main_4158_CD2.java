import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_4158_CD2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
//            long startTime = System.currentTimeMillis(); // 코드 시작 시간
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = parseInt(st.nextToken());
            int m = parseInt(st.nextToken());
            if(n==0 && m==0) break;

            int[] cd = new int[n+m];
            for(int i=0;i<n+m;i++){
                cd[i] = parseInt(br.readLine());
            }

            HashSet<Integer> hset = new HashSet();
            for(int i:cd){
                hset.add(i);
            }

            System.out.println(cd.length- hset.size());
//            long endTime = System.currentTimeMillis(); // 코드 끝난 시간

//            long durationTimeSec = endTime - startTime;
//
//            System.out.println(durationTimeSec + "m/s"); // 밀리세컨드 출력

        }

    }
}
