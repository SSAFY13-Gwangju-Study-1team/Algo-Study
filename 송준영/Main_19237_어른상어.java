import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;
public class Main_19237_어른상어 {
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};
    static int n, m, k;
    static int[][][] dirPriority;
    static int[][] map;
    static List<Shark> sharkList;
    static PriorityQueue<Smell> smellq;
    static class Smell implements Comparable<Smell>{
        int r;
        int c;
        int sharkNo;
        int time;

        public Smell(int r, int c, int sharkNo, int time) {
            super();
            this.r = r;
            this.c = c;
            this.sharkNo = sharkNo;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Smell [r=" + r + ", c=" + c + ", sharkNo=" + sharkNo + ", time=" + time + "]";
        }

        @Override
        public int compareTo(Smell o) {
            return this.time-o.time;
        }
        
        
    }
    
    static class Shark implements Comparable<Shark>{
        int r;
        int c;
        int index;
        int dir;
        public Shark(int r, int c, int index) {
            super();
            this.r = r;
            this.c = c;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Shark [r=" + r + ", c=" + c + ", index=" + index + ", dir=" + dir + "]";
        }

        @Override
        public int compareTo(Shark o) {
            return this.index-o.index;
        }
        
        
    }
    

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken()); // 상어 개수
        k = parseInt(st.nextToken()); // 냄새 지속성
        map = new int[n][n];
        dirPriority = new int[m+1][4][4]; // 상어 m마리 4방
        sharkList = new ArrayList<>();
        smellq = new PriorityQueue<>();
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                int temp = parseInt(st.nextToken());
                if(temp>0) {
                    // 상어 만들기
                    Shark shark = new Shark(i, j, temp);
                    sharkList.add(shark);
                    // map에 냄새 남기기
                    map[i][j] = temp;
                    smellq.add(new Smell(i,j,temp, k));
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++) { // 방향 받기
            sharkList.get(i).dir = parseInt(st.nextToken())-1;
        }
        // 방향 우선순위 받기
        for(int i=1;i<=m;i++) {
            for(int j=0;j<4;j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<4;k++) {
                    dirPriority[i][j][k] = parseInt(st.nextToken())-1;
                }
            }
        }
        
        int time = 0;
        while(true) {
            System.out.println("time: "+ time);
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("=======================");
            
            // 기저 조건: 상어가 한마리가 남으면 종료
            if(sharkList.size()==1) break;
            List<int[]> sharkMoveList = new ArrayList<>(); // r, c, index
            Collections.sort(sharkList);
            // 모든 상어를 돌면서 상어 이동 리스트에 상어 갈 곳을 넣어주기
            for(int i=0;i<sharkList.size();i++) {
                // 상어 이동 시키기
                Shark curShark = sharkList.get(i);
                int nextDir = -1;
                for(int j=0;j<4;j++) {
                    int candidateDir = dirPriority[curShark.index][curShark.dir][j];
                    int nr = curShark.r + dr[candidateDir];
                    int nc = curShark.c + dc[candidateDir];
                    if(isin(nr,nc) && map[nr][nc]==0) {
                        nextDir = candidateDir;
                        sharkMoveList.add(new int[] {nr, nc, curShark.index}); // 한거번에 삭제할 상어 삭제 리스트에 넣어주고
                        curShark.r=nr; // 상어 정보 업데이트하기
                        curShark.c=nc;
                        curShark.dir=nextDir;
                        break;
                    }
                }
                // 만약 상어가 갈 곳을 못 정했다면 자기가 온 곳으로 다시 가기
                if(nextDir==-1) {
                    for(int j=0;j<4;j++) {
                        int candidateDir = dirPriority[curShark.index][curShark.dir][j];
                        int nr = curShark.r + dr[candidateDir];
                        int nc = curShark.c + dc[candidateDir];
                        if(isin(nr,nc) && map[nr][nc]==curShark.index) {
                            nextDir = candidateDir;
                            sharkMoveList.add(new int[] {nr, nc, curShark.index}); // 한거번에 삭제할 상어 삭제 리스트에 넣어주고
                            curShark.r=nr; // 상어 정보 업데이트하기
                            curShark.c=nc;
                            curShark.dir=nextDir;
                            break;
                        }
                    }
                }
            }
            
            // 모든 냄새 time 한칸씩 줄이고 상어 이동시키기
            Iterator<Smell> it = smellq.iterator();
            while(it.hasNext()) {
                it.next().time--;
            }
            // time이 0 되면 smell 삭제 map에서 다시 0으로 돌리기
            while(!smellq.isEmpty()) {
                if(smellq.peek().time==0) {
                    Smell temp = smellq.poll();
                    map[temp.r][temp.c] = 0;
                }else {
                    break;
                }
            }
            
            // 상어 이동 리스트에서 상어 이동 시키기
            for(int i=0;i<sharkMoveList.size();i++) {
                int[] sharkMove = sharkMoveList.get(i);
                int r = sharkMove[0];
                int c = sharkMove[1];
                int sharkNo = sharkMove[2];
                // map에 냄새 전염 시키기
                if(map[r][c]==0) {
                    map[r][c] = sharkNo;
                }else if(map[r][c]==sharkNo) {
                    continue;
                }else { // 만약 이미 앞에서 다른 상어가 찜뽕 했다면 현재 상어 죽음
                    for(int j=0;j<sharkList.size();j++) {
                        if(sharkList.get(j).index==sharkNo) {
                            sharkList.remove(j);
                            break;
                        }
                    }
                }
            }
            
            // 현재 상어 위치 smellq에 넣어주기 - 새로운 냄새
            for(int i=0;i<sharkList.size();i++) {
                Shark curShark = sharkList.get(i);
                smellq.add(new Smell(curShark.r, curShark.c, curShark.index, k));
            }
            
            time++;
            if(time>1000) {
                time= -1;
                break;
            }
        }
        
        System.out.println(time);
    }


    private static boolean isin(int nr, int nc) {
        return nr>=0 && nc>=0 && nr<n && nc<n;
    }

}