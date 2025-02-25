import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17281_야구_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static List<List<Integer>> players;
    static List<Set<List<Integer>>> permus;
    static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        N = parseInt(br.readLine());

        players = new ArrayList<>();
        permus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            players.add(new LinkedList<>());
            permus.add(new HashSet<>());

            for (int j = 0; j < 9; j++) {
                int temp = parseInt(st.nextToken());
                players.get(i).add(temp);
            }
        }

        permutation();

        recursive(0, 0, 0);

        System.out.println(maxScore);

        // for (int i = 0; i < N; i++) {
        //     for (List<Integer> e : permus.get(i)) {
        //         for (int in : e) {
        //             System.out.print(in + " ");
        //         }
        //         System.out.println();
        //     }
        //     System.out.println();
        // }

    }

    public static void recursive(int depth, int score, int order) {
        if (depth == N) {
            maxScore = Math.max(maxScore, score);
            return;
        }

        for (List<Integer> p : permus.get(depth)) {
            int[] order_score = play(order, p);
            recursive(depth + 1, score + order_score[1], order_score[0]);
        }
    }
    public static int[] play(int order, List<Integer> players) {

        boolean[] plate = new boolean[4];
        int outCnt = 0;
        int score = 0;

        while (outCnt < 3) {
            switch (players.get(order)) {
                case 0:
                    outCnt++;
                    break;
                case 1:
                    for (int i = 3; i >= 1; i--) {
                        if (plate[i] == true) {
                            if (i + 1 > 3) {
                                score++;
                                plate[i] = false;
                            } else {
                                plate[i] = false;
                                plate[i + 1] = true;
                            }
                        }
                    }
                    plate[1] = true;
                    break;
                case 2:
                    for (int i = 3; i >= 1; i--) {
                        if (plate[i] == true) {
                            if (i + 2 > 3) {
                                score++;
                                plate[i] = false;
                            } else {
                                plate[i] = false;
                                plate[i + 2] = true;
                            }
                        }
                    }
                    plate[2] = true;
                    break;
                case 3:
                    for (int i = 3; i >= 1; i--) {
                        if (plate[i] == true) {
                            if (i + 3 > 3) {
                                score++;
                                plate[i] = false;
                            } else {
                                plate[i] = false;
                                plate[i + 3] = true;
                            }
                        }
                    }
                    plate[3] = true;
                    break;
                case 4:
                    score++;
                    for (int i = 3; i >= 1; i--) {
                        if (plate[i] == true) {
                            score++;
                            plate[i] = false;
                        }
                    }
                    break;
            }

            order++;
            order %= 9;
        }

        return new int[] { order, score };

    }

    public static void permutation() {
        for (int i = 0; i < N; i++) {
            int first = players.get(i).get(0);

            players.get(i).remove(0);
            Collections.sort(players.get(i));

            do {
                List<Integer> temp = new LinkedList<>(players.get(i));
                temp.add(3, first);
                permus.get(i).add(temp);
                // System.out.println("되나?" + i);
            } while (NP(i));
        }
    }

    public static boolean NP(int index) {
        int i = 7;

        while (i > 0 && players.get(index).get(i - 1) >= players.get(index).get(i)) {
            --i;
            // System.out.println(players.get(index).get(i - 1));
            // System.out.println(players.get(index).get(i));
        }
            

        if (i == 0) {
            return false;
        }

        int j = 7;

        while (players.get(index).get(i - 1) >= players.get(index).get(j))
            --j;

        swap(i - 1, j, index);

        int k = 7;

        while (i < k) {
            swap(i++, k--, index);
        }

        return true;
    }
    
    public static void swap(int x, int y, int index) {
        int temp = players.get(index).get(x);
        players.get(index).set(x, players.get(index).get(y));
        players.get(index).set(y, temp);
    }
}
