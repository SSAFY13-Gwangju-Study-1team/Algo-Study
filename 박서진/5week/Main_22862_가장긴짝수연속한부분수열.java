import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 메모리 91224kb 시간 440ms
 * 투 포인터로 쉽게 풀 수 있었던 문제인데 반복문 종료후 정답이 끝에 나오는 경우를
 * 생각하지 못해서 계속 실패했습니다..엣지케이스를 찾을 수가 없어서 gpt의 도움으로
 * 실수를 찾을 수 있었습니다....
 */
public class Main_22862_가장긴짝수연속한부분수열 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int k = parseInt(st.nextToken()); // 홀수 개수
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            arr[i] = parseInt(st.nextToken());
        }

        int front=0; 
        int rear =0;
        int odd_cnt = 0;
        int even_cnt = 0;
        int max_cnt= 0;

        while(rear<n) {
            if(odd_cnt>k) { // 홀수의 개수가 k보다 크다면 front를 올려준다
                if(arr[front]%2!=0) odd_cnt--; // 현재 front가 가리키고 있는 값이 홀수 이면 홀수를 하나 줄여주고
                else even_cnt--; //반대면 짝수를 줄여준다
                front++;
            }else {    // 홀수의 개수가 k보다 작으면 rear를 올려준다 -> 크기를 키운다
                if(odd_cnt==k) { // 홀수가 k일때가 언제나 최대이기 때문에 이때만 max 갱신
                    max_cnt = Math.max(max_cnt, even_cnt);
                }
                if (arr[rear] % 2 != 0) odd_cnt++; // 여기는 rear를 마지막에 올리는데 이유는 처음 0 0 이었을 때부터 들어가기 위함이다
                else even_cnt++;
                rear++; // rear를 올리기
            }
        }
        // 반복문 끝나고 rear가 n-1이 었을 때 값
        if(odd_cnt<=k) {
            max_cnt = Math.max(max_cnt, even_cnt);
        }

        System.out.println(max_cnt);
    }
}
