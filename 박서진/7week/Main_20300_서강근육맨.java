import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

/**
 * n이 홀수인지 짝수인지에 따라서 풀이가 달라지는걸 케치하면 된다
 * 출력 타입이 long이라는 것을 늦게 파악했다..
 */
public class Main_20300_서강근육맨 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] mustle = new long[n];
        for(int i=0;i<n;i++){
            mustle[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(mustle);
        int index1 =0;
        int index2 = n-1;
        // n이 홀수라면
        if(n>2 && n%2!=0){
            index2 = n-2;
        }
        long M = 0;
        while (index1<index2){
            M = Math.max(M, mustle[index1]+mustle[index2]);
            index1++;
            index2--;
        }

        // n이 홀수라면

        if(n%2!=0){
            M = Math.max(M, mustle[n-1]);
        }

        System.out.println(M);
    }
}
