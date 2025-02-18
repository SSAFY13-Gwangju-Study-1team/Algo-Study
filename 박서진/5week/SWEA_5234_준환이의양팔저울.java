import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 어려웠다.. 처음에는 모든 순열을 리스트로 구하고 거기서 오른쪽 배열을 비교하며 개수를 구하려다 실패...
 * 백트래킹을 이용해서 모든 경우의 수를 탐색할 수 있다. 순서가 중요하니 순열의 백트래킹을 설계한다
 * 현재 추를 왼쪽에 올리거나 혹은 오른쪽에 올리거나 두가지의 방법이 있으므로 두가지의 경우를 돌리고
 * 오른쪽의 경우에는 범위 체크까지 해주면 되는 문제
 *
 */
public class SWEA_5234_준환이의양팔저울 {
    static int n;
    static int weights[];
    static boolean isVisited[];
    static int total_cnt=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int test_case = parseInt(br.readLine());
        for(int t=1;t<=test_case;t++) {
            n = parseInt(br.readLine());
            weights = new int[n]; // 추의 배열
            isVisited = new boolean[n];
            total_cnt=0;
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++) {
                weights[i]=parseInt(st.nextToken());
            }

            backtrack(0,0,0);
            System.out.println("#" + t + " " + total_cnt);
        }
    }


    /**
     * 백트래킹을 이용한 모든 경우 탐색
     * @param depth      현재까지 올린 추의 개수 (재귀 깊이)
     * @param left_sum   왼쪽 저울의 총 무게
     * @param right_sum  오른쪽 저울의 총 무게
     */
    private static void backtrack(int depth, int left_sum, int right_sum) {
        if(depth==n) {
            if(left_sum>=right_sum) { // 왼쪽이 오른쪽 보다 크거나 같아야 함
                total_cnt++;
            }
            return;
        }

        for(int i = 0;i<n;i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;

                //현재 추를 왼쪽 저울에 올리는 경우
                backtrack(depth+1, left_sum+weights[i], right_sum);

                // 오른 쪽 추의 무게가 왼쪽을 넘기지 않는 경우에 현재 추를 오른쪽에 올리는 경우
                if(right_sum+weights[i]<=left_sum) {
                    backtrack(depth + 1, left_sum, right_sum + weights[i]);
                }
                isVisited[i] = false;

            }
        }

    }

}
