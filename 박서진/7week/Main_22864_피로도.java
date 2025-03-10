import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_22864_피로도 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = parseInt(st.nextToken());
        int B = parseInt(st.nextToken());
        int C = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int work = 0;
        int tired=0;
        for(int i=0;i<24;i++){
            if(A>M) break;
            if(A+tired<=M){
                work+=B;
                tired+=A;
            }else{
                tired-=C;
                if(tired<0) tired=0;
            }
        }
        System.out.println(work);
    }
}
