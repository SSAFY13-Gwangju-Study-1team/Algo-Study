import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 2개의 재료의 합
 * 처음에 문제를 잘 못 이해해서 이상한데로 빠짐ㅜㅜㅜ
 * 문제를 잘 일자라는 교훈을 얻었다
 * 정렬 후 투 포인터를 이용해서 배열에서의 갑옷 재료의 합을 구할 수 있었다
 * 수업 시간에 배운 내용이라 빠르게 접근할 수 있었던 문제이다.
 */
public class Main_1940_주몽 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = parseInt(br.readLine());
        int m = parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] A = new int[n];
        for(int i=0;i<n;i++){
            A[i] = parseInt(st.nextToken());
        }

        Arrays.sort(A);
        int front =0, rear=n-1;
        int res=0;

        int sum = 0;
        while(front<rear){ // 2개 이므로 두개가 같아서는 안됨
            sum = A[front]+A[rear];
            if(sum==m){
                res++;
            }
            if(sum<=m) front++;
            else rear--;
        }
        System.out.println(res);
    }
}
