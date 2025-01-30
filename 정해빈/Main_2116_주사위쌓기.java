import java.io.*;
import java.util.*;

public class Main_2116_주사위쌓기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] dice = new int[N][6]; // 1차원 배열을 N번 반복문 돌리므로,,, 2차원 배열로 저장 
		
		// 입력 
		for(int i = 0; i < N; i++) { // N번 반복 
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j =0; j<6; j++) { // 각각 주사위 면의 눈의 수 입력받기 
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = 0; // 옆면의 최대합 저장할 변수(출력값)
		
		// 주사위 각각 면을 맞춰가며 주사위를 쌓아야 함.
		for(int i=0; i<6; i++) { // 첫번째면이 bottom일 때 옆면의 최댓값 -> 그다음 주사위의 옆면의 최댓값...
			int bottom = dice[0][i];
			int top = dice[0][getTopIndex(i)];
			int sum = 0;
			sum += getMax(dice[0], bottom, top);
			
			for(int j=1; j<N; j++) { // 첫번째주사위의 bottom, top은 정해졌으므로 ,, j=1부터! 
				for(int k=0; k<6; k++) { // j번째 주사위의 면을 탐색하면서 j-1의 top값이 같은 면을 bottom으로 지정 
					if(dice[j][k] == top) { // 이전 주사위의 윗면이 현재 주사위의 밑면이라면 
						bottom = dice[j][k]; // bottom으로 저장 
						top = dice[j][getTopIndex(k)]; // 맞은편 top값을 get
						sum += getMax(dice[j], bottom, top); // 현재 주사위의 옆면 최대값 sum 
						break;//찾았으면 반복문 종료 
					}
				}
			}
			// j번째주사위 옆면의 합sum과 max를 비교해 max 갱신
			max = Math.max(sum, max);			
		}
		System.out.println(max); // 최댓값 출력 	
	}
	public static int getTopIndex(int bottom) {
		switch(bottom) { // top-bottom 면 짝지어 주기
		case 0: return 5;
        case 1: return 3;
        case 2: return 4;
        case 3: return 1;
        case 4: return 2;
        case 5: return 0;
        default: return -1;
		}
	}
	
	// 현재 주사위의 옆면의 최댓값 구하는 메서드 
    public static int getMax(int dice[], int bottom, int top) { 
        int maxSide = 0;
        for (int i=0; i<6; i++) {
            if (dice[i] != bottom && dice[i] != top) // bottom, top이 아닌 옆면일때 
                maxSide = Math.max(maxSide, dice[i]); // 최대값 maxSide에 저장 
        }
        return maxSide;
    }

}
