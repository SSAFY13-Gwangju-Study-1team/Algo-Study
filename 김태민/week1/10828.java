
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int cnt = 0;
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            String[] s = scanner.nextLine().split(" ");

            if (s[0].equals("push")){
                arr[cnt++] = Integer.parseInt(s[1]);
            } else if (s[0].equals("pop")) {
                if (cnt ==0){
                    System.out.println(-1);
                } else {
                    System.out.println(arr[--cnt]);
                }
            } else if (s[0].equals("size")) {
                if (cnt == 0){
                    System.out.println(0);
                } else {
                    System.out.println(cnt);

                }
            } else if(s[0].equals("empty")) {
                if (cnt == 0){
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (s[0].equals("top")){
                if(cnt == 0){
                    System.out.println(-1);
                } else {
                    System.out.println(arr[cnt-1]);
                }
            }

        }
    }
}
