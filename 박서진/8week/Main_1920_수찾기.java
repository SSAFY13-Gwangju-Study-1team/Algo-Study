import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1920_수찾기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = parseInt(br.readLine());
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            A[i] = parseInt(st.nextToken());
        }
        Arrays.sort(A);
        int m = parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            if(Arrays.binarySearch(A, parseInt(st.nextToken()))>=0){
                sb.append(1).append("\n");
            }else{
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }

}
