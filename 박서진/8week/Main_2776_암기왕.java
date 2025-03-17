import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2776_암기왕 {
    static int n, m;
    static int[] arr1, arr2;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=0;t<tc;t++) {
            n = parseInt(br.readLine());
            arr1 = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++) {
                arr1[i] = parseInt(st.nextToken());
            }
            Arrays.sort(arr1); // 1번 정렬
            m = parseInt(br.readLine());
            arr2 = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<m;i++) {
                arr2[i] = parseInt(st.nextToken());
            }
            for(int i=0;i<arr2.length;i++) {
                int res = binarySearch(arr2[i]);
                sb.append(res+"\n");
            };
        }
        System.out.println(sb);
    }

    private static int binarySearch(int key) {
        int left = 0;
        int right = arr1.length-1;
        int mid;
        while(left<=right) {
            mid = (left + right)/2;
            if(arr1[mid] == key) {
                return 1;
            }else if(arr1[mid]>key) {
                right = mid -1;
            }else {
                left = mid + 1;
            }
        }
        return 0;
    }



}
