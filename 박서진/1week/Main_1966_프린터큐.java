import java.io.*;
import java.util.*;
public class Main_1966_프린터큐 {
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        // test case
        int tt = Integer.parseInt(br.readLine());
        for(int t=0;t<tt;t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int doc = Integer.parseInt(st.nextToken());
            LinkedList<int[]> q = new LinkedList<>(); //큐를 활용 할 연결리스트
            
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<n;i++){
                q.offer(new int[] {i, Integer.parseInt(st.nextToken())}); // 초기 인덱스, 중요도
            }

            int cnt = 0;

            while(!q.isEmpty()){
                int[] front = q.poll(); // 가장 첫 번째 원소
                boolean isMax = true;

                //큐에 남아있는 원소들과 중요도 비교
                for (int i=0;i<q.size();i++){
                    // 첫번째 원소보다 큐에 있는 i번째 요소가 더 클 경우
                    if (front[1] < q.get(i)[1]){
                        q.offer(front); //맨 뒤로 다시 보내주기

                        // 처음 원소 이후 i 전까지 다 뒤로 보냄
                        for(int j=0;j<i;j++){
                            q.offer(q.poll());
                        }

                        // front원소가 가장 큰 원소가 아니였으므로
                        isMax = false;
                        break;
                    }

                }

                if (isMax==false){
                    continue;
                }
                cnt++;
                if (front[0] == doc){
                    break;
                }
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

}
