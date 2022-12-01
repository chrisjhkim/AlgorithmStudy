package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_11003_최솟값찾기 {

	

	public static void main(String[] args) throws IOException {
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.MAX_VALUE);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int nCount = Integer.parseInt(st.nextToken());
		int length = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Deque<Input> deque = new ArrayDeque<>();
		for ( int i = 0 ; i < nCount ; i ++ ) {
 			Input newItem = new Input(i, Integer.parseInt(st.nextToken()));
			
			while ( !deque.isEmpty() 
					&& newItem.value < deque.peekLast().value ) {
				deque.pollLast();
			}
			deque.addLast(newItem);
			
			
			Input minItem = deque.peekFirst();
			if ( i != 0) {
				bw.write(' ');
			}
			bw.write(String.valueOf(minItem.value));
			if ( i-minItem.pos+1 >= length) {
//			if ( minItem.pos <= i - length) {
				deque.pollFirst();
			}
		}
		
		int answer = -1;
		bw.write(answer);
		bw.flush();
		br.close();
		bw.close();

	}

	private static class Input{
		int pos;
		int value;
		public Input(int pos, int value) {
			this.pos = pos;
			this.value = value;
		}
		@Override
		public String toString() {
			return pos + "," + value;
		}
		
		
	}
}

/**
12 3
1 5 2 3 6 2 3 7 3 5 2 6

1 1 1 2 2 2 2 2 3 3 2 2

12 3
12 11 10 9 8 7 6 5 4 3 2 1

12 11 10 9 8 7 6 5 4 3 2 1


 * 
 * 
 */
