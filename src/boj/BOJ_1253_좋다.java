package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_좋다 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] inputs = new int[n];
		for ( int i = 0 ;i < n ; i ++ ) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(inputs);
		
		int answer = 0;
		
		for ( int i = 0 ; i < n ; i ++ ) {
			int left = 0;
			int right = n-1;
			int sum = 0;
			while ( left < right ) {
				sum = inputs[left]+inputs[right];
				if ( left == i ) {
					left++;
				}else if ( right == i ) {
					right--;
				}else if ( sum < inputs[i] ) {
					left++;
				}else if ( inputs[i] < sum ) {
					right--;
				}else {
					answer++;
					break;
				}
			}
		}
		
		
		bw.write(String.valueOf(answer));
		bw.flush();
	}
	
}
/**
10
1 2 3 4 5 6 7 8 9 10


*/