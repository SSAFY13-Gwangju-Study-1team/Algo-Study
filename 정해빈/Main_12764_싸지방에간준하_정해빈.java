package algo_study.week09;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_12764_싸지방에간준하_정해빈 {
	
	static class User implements Comparable<User>{
		int start, end;

		public User(int start, int usedTime) {
			this.start = start;
			this.end = start + usedTime;
		}

		@Override
		public int compareTo(User o) {
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(br.readLine());
		User[] users = new User[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			users[i] = new User(parseInt(st.nextToken()), parseInt(st.nextToken()));
		}
		
		Arrays.sort(users);

		PriorityQueue<Integer> availableSeats = new PriorityQueue<>();
		// usedSeats PQ 수정
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
		    @Override
		    public int compare(int[] a, int[] b) {
		        if (a[0] == b[0]) return a[1] - b[1];
		        return a[0] - b[0];
		    }
		});

		int[] count = new int[N + 1];
		int seatNo = 0;
		int maxSeatNum = -1;

		for (User user : users) {
			while (!usedSeats.isEmpty() && usedSeats.peek()[0] <user.start) {
				availableSeats.add(usedSeats.poll()[1]);
			}

			int assignedSeat;
			if (!availableSeats.isEmpty()) {
				assignedSeat = availableSeats.poll();
			} else {
				assignedSeat = seatNo++;
			}

			usedSeats.add(new int[]{user.end, assignedSeat});
			count[assignedSeat]++;
			maxSeatNum = Math.max(maxSeatNum, assignedSeat);
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
		    if (count[i] == 0) break;
		    cnt++;
		}
		System.out.println(cnt);
		for (int i = 0; i < cnt; i++) {
		    System.out.print(count[i] + " ");
		}

	}
}
