import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_17951_흩날리는시험지속에서 {
    static int n, k, total;
    static int[] scores;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=parseInt(st.nextToken());
        k=parseInt(st.nextToken());
        total = 0;
        scores = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            scores[i] =parseInt(st.nextToken());
            total+=scores[i];
        }
        // 시험 점수가 max인 값을 구한다
        int result = parametricSearch();
        System.out.println(result);
    }

    private static int parametricSearch() {
        int left = 0;
        int right = total;
        int mid;
        int result=0;
        while(left<=right){
            mid = (left+right)/2;
            int cnt=0;
            int sum = 0;
            // 시험지의 합을 구해서 그룹이 몇개로 나뉘는지 확인한다
            // 시험지를 그룹으로 나누기
            for (int i = 0; i < n; i++) {
                sum += scores[i];
                if(sum>=mid){
                    cnt++;
                    sum=0;
                }
            }
            if(cnt>=k){
                result = mid;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return result;
    }
}
