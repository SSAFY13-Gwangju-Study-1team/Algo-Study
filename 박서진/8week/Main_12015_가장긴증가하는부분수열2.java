import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_12015_가장긴증가하는부분수열2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = parseInt(br.readLine());
        int[] arr = new int[n];
        ArrayList<Integer> result = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = parseInt(st.nextToken());
        }
        result.add(arr[0]);
        for(int i=1;i<n;i++){
            if(arr[i]>result.get(result.size()-1)){
                result.add(arr[i]);
            }else{
                int index = Collections.binarySearch(result, arr[i]); // -(들어갈자리+1)가 나옴
                if(index>=0) continue;
                result.set(-index-1, arr[i]); // 대치된 값 대체하기
            }
        }
        System.out.println(result.size());
    }
}
