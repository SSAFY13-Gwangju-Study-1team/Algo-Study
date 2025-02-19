import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA 6808. �Կ��̿� �ο����� ī�����
 * ���̵� 3/10
 * ��Ʈ��ŷ, ����
 * 1733ms 27000kb
 * 
 * ������ �̿��Ͽ� ��� ����� ���� ���ϰ� ���Ͽ� ���и� �Ǵ��Ѵ�.
 * �Կ��̴� ������ ������ �־ �ο��̸� ��� ������ ������ ���ϸ� �ȴ�
 * ��Ʈ��ŷ �ϸ鼭 �Կ��̿� ũ�⸦ ���ϸ� ���� ��Ϳ� �Կ��� ������ �ο��� ������ �Ű������� �Ѱ��ش�
 * ���� ������ �� �� �ش� �� ������ �����̷� ���и� �Ǵ��ϸ� �ȴ�.
 */
public class SWEA_6808_규영이와인영이의카드게임_송준영 {
    // ���� �����
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // static ����
    static int[] nums;          // �Կ��� ī��
    static int[] othernums;     // �ο��� ī��
    static boolean[] visited;   // �湮 üũ
    static int win;             // �Կ��̰� �̱� Ƚ��
    static int lose;            // �Կ��̰� �� Ƚ��

    public static void main(String[] args) throws Exception {
        // �׽�Ʈ ���̽� �� �Է�
        int T = parseInt(br.readLine());

        // ���� �޼��� ���� �ݺ�
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d %d\n", t, win, lose));
        }

        // ��� ���
        System.out.println(sb);
    }
    
    // ���� �޼���
    public static void solve() throws Exception {
        // static ���� �ʱ�ȭ
        // 9���� ī�带 �����Ƿ� 9�� ���� �ʱ�ȭ
        nums = new int[9];
        othernums = new int[9];
        visited = new boolean[9];
        win = 0;
        lose = 0;

        // �Կ��̰� ���� ī�带 ������ �迭
        boolean[] numCheck = new boolean[19];

        // �Կ��̰� �� ������ �ο������� �ֱ�
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 9; i++) {
            nums[i] = parseInt(st.nextToken());
            numCheck[nums[i]] = true;
        }

        int othernumsIdx = 0;
        for (int i = 1; i < 19; i++) {
            if (!numCheck[i]) {
                othernums[othernumsIdx++] = i;
            }
        }

        backtrack(0, 0, 0);

        // System.out.println(Arrays.toString(nums));
        // System.out.println(Arrays.toString(othernums));
    }

    public static void backtrack(int depth, int gyu, int inn) {
        if (depth == 9) {
            if (gyu > inn) {
                win++;
            } else if (gyu < inn) {
                lose++;
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (nums[depth] > othernums[i]) {
                    backtrack(depth + 1, gyu + nums[depth] + othernums[i], inn);
                } else {
                    backtrack(depth + 1, gyu, inn + nums[depth] + othernums[i]);
                }
                visited[i] = false;
            }
        }
    }    
}
