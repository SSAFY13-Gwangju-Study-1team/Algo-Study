public class Solution_서버증설횟수 {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] server = new int[24];
        for(int t=0;t<=23;t++){
            if(players[t]<m) continue; // players가 3명 미만이면 skip
            if(players[t]/m>=server[t]){
                int diff = players[t]/m-server[t];
                answer += diff;
                for(int i=0;i<k;i++){
                    if(t+i<=23) {
                        server[t + i] +=diff;
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution_서버증설횟수 sol = new Solution_서버증설횟수();
        int res = sol.solution(new int[]{0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0}, 5, 1);
        System.out.println(res);
    }
}
