package algo_study.week07;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1339_단어수학_정해빈 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(br.readLine());
		String[] words = new String[N];
		
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
		}
		
		HashMap<Character, Integer> map = new HashMap<>();

		for(String word : words) {
			int len = word.length();
			for(int i=0; i<len; i++) {
				char c = word.charAt(i);
				int priority = (int) Math.pow(10, len-i-1); // 자릿수로 우선순위 매기기
				map.put(c, map.getOrDefault(c, 0) + priority); // 이미 우선순위가 정해져 있으면 더해준 값으로 갱신 
			}
		}
		
		// 검색 찬스 !! Map에 있는 값을 내림차순 하는 법 -> 리스트로 변환 후 정렬 
		List<Map.Entry<Character, Integer>> sortedList = new ArrayList<>(map.entrySet());
		sortedList.sort((a,b)->b.getValue()-a.getValue()); // 내림차순 정렬 
		
		HashMap<Character, Integer> charToNum = new HashMap<>();
		int num = 9;
		for(Map.Entry<Character, Integer> entry : sortedList) { 
			charToNum.put(entry.getKey(), num--); // 우선순위에 맞게 큰 순서부터 9,8,7,... 숫자 매칭 
		}
		
		int sum = 0;
		for(String word : words) {
			int numValue = 0;
			for(char c : word.toCharArray()) {
				numValue = numValue * 10 + charToNum.get(c);
			}
			sum+= numValue;
		}
		
		System.out.println(sum);

	}

}
