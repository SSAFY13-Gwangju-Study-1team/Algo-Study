import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * n일에 거쳐서 선물을 줄 때 선물의 합이 소수가 되면 안됨
 */
public class Main_30021_순열선물하기 {
    static int n;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();
    static boolean flag;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        isVisited = new boolean[n+1];
        flag =false;
        perm(0, new int[n], 0);

        if(sb.length()==0){
            sb.append("NO");
        }
        System.out.println(sb);

    }
    public static void perm(int depth, int[] arr, int sum){
        if(isPrime(sum)) return;
        if(depth == n){
            if(!isPrime(sum)) {
                flag=true;
                sb.append("YES" + "\n");
                for (int i : arr) {
                    sb.append(i + " ");
                }
                sb.append("\n");
            }
            return;
        }

        for(int i=1;i<=n;i++){
            if(isVisited[i]) continue;
            isVisited[i] =true;
            arr[depth] = i;
            perm(depth+1, arr, sum+i);
            isVisited[i] =false;
            if(flag) return;
        }
    }

    private static boolean isPrime(int sum) {
        if(sum==1 || sum==0) return false;
        else if(sum==2) return true;
        for(int i=2; i<=Math.sqrt(sum);i++){
            if(sum%i==0) return false;
        }
        return true;
    }
}
