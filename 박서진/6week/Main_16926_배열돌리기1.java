import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 메모리 40436	시간 508ms
 * 처음에 어떤식으로 풀어야 하는지 도저히 모르겠어서 찬영이 오빠한테 힌트를 얻었다
 * 테두리별로 덱을 이용해서 각각 처리해 주면 되는 문제
 * 그런데 범위 조절하는게 까다로워서 조금 오래 걸렸던 문제이다
 */
public class Main_16926_배열돌리기1 {
    static int[][] map;
    static int n,m,r;
    static int row, col, index;
    static ArrayDeque<Integer> arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        r = parseInt(st.nextToken());
        map = new int[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++){
                map[i][j] = parseInt(st.nextToken());
            }
        }
        
        // 변수 지정
        row = n;
        col = m;
        index=0;

        // 반복 회수
        // row와 col 중 작은 값이 먼저 길이 2에 도달할건데 그 값을 체크
        while(row>=2 && col>=2) {
            // 들어가기 전에 arr 초기화
            arr = new ArrayDeque<>();
            // 테두리의 값을 덱에 넣기
            addToDeque();
            // r 만큼 회전 시키기
            shift();
            // 다시 map에 넣기
            addToMap();
            // 앞 과정이 완성되었다면 다음 테두리 회전 시작
            row-=2; // 가로와 세로의 최소 값을 파악하기 위한 변수
            col-=2;
            index++; // 테두리별로 접근을 하기 위한 변수
            if(row<2 || col<2) break; // 조건에서 min(n, m)은 2로 나누어 떨어진다고 나와있음! 2보다 작아지면 0이 될것임
        }

        // 출력
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                sb.append(map[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    // 다시 arr에서 꺼내서 그자리 그 대로 map에 넣어주면 되서 구현은 어렵지 않음
    private static void addToMap() {
        // 왼쪽 세로
        for(int r=index;r<n-index-1;r++){
            map[r][index] = arr.pollFirst();
        }
        // 아래 가로
        for(int c=index;c<m-index-1;c++){
            map[n-index-1][c] = arr.pollFirst();
        }
        // 오른쪽 세로
        for(int r=n-index-1;r>index;r--){
            map[r][m-index-1] = arr.pollFirst();
        }
        // 위쪽 가로
        for(int c=m-index-1;c>index;c--){
            map[index][c] = arr.pollFirst();
        }
    }

    private static void shift() {
        // 가장 마지막의 값을 가장 첫번쨰에 넣어준다
        for(int i=0;i<r;i++){
            arr.addFirst(arr.pollLast());
        }
//        System.out.println(Arrays.toString(arr.toArray()));
    }

    // 테두리별로 덱에 넣어서 관리한다
    private static void addToDeque() {
        // 왼쪽 세로
        for(int r=index;r<n-index-1;r++){
            arr.add(map[r][index]);
        }
        // 아래 가로
        for(int c=index;c<m-index-1;c++){
            arr.add(map[n-index-1][c]);
        }
        // 오른쪽 세로
        for(int r=n-index-1;r>index;r--){
            arr.add(map[r][m-index-1]);
        }
        // 위쪽 가로
        for(int c=m-index-1;c>index;c--){
            arr.add(map[index][c]);
        }
//        System.out.println(Arrays.toString(arr.toArray()));
    }
}
