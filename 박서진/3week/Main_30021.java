import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main_30021 {
    static List<Integer> res = new LinkedList<>(); // 결과 받을 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int n = parseInt(br.readLine());
        List<Integer> arr = new LinkedList<>(); // n까지 배열 담기
        for(int i=0;i<n;i++) {
            arr.add(i+1);
        }

        int sum=0; // 선물의 총합
        int idx=0;

        // n일 동안 i번 째 날 의 선물 지정
        while(!arr.isEmpty() && idx<arr.size()) {
            sum+=arr.get(idx);
            if (isPrime(sum)) { // 합이 소수라면 res에 넣기
                sum-=arr.get(idx);
                idx++;
            }else {
                res.add(arr.get(idx));
                arr.remove(idx);
                idx=0;
            }
        }

        if (res.size()!=n) {
            sb.append("NO").append("\n");
        }else {
            sb.append("YES").append("\n");
            for(int i=0;i<res.size();i++) {
                sb.append(res.get(i)).append(" ");
            }
        }

        System.out.println(sb.toString());
    }
    public static boolean isPrime(int x) {
        if (x <= 1) return false;
        for(int i=2;i<x;i++) {
            if (x%i==0) {
                return false;
            }
        }
        return true;
    }
}
