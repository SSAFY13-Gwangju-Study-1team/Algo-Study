import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_14510_나무높이 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = parseInt(br.readLine()); // 나무 개수
            int[] trees = new int[n]; // 나무들 배열
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 나무 배열 입력 받기
            for(int i=0;i<n;i++){
                trees[i] = parseInt(st.nextToken());
            }

            // 물주기
            // 가장 큰 나무
            int max_tree = Arrays.stream(trees).max().getAsInt();
            // 배열을 돌면서 필요한 몰의 양을 2로 나눠서 필요한 날을 계산
            // 짝수날, 홀수 날
            int even=0, odd=0;

            for(int i:trees){
                int need = max_tree-i;
                even += need/2;
                odd += need%2;
            }

            // 최종적인 필요한 날
            int res=0;
            // e
            if (even>=odd){
                int remain = even-odd;
                if (remain % 3 == 1) res+=2;
                else if (remain % 3 == 2) res+=3;

                res += odd * 2 + (remain / 3) * 4;
            }else{
                res = odd*2 -1;
            }



            System.out.println("#"+test_case+" "+res);

        }

    }
}
