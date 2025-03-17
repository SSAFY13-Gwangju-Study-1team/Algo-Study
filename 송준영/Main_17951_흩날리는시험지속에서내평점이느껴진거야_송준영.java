import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_17951_흩날리는시험지속에서내평점이느껴진거야_송준영
 * 난이도 5?/10
 * 이분탐색 파라메트릭 서치
 * 260ms 23mb
 * 
 * 전형적인 파라매트릭 서치 문제지만 파라매트릭을 위한 메서드를 생각해내는 것과 범위 지정이 까다로운 문제지만
 * 나도 왜 맞은지 모르겠음
 * 찾는 기준을 그룸 최저 점수로 두고 서치하면 되는데
 * 그룹을 몇개 만들 수 있는지를 구하는 메서드를 만들어서 이분탐색을 돌리면 된다
 * 해당 점수의 그룹 개수보다 크면 결과를 갱신하고 start를 mid+1로 옮겨주고
 * 아니면 end를 mid-1로 옮겨주면 된다
 * 같을때는 왜 범위를 낮은 쪽으로 줄여주냐면 -> 그걸 모르겠음
 * 
 * GPT 曰:
 * 만약 if (countGp >= K)를 사용하게 되면,

    countGp == K인 경우에도 “만들 수 있다”고 판단해 버립니다.
    하지만 실제 유효 그룹은 countGp - 1 = K - 1개밖에 안 되므로, 이는 잘못된 해석이 됩니다.
    그 결과, “더 큰 mid 값”을 탐색해 버려서 답이 틀어질 수 있습니다.

    코드가 맞는 이유

    countGroups(mid)가 반환하는 값에는 초기 그룹 카운팅(cnt = 1) 때문에 +1이 가산되어 있습니다.
    실제 “mid 이상인 그룹”은 countGroups(mid) - 1개입니다.
    따라서 K개 이상을 만족하려면 countGroups(mid) - 1 >= K → countGroups(mid) > K 여야 합니다.
    그래서 if (countGp > K) 조건을 써야 “실제로 K개 이상의 유효 그룹”을 만들 수 있는지를 올바르게 판단하게 됩니다.
    if (countGp >= K)를 쓰면 안 되는 이유

    countGp == K인 상황은 실제로는 K-1개의 유효 그룹을 의미하므로,
    아직 K개를 만들 수 없는데도 “가능하다(feasible)”고 잘못 판단하여 탐색 범위를 잘못 조정하게 됩니다.
    그 결과 최적값을 놓치게 되므로, 정답이 달라지거나 오답이 됩니다.

    부가 팁
    
    **이분 탐색(파라메트릭 서치)**를 구현할 때는,

    “탐색 대상(여기서는 mid)이 ‘가능/불가능’로 나뉘는가?”
    “가능할 때는 어떤 식으로 범위를 좁힐 것인가?”
    “불가능할 때는 어떤 식으로 범위를 좁힐 것인가?”
    “경계값(등호 포함 여부)을 어떻게 설정해야 하는가?”
    이 네 가지를 항상 주의 깊게 확인해야 합니다.

 */
public class Main_17951_흩날리는시험지속에서내평점이느껴진거야_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;        //  문제 수, 그룹 수
    static int[] corret;    //  정답 배열

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        // 정답 배열 입력 처리
        corret = new int[N];    
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            corret[i] = parseInt(st.nextToken());
        }
        
        // 이분탐색
        int start = 0, end = 2000000;   //  문제의 최대 점수가 20만점이므로 end를 20만으로 잡아준다
        int result = 0;                 //  결과값

        while (start <= end) {
            int mid = (start + end) / 2;

            int countGp = countGroups(mid); //  해당 점수로 그룹을 몇개 만들 수 있는지

            if (countGp > K) {  //  그룹 개수가 K보다 크면 결과 갱신 후 start를 mid+1로
                result = Math.max(result, mid);
                start = mid + 1;
                // System.out.println(countGp + " 갱신=> " + start + " " + end);
            } else {            //  아니면 end를 mid-1로
                end = mid - 1;
                // System.out.println(countGp + " => " + start + " " + end);
            }
        }

        // 출력
        System.out.println(result);
    }

    /**
     * 해당 점수로 그룹을 몇개 만들 수 있는지 반환해주는 메서드
     * @param score 점수
     * @return      그룹 수
     */
    public static int countGroups(int score) {
        int cnt = 1;    //  그룹 수
        int temp = 0;   //  임시 합계
        for (int i = 0; i < N; i++) {
            temp += corret[i];

            if (temp >=  score) {   //  임시 합계가 점수보다 크면 그룹을 하나 늘리고 임시 합계 초기화 -> 최저 점수를 기준으로 하고 그룹을 나누는거기 때문에 커지면 그때 분할 해야 한다.
                temp = 0;
                cnt++;
            }
        }

        // 그룹 수 반환
        return cnt;
    }
}
