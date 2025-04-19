import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_20159_동작그만밑장빼기냐_송준영
 * 난이도 7/10
 * 누적합
 * 272ms 24mb
 * 
 * 
 * 밑장이 어디에 들어가냐에 따라 jung의 점수가 달라진다
 * 그런데 10만이라 다 일일이 넣고 검사하면 시간초과가 날 수 있다
 * 누적합으로 구간합을 구하여 규틱을 찾아 둘을 적절히 섞어서 사용한다
 * 규칙 찾는게 죽을뻔 했다
 * long을 써야한다!! 자료 범위 주의!!!
 */
public class Main_20159_동작그만밑장빼기냐_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;               // 카드의 개수
    static long max;            // 최대 점수
    static long[] jung, player; // jung과 player의 점수


    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        jung = new long[N/2];     // jung의 점수
        player = new long[N/2];   // player의 점수
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                jung[i/2] = parseInt(st.nextToken());   // jung의 점수
            } else {
                player[i/2] = parseInt(st.nextToken()); // player의 점수
            }
        }

        for (int i = 1; i < N/2; i++) {
            jung[i] += jung[i-1];     // 누적합
            player[i] += player[i-1]; // 누적합
            
        }
        // for (int i = 0; i < N/2; i++) {
        //     System.out.println(jung[i] + " " + player[i]);
        // }

        max = Math.max(jung[N/2-1], player[N/2-1]);     // 처음과 끝에 밑장이 있을 때

        // 밑장 위치에 따른 jung 의 점수 최대값 계산
        for (int i = 1; i < N-1; i++) {
            if (i % 2 == 0) {   // 밑장이 짝수 인덱스에 있을 때
                max = Math.max(max, jung[i/2 - 1] + player[N/2 - 1] - player[i/2 - 1]); 
            } else {            // 밑장이 홀수 인덱스에 있을 때
                if (i == 1) {
                    max = Math.max(max, jung[i/2] + player[N/2 - 2]);
                } else {
                    max = Math.max(max, jung[i/2] + player[N/2 - 2] - player[i/2 - 1]);
                }
            } 
        }

        // 결과 출력
        System.out.println(max);
        
    }
}

/*  여기서 규칙 찾아서 사용
jung : {3 5 1}
player : {1 2 3}

[0]
x
0 1 2

[1]
0
0 1

[2]
0
1 2

[3]
0 1
1

[4]
0 1
2

[5]
0 1 2
x
 */
