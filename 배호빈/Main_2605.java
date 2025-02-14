import java.util.*;
import java.io.*;

public class Main_2605 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list= new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            list.add(i - num, i + 1);
        }
        for (Integer i : list) {
            System.out.print(i + " ");
        }
    }
}
