import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class SWEA_8382_방향전환2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1= parseInt(st.nextToken());
            int y1= parseInt(st.nextToken());
            int x2= parseInt(st.nextToken());
            int y2= parseInt(st.nextToken());
            int diffX = Math.abs(x2-x1);
            int diffY = Math.abs(y2-y1);
            int res=0; // 총 이동해야 하는 양

            res+=diffX+diffY; // x,y가 변한 양만큼 더해줌
            int differDir = Math.abs(diffY-diffX)-1;
            differDir = (differDir%2==0)?differDir:differDir+1;
            res+=differDir;

            sb.append("#"+t+" "+res).append("\n");

        }
        System.out.println(sb);
    }
}
