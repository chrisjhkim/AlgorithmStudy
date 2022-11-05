package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/**
 * 랜선 자르기
 * BOJ 1654
 * https://www.acmicpc.net/problem/1654
 *
 */
public class BOJ1654 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int lineCount = Integer.parseInt(st.nextToken());
		int lineReq = Integer.parseInt(st.nextToken());
		
		int[] input = new int[lineCount];
		
		int left = 1;
		int right = 1; 
		for ( int i = 0 ; i < lineCount ; i ++ ) {
			input[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, input[i]);
		}
		
		while ( left < right ) {
			int mid = (int) ((left+(long)right+1)/2);
			
			int lineGot = extracted(input, mid);
			
			if ( lineGot < lineReq ) {
				right = mid -1;
			}else {
				left = mid;
			}
		}		
		
		bw.write(String.valueOf(right));
		bw.flush();
		bw.close();
		br.close();
	}

	private static int extracted(int[] input, int mid) {
		int lineGot = 0;
		for ( int i = 0 ; i < input.length ; i ++ ) {
			lineGot += (input[i]/mid); 
		}
		return lineGot;
	}
}



/*

4 5
10
1
1
1
----> 2

4 11
802
743
457
539
-----> 200
3 1
1000
1
1
----> 1000

3 2
1000
1
1
----->500
1 1 
500
-----> 500
1 1
2147483647
-----> 2147483647
1 2
2147483647
-----> 1073741823


*/
