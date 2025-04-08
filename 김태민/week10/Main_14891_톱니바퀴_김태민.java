package Week10;

import java.io.*;
import java.util.*;

/**
 *
 * 조건
 *  - 톱니바퀴는 총 4개(각각 8개의 톱니를 가지고 있음)
 *  - 각각은 N또는 S극임
 *  - 회전은 시계방향 또는 반시계 방향
 *  - 하나의 톱니바퀴를 회전할떄--> 옆에 맞닿은 톱니바퀴와 극이 다르다면 돌리는거임
 *  - 아 슈밤 내가 문제를 잘 못 해석했던게 돌렸을떄 극이 다르면 계쏙 돌리는건줄 알았느데
 *      그게 아니라 하나의 톱니바퀴를 돌렸을때 양옆에 있는 톱니바퀴가 극이 다르면 돌리는거였음
 *  - 나는 돌렸을 때극이 다르면 극이 같아질 때 까지 계쏙 돌리는건줄 알았음
 *
 *  설계
 *   - 맞닿은 톱니 바퀴를 보면 2번 인덱스와 6번 인덱스의 값을 비교하면 됨.
 *      2와 6 또는 6이랑 2임
 *   - deque써서 관리하면 될듯?
 *   - 어짜피 극만 비교해서 돌리면 되니까 돌릴방향을 먼저 정해놓은 다음 한번에 돌리면 될듯?
 *
 *   - 3이라몀0
 *  필요한 함수:
 *  -
 *
 *
 */


public class Main_14891_톱니바퀴_김태민 {



    static Deque<Integer>[] wheel = new LinkedList[5];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i<5; i++){
            wheel[i] = new LinkedList<>();
        }

        String s;
        for(int i = 1; i<5; i++){
            s = br.readLine();
            for(int j = 0; j<8; j++){
                wheel[i].add(s.charAt(j)-'0');
            }

        }


        int n = Integer.parseInt(br.readLine());

        // 여기서 값을 받자마자 회전 돌려버릴거임
        for(int p = 0; p<n; p++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int vec = Integer.parseInt(st.nextToken());

            int[] rotate = new int[5];
            rotate[num] = vec;

            // 왼쪽을 돌림
            for(int i = num; i>1; i--){
                int left = i-1;
                int leftWheel =((LinkedList<Integer>) wheel[left]).get(2);
                int rightWheel = ((LinkedList<Integer>) wheel[i]).get(6);

                if(leftWheel!=rightWheel){
                    rotate[left] = -rotate[i];
                } else {
                    break;
                }
            }
            // 오른쪽 돌림
            for(int i = num; i<4; i++){
                int right = i+1;
                int leftWheel =((LinkedList<Integer>) wheel[i]).get(2);
                int rightWheel = ((LinkedList<Integer>) wheel[right]).get(6);

                if(leftWheel!=rightWheel){
                    rotate[right] = -rotate[i];
                } else {
                    break;
                }
            }

            // 회전 시키기
            for(int i = 1; i<=4; i++){
                if(rotate[i] ==1){
                    wheel[i].addFirst(wheel[i].removeLast());
                } else if (rotate[i]==-1) {
                    wheel[i].addLast(wheel[i].removeFirst());
                }
            }
        }
        int result = 0;
        for(int i = 1; i<5; i++){
            if(((LinkedList<Integer>) wheel[i]).get(0)==1){
                if (i == 1) {
                    result+=1;
                } else if (i==2) {
                    result+=2;
                } else if(i==3){
                    result+=4;
                } else if (i==4) {
                    result+=8;
                }
            }
        }
        System.out.println(result);

    }



}




