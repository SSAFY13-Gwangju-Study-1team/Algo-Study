import java.io.*;
import java.util.*;

public class 백준_25393_교집합만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        // 시작점 l -> 끝점들 (TreeSet)
        // 끝점 r -> 시작점들 (TreeSet)
        Map<Integer, TreeSet<Integer>> startMap = new HashMap<>();
        Map<Integer, TreeSet<Integer>> endMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            startMap.putIfAbsent(a, new TreeSet<>());
            startMap.get(a).add(b);
            endMap.putIfAbsent(b, new TreeSet<>());
            endMap.get(b).add(a);
        }

        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            TreeSet<Integer> ends = startMap.get(l);
            TreeSet<Integer> starts = endMap.get(r);

            // 정확히 [l, r] 구간이 존재하는가?
            if (ends != null && ends.contains(r)) {
                sb.append("1\n");
                continue;
            }

            boolean flag = false;

            // 두 구간의 교집합으로 [l, r]을 만들 수 있는가?
            // [l, r1] (r1 >= r) 구간과 [l2, r] (l2 <= l) 구간이 모두 존재
            if ((ends != null && ends.ceiling(r) != null) && (starts != null && starts.floor(l) != null)) {
                flag = true;
            }

            if (flag) {
                sb.append("2\n");
            } else {
                sb.append("-1\n");
            }
        }

        System.out.print(sb);
    }
}