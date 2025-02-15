package PACKAGE_NAME;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
public class Main_01알고리즘기본_gravity {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxLine = Arrays.stream(nums).max().getAsInt();

        int[] cnt = new int[maxLine+1];
        int max = nums[0];
        for(int i=0;i<n-1;i++){
            int start = nums[i+1];
            if(max<start){
                max = start;
                continue;
            }else{
                for(int j=start;j<max;j++){
                    cnt[j]++;
                }
            }
        }
        int res = Arrays.stream(cnt).max().getAsInt();
        System.out.println(res);
    }
}
