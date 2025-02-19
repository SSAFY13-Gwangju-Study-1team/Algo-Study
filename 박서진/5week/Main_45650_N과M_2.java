import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 메모리 15880 시간 128ms
 * 오름차순으로 순열을 구하는 문제는 -> 조합으로 구할 수 있음(자리가 정해져 있기 때문)
 * 원래는 for문으로 start부터 쓰는걸로 푸는데 오늘 준영이오빠가 알려준 방법으로 풀었다
 * 그 부분에 방문 하는 경우와 그렇지 않은 경우를 나누어서 해주면 된다
 * 이 방식에 익숙해 지려고 노력해야 겠다
 */
public class Main_45650_N과M_2 {
    static int n, m;
    static boolean isVisited[];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());

        backtracking(0, 1, new ArrayList<Integer>());
    }
    public static void backtracking(int depth, int target, ArrayList<Integer> arr){
        if(depth==m){
            for(int i:arr){
                System.out.print(i+ " ");
            }
            System.out.println();
            return;
        }
        if(target>n) return;

        // 새로운 리스트를 만들어 넘겨줌 (원본 리스트 수정 X)
        ArrayList<Integer> new_arr = new ArrayList<>(arr);
        new_arr.add(target);

        // 조합에 target 넣는 경우
        backtracking(depth+1, target+1, new_arr);
        // 조합에 target 넣지 않는 경우
        backtracking(depth, target+1, arr);
    }
}
