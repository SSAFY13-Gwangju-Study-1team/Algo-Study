import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 처음에는 첫 자리 수를 2-9까지 다 돌리면서 DFS에 넣었는데 그럴 필요없이
 * DFS에 2,3,5,7 만 진행해서 바로 구현할 수 있는걸 깨달았다
 * 또한 두번쨰 자리수(앞에서 부터)부터도 2의 배수가 나오면
 * 무조건 소수가 아니므로 소수이면서 홀수인걸 찾으면 더 빨리 문제를 풀 수 있을 것 같다
 * 다시 해 봤는데 시간이 더 늘더라
 */
public class Main_2023_신기한소수 {
    static StringBuilder sb = new StringBuilder();
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);

        System.out.println(sb);
    }

    // 다음 자리수의 숫자를 파악하는 함수 계속 깊이 내려가니깐 -> DFS
    public static void DFS(int x, int depth){
        if(depth==n)
            if (isPrime(x)){
                sb.append(x).append("\n");
                return;
            }

        if(isPrime(x)){
            for(int i=0;i<=9;i++){
                if(i%2==0) continue;
                String temp = Integer.toString(x) + Integer.toString(i);
                if(isPrime(parseInt(temp))){
                    DFS(parseInt(temp), depth + 1);
                }
            }

        }
    }

    // 소수임을 확인하는 함수
    public static boolean isPrime(int n){
        // n이 2보다 작을 수 없음
        for(int i=2;i<n;i++){
            if(n%i==0) return false;
        }
        return true;
    }
}
