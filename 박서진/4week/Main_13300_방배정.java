import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 반올림 처리가 가장 중요한 문제 -> 어디 부분에서 반올림을 할 지 결정하는 것이 중요함
 */
public class Main_13300_방배정 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parseInt(st.nextToken());
        int k = parseInt(st.nextToken()); // 인원 제한
        
        int[][] A = new int[7][2]; // 6학년 성별

        int res=0;
        
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int gender = parseInt(st.nextToken());
            int grade = parseInt(st.nextToken());

            A[grade][gender]+=1;
        }

        for(int i=1;i<=6;i++){
            for(int j=0;j<=1;j++){
                if (A[i][j]==0) continue;
                else{
                    res += (int) Math.ceil((double) A[i][j]/k); // 올림 처리 -> double로 계산한 후 소수점 한자리에서 올려서 정수형으로 올려줘야 함
                }
            }
        }
        System.out.println(res);
    }
}
