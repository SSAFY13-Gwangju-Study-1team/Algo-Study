import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2578_빙고 {
    static int[][] map = new int[5][5];
    static boolean[][] isVisited = new boolean[5][5];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<5;i++){
            String[] line = br.readLine().split(" ");
            for(int j=0;j<5;j++){
                map[i][j] = parseInt(line[j]);
            }
        }
        // 사회자 숫자
        for(int i=0;i<5;i++){
            String[] line = br.readLine().split(" ");
            for(int j=0;j<5;j++){
                ans.add(parseInt(line[j]));
            }
        }
        // 12까지는 그냥 체크
        for(int i=0;i<11;i++){
            for(int r=0;r<5;r++){
                for(int c=0;c<5;c++){
                    if(map[r][c] == ans.get(i)){
                        isVisited[r][c] = true;
                    }
                }
            }
        }
        int cnt = 11;

        // 12 이후로는 빙고 체크
        while(cnt<25){
            for(int r=0;r<5;r++){
                for(int c=0;c<5;c++){
                    if(map[r][c] == ans.get(cnt)){
                        isVisited[r][c] = true;
                    }
                }
            }
            if(isBingo()) break;
            cnt++;
        }
        System.out.println(cnt+1);
    }
    public static boolean isBingo(){
        int cnt = 0; //3이 될때 true 리턴
        boolean flag;
        //행검사
        for(int i=0;i<5;i++){
            flag=true;
            for(int j=0;j<5;j++){
                if(!isVisited[i][j]) {
                    flag=false;
                    break;
                }
            }
            if(flag) cnt++;
            if(cnt==3) return true;
        }
        //열검사
        for(int i=0;i<5;i++){
            flag=true;
            for(int j=0;j<5;j++){
                if(!isVisited[j][i]) {
                    flag=false;
                    break;
                }
            }
            if(flag) cnt++;
            if(cnt==3) return true;
        }
        //대각선 감소 방향
        flag=true;
        for(int i=0;i<5;i++){
            if(!isVisited[i][i]) {
                flag=false;
                break;
            }
        }
        if(flag) cnt++;
        if(cnt==3) return true;

        //대각선 증가 방향
        flag=true;
        for(int i=4;i>=0;i--){
            if(!isVisited[i][4-i]) {
                flag=false;
                break;
            }
        }
        if(flag) cnt++;
        return cnt == 3;
    }
}
