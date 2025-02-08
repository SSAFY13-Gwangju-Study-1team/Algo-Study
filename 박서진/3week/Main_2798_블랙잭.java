import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_2798_블랙잭 {
    public static int n;
    public static int m;
    public static int res;
    public static List<Integer> list;
    public static boolean[] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        list = new LinkedList<>();
        isVisited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i =0;i<n;i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        res=0;
        backtrack(0, 0);
        System.out.println(res);
    }

    public static void backtrack(int depth, int hap){

        if(depth==3){
            if (hap<=m){
                if(res<hap){
                    res = hap;
                }
            }
            return;
        }

        for(int i=0;i<n;i++){
            if(!isVisited[i]) {
                isVisited[i] = true;
                hap += list.get(i);
                backtrack(depth + 1, hap);
                isVisited[i] = false;
                hap -= list.get(i);
            }

        }
    }
}
