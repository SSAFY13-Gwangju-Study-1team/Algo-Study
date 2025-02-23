package PACKAGE_NAME;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class SWEA_gravity {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testcase = parseInt(br.readLine());
        for(int t=1;t<=testcase;t++){
            int n = parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] box = new int[n];
            for(int i=0;i<n;i++){
                box[i] = parseInt(st.nextToken());
            }
            int max_length = 0;
            for(int i=0;i<n;i++){
                int cnt=0;
                for(int j=i;j<n;j++){
                    if(box[i]>box[j]) cnt++;
                }
                max_length = Math.max(cnt, max_length);
            }
            System.out.println("#"+t+" " +max_length);
        }
    }
}
