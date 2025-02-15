package PACKAGE_NAME;

import java.util.ArrayDeque;

public class Main_03큐_마이쮸 {
    public static void main(String[] args) {
        ArrayDeque<Pair> q= new ArrayDeque<>();
        int student_idx = 0;
        int candy_cnt = 20;
        while(true){
            if(!q.isEmpty()){
                Pair temp = q.poll();
                candy_cnt-=temp.num;
                if(candy_cnt<=0){
                    System.out.println(temp.id);
                    break;
                }
                q.add(new Pair(temp.id, temp.num+1));
            }
            student_idx++;
            q.add(new Pair(student_idx, 1));

        }
    }

    static class Pair{
        int id;
        int num;
        public Pair(int id, int num){
            this.id = id;
            this.num = num;
        }
    }
}
