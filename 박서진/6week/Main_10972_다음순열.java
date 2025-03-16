import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * np 문제 다시 풀기!!!!
 * 나중에 조합도 풀어봐야겠다
 */
public class Main_10972_다음순열 {
    static int n;
    static int[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i] = parseInt(st.nextToken());
        }
        
        if(np()){
            for(int i:nums){
                System.out.print(i+ " ");
            }
        }else{
            System.out.println(-1);
        }
    }

    public static boolean np(){
        int i = n-1;
        while(i>0 && nums[i-1]>=nums[i]) --i;
        if(i==0) return false;
        // 바꿀 자리를 찾는 부분
        // 뒤에서 i-1보다 큰 값 중 가장 작은 값
        // 고로 같거나 작은 값을 골라서(어차피 정렬되어 있기 때문에) 그 다음 값을 선택한다
        int j = n-1;
        while(nums[i-1]>=nums[j]) j--; // 무조건 존재하기 때문에 예외 없음

        swap(i-1, j);
        
        // i부터 오름차순으로 다시 만들어준다
        int k = n-1;
        while(i<k) swap(i++, k--);
        return true;
    }
    public static void swap(int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
