import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 백트래킹 문제 n자리 순열을 만들면서 연속된 중복된 순열이 없도록 하는 문제입니다.
 */
public class Main_2661_좋은수열 {
    static boolean flag;
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        flag = false;
        backtrack(0, n, "");
    }
    public static void backtrack(int depth, int n, String res){
        //n자리 숫자를 만들어야 하며
        // 만들어진 값중에 가장 작은 값을 넣어야 한다.
        if(depth==n){
            System.out.println(res);
            flag =true;
            return;
        }
        // 1을 넣을 때
        if(isAvailable(res+"1") && !flag)
            backtrack(depth+1, n, res+"1");
        // 2를 넣을 때
        if(isAvailable(res+"2")&& !flag)
            backtrack(depth+1, n, res+"2");
        // 3을 넣을 때
        if(isAvailable(res+"3")&& !flag)
            backtrack(depth+1, n, res+"3");

        if(flag) return;

    }
    public static boolean isAvailable(String x){
        char[] arr = x.toCharArray();
        int i=arr.length-1;
        int j=arr.length-2;
        while(j>=0){
            // 구간을 정하고 그 안에 겹치는게 있는지 체크
            // 구간은 짝수로 커짐: 2 -> 4 -> 6 ...
            // j는 2씩 줄어들고 i는 고정, 앞에 절반은 s1에 남은 절반은 s2에 삽입
            int mid = (i+j)/2;
            String s1="", s2="";
            for(int s=j;s<=mid;s++){
                s1+=arr[s]+"";
            }
            for(int s=mid+1;s<=i;s++){
                s2+=arr[s]+"";
            }
            if(s1.equals(s2)){
                return false;
            }
            j-=2;
        }
        return true;
    }
}
