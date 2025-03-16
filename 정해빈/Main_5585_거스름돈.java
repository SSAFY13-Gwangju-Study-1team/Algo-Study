package algo_study;

import java.util.Scanner;

public class Main_5585_거스름돈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int money = 1000 - sc.nextInt();
		int[] changes = {500, 100, 50, 10, 5, 1};
		int coin = 0;
		for (int change : changes) {
			if(money==0) break;
			
			coin += money/change;
			money = money%change;
			
		}
		System.out.print(coin);

	}

}