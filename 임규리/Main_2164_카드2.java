import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_2164_카드2 {
    public static void main(String[] args) throws IOException {
        Deque<Integer> deque = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            deque.add(i + 1);
        }

        boolean isThrown = true;
        while (!(deque.size() == 1)) {
            if (isThrown) {
                deque.pollFirst();
                isThrown = false;
            } else {
                int temp = deque.pollFirst();
                deque.add(temp);
                isThrown = true;
            }
        }

        System.out.println(deque.peek());
    }
}
