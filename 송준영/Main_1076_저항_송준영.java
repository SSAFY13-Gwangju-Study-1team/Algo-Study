import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1076_저항_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Map<String, Integer> map = new HashMap<>();
    static String[] colors = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};

    public static void main(String[] args) throws Exception {
        // 입력 처리
        for (int i = 0; i < colors.length; i++) {
            map.put(colors[i], i);
        }

        // 입력
        String color1 = br.readLine();
        String color2 = br.readLine();
        String color3 = br.readLine();

        // 출력
        System.out.println((long) (map.get(color1) * 10 + map.get(color2)) * (long) Math.pow(10, map.get(color3)));
        
    }
}
