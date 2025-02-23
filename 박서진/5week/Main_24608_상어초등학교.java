import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 메모리25604kb	시간268ms
 * 혼자서는 풀 수 없었고,,오늘 준엽님 수업을 듣고 풀었습니다*^^* 주말에 다시 풀어야겠다
 * 처음에는 계속 if문 돌리려고 했는데 수업 듣고 node 클래스의 comparable을 써야겠다고 다시 생각해볼 수 있었다
 */
public class Main_24608_상어초등학교 {
    static int n;
    static int[][] seats;
    static int[][] students;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0, 0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        seats = new int[n+1][n+1];  // 자리정보 (1,1) 부터 시작함
        students = new int[n*n][5]; // 학생정보
        for(int i=0;i<n*n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++) {
                students[i][j] = parseInt(st.nextToken());
            }
        }
        makeSeat(0);

//        for(int i=1;i<=n;i++){
//            for(int j=1;j<=n;j++){
//                System.out.print(seats[i][j]);
//            }
//            System.out.println();
//        }
        int res = checkSatisfaction();
        System.out.println(res);

    }
    public static int checkSatisfaction(){ // 학생들의 만족도를 조사하는 함수
        int sum=0;
        for(int r=1;r<=n;r++){
            for(int c=1;c<=n;c++){
                int student = seats[r][c]; // 학생의 번호가 나옴
                int idx=-1;
                int loversCnt=0;
                for(int i = 0;i<n*n;i++){ //학생의 인덱스를 뽑아서 --> 이 부분은 준엽님처럼 애초에 리스트 관리를 잘 하면 필요 없을 수도 있을듯
                    if(students[i][0] == student)
                        idx = i;
                }
                for(int k=0;k<4;k++){ // 4방에 lovers가 몇명이 있는 지 체크
                    int nr = r+dr[k];
                    int nc = c+dc[k];
                    if(nr>0 && nr<=n && nc>0 && nc<=n){
                        for (int i=1;i<5;i++) {
                            if (seats[nr][nc] == students[idx][i]) {
                                loversCnt++;
                                break;
                            }
                        }
                    }
                }
                switch (loversCnt){
                    case 1:
                        sum++;
                        break;
                    case 2:
                        sum+=10;
                        break;
                    case 3:
                        sum+=100;
                        break;
                    case 4:
                        sum+=1000;
                        break;
                }

            }
        }
        return sum;
    }

    public static void makeSeat(int i){
        if(i==n*n) return;
        ArrayList<Node> list = new ArrayList<>(); // 학생이 앉을 수 있는 자리들을 리스트에 담을거임
        int student = students[i][0]; // 학생의 번호
        int[] lovers = new int[4]; // 좋아하는 학생 번호
        for (int x=1;x<=4;x++){
            lovers[x-1] = students[i][x];
        }


        for(int r=1;r<=n;r++){
            for(int c=1;c<=n;c++){
                int blankCnt=0;
                int loversCnt = 0;
                if(seats[r][c]!=0) continue; // 1. 빈자리인지 확인
                for(int k=0;k<4;k++){ // 2. 빈자리라면 주위에 내가 좋아하는 사람이 있는지 체크, 빈자리는 몇개인지 체크
                    int nr = r+dr[k];
                    int nc = c+dc[k];
                    if(nr>0 && nr<=n && nc>0 && nc<=n){
                        // 빈칸 체크
                        if(seats[nr][nc]==0)
                            blankCnt++;
                        for (int lover : lovers) {
                            if (seats[nr][nc] == lover) {
                                loversCnt++;
                                break;
                            }
                        }
                    }
                }
                list.add(new Node(r, c, loversCnt,blankCnt)); // 노드 클래스를 리스트에 넣어서 sort로 정렬을 시키는 중요한 포인트!!

            }

        }

        Collections.sort(list);
        seats[list.get(0).r][list.get(0).c] = student;

        // 다음학생 호출
        makeSeat(i+1);
    }
}
class Node implements Comparable<Node>{
    int r;
    int c;
    int loversCnt;
    int blankCnt;
    public Node(int r, int c, int loversCnt, int blankCnt){
        this.r =r;
        this.c=c;
        this.loversCnt =loversCnt;
        this.blankCnt=blankCnt;
    }

    @Override
    public int compareTo(Node o) {
        if(loversCnt != o.loversCnt){
            return Integer.compare(o.loversCnt, loversCnt); // loversCnt 내림차순으로
        }
        if(blankCnt!=o.blankCnt){
            return Integer.compare(o.blankCnt, blankCnt); // blankCnt 내림차순
        }
        if(r!=o.r){
            return Integer.compare(r, o.r); //행 오름차순
        }
        return Integer.compare(c, o.c); //열 오름차순
    }
}
