import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2605_줄_세우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        int student = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> line = new ArrayList<>();

        for(int i=1; i<=student; i++) { // 첫 번째 학생부터 시작
            int num = Integer.parseInt(st.nextToken());
            if(num==0)  // 0인 경우 맨 뒤에 섬
                line.add(i);
            else line.add(line.size()-num, i);  // 아닌 경우 뽑은 수 만큼 현재 줄에서 앞에 섬
        }

        for(int i:line)
            System.out.print(i + " ");
    }
}