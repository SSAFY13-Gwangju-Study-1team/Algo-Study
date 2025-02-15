import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_30804_과일탕후루 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] fruits = new int[n];
        HashMap<Integer, Integer> hmap = new HashMap<>();
        for(int i=0;i<n;i++) {
            fruits[i]= parseInt(st.nextToken());
        }

        int front=0, rear=0;
        //front와 rear를 같은 방향으로 움직이면서 슬라이딩 윈도우에 있는
        // 숫자 개수가 2를 초과하면 front를 움직이고 2개 이하이면 rear를 움직여서
        // rear가 끝을 만나면 종료
        int maxCnt=0;
        while (front<=rear && rear<n){
            hmap.put(fruits[rear], hmap.getOrDefault(fruits[rear], 0)+1);
            while(hmap.size()>2){
                hmap.put(fruits[front], hmap.get(fruits[front])-1);
                if(hmap.get(fruits[front])==0)
                    hmap.remove(fruits[front]);
                front+=1;
            }

            maxCnt = Math.max(rear-front+1, maxCnt);
            rear+=1;
        }

        System.out.println(maxCnt);

    }
}
