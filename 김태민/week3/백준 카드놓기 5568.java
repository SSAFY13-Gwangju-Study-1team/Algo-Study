package argoStudy;

import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] nums;

    // 중복된 값을 피하기 위해 set을 사용
    static Set<String> set;

    // 한번쓴 값은 다시 사용하지 않기 위해 쓴 값은 true
    // 쓰지 않은 값은 false
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        nums = new int[n];
        set = new HashSet<>();
        used = new boolean[n];
        for(int i = 0; i<n; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        backtrack(0,"");
        System.out.println(set.size());


    }

    public static void backtrack(int cnt, String value){

        // k개 만큼 카드를 선택하면 return
        // 처음에 value를 int로 했다가 답이 계속 이상하게 나와서 왜 틀렸는지 몰랐는데
        // 12+1 = 121이 아니라 13인걸 꺠달아서 String으로 바꾸니 해결완료
        if (cnt == k){
            set.add(value.trim());
            return;
        }

        for (int i = 0; i<n;i ++){
            if (!used[i]){
                used[i] = true;
                backtrack(cnt+1,value+nums[i]);
                used[i] = false;
            }
        }
    }
}
