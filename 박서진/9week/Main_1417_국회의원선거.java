import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1417_국회의원선거 {
    static class Candidate implements Comparable<Candidate>{
        int index;
        int pollCnt;

        public Candidate(int index, int pollCnt) {
            this.index = index;
            this.pollCnt = pollCnt;
        }

        @Override
        public int compareTo(Candidate o) {
            if(o.pollCnt == this.pollCnt) return o.index - this.index;
            return o.pollCnt - this.pollCnt;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Candidate{");
            sb.append("index=").append(index);
            sb.append(", pollCnt=").append(pollCnt);
            sb.append('}');
            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Candidate> pq = new PriorityQueue<>();
        int n = parseInt(br.readLine());
        Candidate[] candidates = new Candidate[n+1];
        for(int i=1;i<=n;i++){
            candidates[i] = new Candidate(i, parseInt(br.readLine()));
            pq.add(candidates[i]);
        }
        int buyPeople=0;
        while(true){
            Candidate temp = pq.poll();
            if(temp.index==1){
                break;
            }

            temp.pollCnt-=1;
            pq.remove(candidates[1]);
            pq.add(temp);
            candidates[1].pollCnt+=1;
            pq.add(candidates[1]);
            buyPeople++;
        }
        System.out.println(buyPeople);
    }
}
