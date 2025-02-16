import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 원형 큐의 구현
 * 너무 오래걸렸다 별로 어렵지 않은 문젠데
 * 원형큐에서 오른쪽에서 뽑아서 앞으로 가져와서 검사해야하는데 뒤에서 검사하다가 말렸다
 * peekLast와 peekFirst를 항상 조심해서 사용하고 어디서 검사가 이루어져야 하는지 체크하는 버릇을 들여야겠다
 */
public class Main_1021_회전하는큐 {
    static ArrayDeque<Integer> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n =parseInt(st.nextToken());
        int m =parseInt(st.nextToken());
        for(int i=1;i<=n;i++){
            q.add(i);
        }
        int[] want = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            want[i] = parseInt(st.nextToken()); // 원하는 배열의 인덱스를 받는다
        }

        int res=0;
        // 원하는 인덱스를 뽑는데 2, 3 연산 카운팅
        for(int i=0;i<m;i++){
            int key = want[i];
            int index = 0; //원하는 인덱스를 찾기
            for(int j: q){
                if(j==key)
                    break;
                index++;
            }
            // 찾은 인덱스를 어느 방향으로 접근하는 것이 좋은지 검사한다
            res += index <= q.size() / 2 ? popLeft(key) : popRight(key);

        }

        System.out.println(res);

    }
    // 외쪽 접근
    public static int popLeft(int key) {
        int cnt = 0;
        while (q.peekFirst() != key) { // 찾을 때까지 회전
            q.addLast(q.pollFirst()); // 왼쪽 회전
            cnt++;
        }
        q.pollFirst(); // key를 제거
        return cnt;
    }

    // 오른쪽 접근 -> 오른쪽에 있는 숫자를 앞으로 가져와서 제거해야한다.
    // 뒤에서 제거하고 체크하다가 계속 풀리지 않아서 혼났다.
    public static int popRight(int key) {
        int cnt = 0;
        while (q.peekFirst() != key) { // 찾을 때까지 회전
            q.addFirst(q.pollLast()); // 오른쪽 회전
            cnt++;
        }
        q.pollFirst(); // key를 제거
        return cnt;
    }

}
