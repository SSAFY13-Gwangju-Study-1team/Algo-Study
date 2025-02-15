import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
/**
 * 1짜리 물뿌리개와 2짜리 물뿌리개를 무조건 동시에 사용해야 하므로
 * 갊자가 바라는 나무의 높이들의 합은 무조건 3의 배수여야 함
 *
 * 또한 1짜리 물뿌리개는 어디에든 사용할 수 있지만 2짜리 물뿌리개는 남은 높이가 1이하일 경우 사용하지 못하기 때문에
 * 2짜리 물뿌리개를 몇 번 사용할 수 있느냐에 따라 정답이 결정됨
 *
 * 만약 2짜리 물뿌리개를 사용할 수 없는 횟수가 2짜리 물뿌리개를 사용할 수 있는 횟수 보다 크다면 해당 나무들의 높이는 만들지 못한다.
 * 2이상의 높이는 1짜리 물뿌리개를 사용할 수 있지만 1이하의 높이는 2짜리 물뿌리개를 사용할 수 없기 때문
 */
public class Main_19539_사과나무2 {
    static int[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken()); // 사과 나무 개수
        trees = new int[n];
        st= new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            trees[i] = parseInt(st.nextToken());
        }

        int total = 0;
        int cnt1 = 0; // 2로 나눈 나머지
        int cnt2 = 0; // 2로 나눈 몫
        for(int i:trees) {
            total+=i;
            cnt1+=i%2;
            cnt2+=i/2;
        }

        // 전체가 3의 배수가 아니면 탈락
        if(total%3!=0) {
            System.out.println("NO");
            return;
        }
        if(cnt1<=cnt2){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }

}
