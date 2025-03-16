import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759_암호_만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int L;
    static int C;
    static String[] arrC;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());   // 암호의 길이 L
        C = Integer.parseInt(st.nextToken());   // 문자의 종류 C

        arrC = br.readLine().split(" ");   // C개의 문자들을 저장
        Arrays.sort(arrC);  // 정렬
        visited = new boolean[C];   // 방문 확인

        password(0, new StringBuilder(), 0, 0, 0);  // 백트래킹 시작
    }

    /**
     * 암호 생성기
     * @param depth    암호의 n번째 글자
     * @param pw       현재까지 완성된 암호
     * @param lastIdx  암호의 글자들은 중복되지 않고, 정렬이 중요하므로 마지막으로 저장한 글자의 인덱스 포함
     * @param zaum     자음의 개수
     * @param moum     모음의 개수
     */
    public static void password(int depth, StringBuilder pw, int lastIdx, int zaum, int moum) {
        if(depth == L) {
            if(zaum >= 2 && moum >= 1)  // 암호는 자음 2개, 모음 1개 이상을 포함해야 함
                System.out.println(pw);
            return;
        }

        StringBuilder nextPw;
        for(int c=lastIdx; c<C; c++) {
            if(!visited[c]) {
                nextPw = new StringBuilder(pw.toString());  // nextPw = pw와 같이 하면 얕은 복사로 pw도 수정되어 버림
                visited[c] = true;
                nextPw.append(arrC[c]);

                if ("aeiou".contains(arrC[c]))  // 모음인지 확인
                    password(depth+1, nextPw, c, zaum, moum+1);     // 모음 갯수 증가
                else password(depth+1, nextPw, c, zaum+1, moum);    // 자음 갯수 증가

                visited[c] = false;     // 백트래킹
            }
        }

    }
}
