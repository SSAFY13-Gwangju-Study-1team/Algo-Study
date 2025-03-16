import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 시간: 1364ms(제한 2초)
 * dfs로 한자리씩 확장해 가면서 푸는 전략을 택해습니다.
 * 한자리수 늘어날 때마다 소수 확인을 하고 맞으면 다음 단계를 진행하는 로직을 구현하였습니다.
 */
public class Main_2023_신기한소수2 {
    static int n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());

        //자리수를 만들어서 숫자를 넣고 소수인지 확인
        // 한자리수 소수는 2,3,5,7만 있으니 이 부분만 확인
        dfs(2,0);
        dfs(3,0);
        dfs(5,0);
        dfs(7,0);
        System.out.println(sb);

    }

    private static void dfs(int target, int depth) {
        if(depth==n-1){
            if(isPrime(target)) {
                sb.append(target).append("\n");
            }
            return;
        }

        //누적된 값이 소수가 아니라면 return
        if(!isPrime(target)) return;

        // 다음 자리수를 정하고 dfs 돌리기
        for(int i=1;i<=9;i++){
            if(i%2!=0){
                int temp = target*10+i;
                if(isPrime(temp)) {
                    dfs(temp, depth + 1);
                }
            }
        }

    }

    // 소수인지 확인하는 함수
    private static boolean isPrime(int n){
        if(n==2) return true;
        for(int i=2;i<n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}
