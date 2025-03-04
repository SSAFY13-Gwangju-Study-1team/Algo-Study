import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
class Meeting implements Comparable<Meeting>{
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting o) {
        return (this.end!=o.end)? this.end-o.end : this.start-o.start;
    }
}
public class Main_1931_회의실배정2번째풀이 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        StringTokenizer st;
        Meeting[] meetings = new Meeting[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            Meeting m = new Meeting(parseInt(st.nextToken()),parseInt(st.nextToken()));
            meetings[i] = m;
        }
        Arrays.sort(meetings);

        // 빨리 끝나는 것 부터 하나씩 선택 - greedy!!, 첫번째는 무조건 들어감 -> 최선의 선택임을 보장할 수 없음
        int end =  meetings[0].end;
        int cnt = 1;
        for(int i=1;i<n;i++){
            if(meetings[i].start < end) continue;
            cnt++;
            end = meetings[i].end;
        }
        System.out.println(cnt);
    }
}
