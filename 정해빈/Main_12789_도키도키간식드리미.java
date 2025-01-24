import java.util.*;
import java.io.*;

public class Main_12789_도키도키간식드리미 {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        int snack = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }

        while (!queue.isEmpty() || !stack.isEmpty()) {
            if (!stack.isEmpty() && stack.peek() == snack) {
                stack.pop();
                snack++;
            } else if (!queue.isEmpty() && queue.peek() == snack) {
                queue.poll();
                snack++;
            } else if (!queue.isEmpty()) {
                if (!stack.isEmpty() && stack.peek() < queue.peek()) {
                    System.out.println("Sad");
                    return;
                }
                stack.push(queue.poll());
            }
        }

        if (snack == N + 1) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}
