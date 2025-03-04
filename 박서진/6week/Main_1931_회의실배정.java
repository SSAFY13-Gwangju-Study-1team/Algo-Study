import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1931_회의실배정 {
    static class Meeting implements Comparable<Meeting>{
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // 회의 시작, 종료 시간이 같은 경우도 있을 경우: 종료 시간이 빠른순 같다면 시작 시갖닝 빠른 순
        // (1,2), (2,3), (3,3) -> 3개 = 답
        // (1,2), (3,3), (2,3) -> 2개
        @Override
        public int compareTo(Meeting o) {
            return this.end != o.end? this.end-o.end: this.start-o.start;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = parseInt(br.readLine());
        Meeting[] meetings = new Meeting[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            Meeting m = new Meeting(parseInt(st.nextToken()), parseInt(st.nextToken()));
            meetings[i] = m;
        }
        Arrays.sort(meetings); // 오름차순정렬
        List<Meeting> result = new ArrayList<>();
        result.add(meetings[0]); // 첫번째 무조건 넣기

        for(int i=1;i<n;i++){
            // 배정된 마지막 회의 종료시간보다 고려하는 회의 시작시간이 길거나 이후라면 
            // 배정가능
            if (result.get(result.size()-1).end<=meetings[i].start){
                result.add(meetings[i]);
            }
        }

        System.out.println(result.size()); // 배정 회의 수
    }
}
