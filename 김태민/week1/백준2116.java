package argoStudy;

import java.util.*;
import java.io.*;
public class Main2346 {
    static final int DICE_NUM = 6;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dice = new int[n][DICE_NUM];



        for (int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<DICE_NUM; j++){
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        int top;
        int bottom;

        for (int i = 0; i<DICE_NUM; i++){
            bottom = dice[0][i];
            top = dice[0][getPair(i)];
            int sum = 0;
            sum += getMax(dice[0], bottom, top);

            for (int j = 1; j<n; j++){
                for (int k = 0; k<DICE_NUM; k++){
                    if (dice[j][k] == top){
                        bottom = dice[j][k];
                        top = dice[j][getPair(k)];
                        sum += getMax(dice[j], bottom, top);
                        break;
                    }

                }
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);

    }

    // 다이스의 밑면(bottom)이 정해지면 다이스의 윗면(top)은 자동으로 정해짐
    // 계산을 자동으로 해주는 함수들을 만듬
    public static int getPair(int bottom){
        switch (bottom){
            case 0:
                return 5;

            case 1:
                return 3;

            case 2:
                return 4;

            case 3:
                return 1;

            case 4:
                return 2;

            case 5:
                return 0;

            default:
                return -1;
        }
    }
    public static int getMax(int[] dice, int bottom, int top){
        int max = 0;
        for(int i = 0; i<DICE_NUM; i++){
            if(dice[i] != bottom && dice[i] != top){
                max = Math.max(max, dice[i]);
            }
        }
        return max;
    }



}












