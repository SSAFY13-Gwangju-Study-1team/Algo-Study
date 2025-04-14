import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_5648_원자소멸시뮬레이션
 * 난이도 9/10
 * 시뮬레이션, 수학
 * 219ms 40mb
 * 
 * 원자가 충돌하는 조건을 수학적으로 계산해야함 -> 빡셈, 모든 경우 계산
 * 각 두 원소를 모두 비교 -> 충돌하면 충돌 리스트에 삽입
 * 충돌 리스트를 정렬 -> 시간 순으로 정렬
 * 충돌 리스트를 순회하면서 충돌하는 원자들에 대해 소멸 시각을 갱신 (이미 충돌 해서 더 빠른 시간이 있으면 갱신 하지 않음, 다만 같은 시간 이면 갱신)
 * 충돌 시각이 갱신된 원자들은 에너지를 더해줌
 * 
 */
public class SWEA_5648_원자소멸시뮬레이션_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int T;      // 테스트 케이스 수
    static int N;      // 원자 개수
    static double[] destroyTime;  // 각 원자가 소멸하는 시각 (갱신되지 않으면 무한대)
    static Atom[] atoms;
    
    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 메서드
     * @return
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        
        destroyTime = new double[N];
        Arrays.fill(destroyTime, Double.MAX_VALUE); // 소멸 시각 초기화
        atoms = new Atom[N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());
            int dir = parseInt(st.nextToken());
            int energy = parseInt(st.nextToken());
            atoms[i] = new Atom(x, y, dir, energy);
        }
        
        Arrays.sort(atoms); // x, y 좌표 기준으로 정렬

        List<double[]> times = new ArrayList<>();   // 충돌 시각 리스트

        // 모든 원자 쌍에 대해 충돌 시각 계산
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double time = isDestroy(atoms[i], atoms[j]);
                if (time != Double.MAX_VALUE) { // 충돌 시각이 유효한 경우
                    times.add(new double[]{time, i, j});    // 충돌 시각, 원자 i, 원자 j
                }
            }
        }

        Collections.sort(times, (a, b) -> Double.compare(a[0], b[0]));  // 충돌 시각 기준으로 정렬

        for (double[] timeInfo : times) {   // 충돌 시각 리스트 순회
            double time = timeInfo[0];
            int i = (int) timeInfo[1];
            int j = (int) timeInfo[2];

            // 충돌 시각이 유효한 경우에만 처리 (이미 충돌 해서 더 빠른 시간이 있으면 갱신 하지 않음, 다만 같은 시간 이면 갱신)
            if (destroyTime[i] >= time && destroyTime[j] >= time) {
                destroyTime[i] = time;
                destroyTime[j] = time;
            }
        }
        
        int totalEnergy = 0;    // 소멸된 원자의 에너지를 합산

        // 소멸된 원자의 에너지를 계산
        for (int i = 0; i < N; i++) {
            if (destroyTime[i] != Double.MAX_VALUE) {
                totalEnergy += atoms[i].energy;
            }
        }

        return totalEnergy; // 결과 반환
    }
    
    /**
     * 원자 두 개의 충돌 시각을 계산하는 메서드
     * @param a1    원자 1
     * @param a2    원자 2
     * @return      충돌 시각 (소멸 시각), 충돌하지 않으면 Double.MAX_VALUE
     */
    public static double isDestroy(Atom a1, Atom a2) {
        if (a1.dir == a2.dir) return Double.MAX_VALUE;  // 같은 방향으로 이동하는 경우 충돌하지 않음
        
        switch (a1.dir) {
            case 0: // a1이 위 방향으로 이동
                if (a2.dir == 1 && a1.x == a2.x && a1.y < a2.y) return (Math.abs(a1.y - a2.y)) / 2.0;
                if (a2.dir == 3 && (a1.x - a2.x) == (a2.y - a1.y) && a1.x > a2.x) return Math.abs(a1.x - a2.x);
                if (a2.dir == 2 && (a2.x - a1.x) == (a2.y - a1.y) && a2.x > a1.x) return Math.abs(a1.x - a2.x);
                break;
            case 1: // a1이 아래 방향으로 이동
                if (a2.dir == 0 && a1.x == a2.x && a1.y > a2.y) return (Math.abs(a1.y - a2.y)) / 2.0;
                if (a2.dir == 3 && (a1.x - a2.x) == (a1.y - a2.y) && a1.x > a2.x) return Math.abs(a1.x - a2.x);
                if (a2.dir == 2 && (a2.x - a1.x) == (a1.y - a2.y) && a2.x > a1.x) return Math.abs(a1.x - a2.x);
                break;
            case 2: // a1이 왼쪽 방향으로 이동
                if (a2.dir == 3 && a1.y == a2.y && a1.x > a2.x) return (Math.abs(a1.x - a2.x)) / 2.0;
                if (a2.dir == 0 && (a1.x - a2.x) == (a1.y - a2.y) && a1.x > a2.x) return Math.abs(a1.x - a2.x);
                if (a2.dir == 1 && (a1.x - a2.x) == (a2.y - a1.y) && a1.x > a2.x) return Math.abs(a1.x - a2.x);
                break;
            case 3: // a1이 오른쪽 방향으로 이동
                if (a2.dir == 2 && a1.y == a2.y && a1.x < a2.x) return (Math.abs(a1.x - a2.x)) / 2.0;
                if (a2.dir == 0 && (a2.x - a1.x) == (a1.y - a2.y) && a2.x > a1.x) return Math.abs(a1.x - a2.x);
                if (a2.dir == 1 && (a2.x - a1.x) == (a2.y - a1.y) && a2.x > a1.x) return Math.abs(a1.x - a2.x);
                break;
        }
        return Double.MAX_VALUE;    // 충돌하지 않음
    }
    
    /**
     * 원자 클래스
     * x, y 좌표, 방향, 에너지를 저장
     */
    public static class Atom implements Comparable<Atom> {
        int x;
        int y;
        int dir;
        int energy;
        
        public Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
        
        @Override
        public int compareTo(Atom o) {
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }
    }
}
