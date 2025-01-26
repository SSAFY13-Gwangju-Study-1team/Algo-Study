import java.io.*;
import java.util.*;
public class Main_28279_덱2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(int i =0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> line = new ArrayList<>();

            while (st.hasMoreTokens()){
                line.add(Integer.parseInt(st.nextToken()));
            }

            //첫 번째 값
            switch (line.get(0)){
                case 1:
                    dq.addFirst(line.get(1));
                    break;
                case 2:
                    dq.addLast(line.get(1));
                    break;
                case 3:
                    if (!dq.isEmpty()){
                        sb.append(dq.poll()+"\n");
                    }else{
                        sb.append(-1+"\n");
                    }
                    break;
                case 4:
                    if (!dq.isEmpty()){
                        sb.append(dq.pollLast()+"\n");
                    }else {
                        sb.append(-1+"\n");
                    }
                    break;
                case 5:
                    sb.append(dq.size()+"\n");
                    break;
                case 6:
                    if (!dq.isEmpty()){
                        sb.append(0+"\n");
                    }else {
                        sb.append(1+"\n");
                    }
                    break;
                case 7:
                    if (!dq.isEmpty()){
                        sb.append(dq.peek()+"\n");
                    }else {
                        sb.append(-1+"\n");
                    }
                    break;
                case 8:
                    if (!dq.isEmpty()){
                        sb.append(dq.getLast()+"\n");
                    }else {
                        sb.append(-1+"\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
        br.close();
    }
}
