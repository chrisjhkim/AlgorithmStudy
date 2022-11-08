package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 1931  
 * 회의실 배정 문제
 * 
 * 
 * 한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다. 
 * 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자. 
 * 단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 
 * 회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
 * 
 * 입력
 * 첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다. 
 * 둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 
 * 시작 시간과 끝나는 시간은 231-1보다 작거나 같은 자연수 또는 0이다.
 * 
 * 출력
 * 첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
 * 
 * 
 * 예제 입력 1 
 * 11
 * 1 4
 * 3 5
 * 0 6
 * 5 7
 * 3 8
 * 5 9
 * 6 10
 * 8 11
 * 8 12
 * 2 13
 * 12 14
 * 예제 출력 1 
 * 4
 * 
 *
 */
public class BOJ1931 {
	public static void main(String[] args) throws IOException {
		BOJSolver solver = new BOJSolver();
		
		int count = solver.readInt();
		int[][] inputs = new int[count][2];
		for ( int i = 0 ; i < count; i ++ ) {
			solver.nextLine();
			inputs[i][0] = solver.readInt();
			inputs[i][1] = solver.readInt();
		}
		
		// Greedy -> 종료 시간 기준으로 정렬.
		// 종료시간 같을 경우 시작 시간 기준 정렬
		// 시작시간 기준 정렬 하지 않으면
		// 3
		// 8 8
		// 4 8
		// 1 3 
		// 과 같은 input 들어왔을때 실패함
		Arrays.sort(inputs, (o1,o2)->(o1[1]==o2[1]?o1[0]-o2[0]:o1[1]-o2[1]));
		
		int pos = 1;
		int resultCount = 1;
		int lastFinishedTime = inputs[0][1];
		while ( pos < count ) {
			if ( inputs[pos][0] < lastFinishedTime ) {
			}else {
				lastFinishedTime = inputs[pos][1];
				resultCount++;
			}
			pos++;
		}
		
		solver.write(resultCount);
		solver.flushAndClose();
	}
	
	private static class BOJSolver{
		private StringTokenizer st;
		private BufferedReader br;
		private BufferedWriter bw;
		private BOJSolver() throws IOException {
			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}
		
		public void writeNewLine() throws IOException {
			bw.newLine();
			
		}

		public void flushAndClose() throws IOException {
			bw.flush();
			bw.close();
			br.close();
		}
		
		public void write(int input) throws IOException {
			this.write(String.valueOf(input));
		}
		public void write(String input) throws IOException {
			bw.write(input);
		}

		private int readInt() {
			return Integer.parseInt(st.nextToken());
		}
		private void nextLine() throws IOException {
			st = new StringTokenizer(br.readLine());
		}
		
		private static void print2DArray(int[][] arr) {
			for ( int i = 0 ; i < arr.length ; i ++ ) {
				System.out.println(Arrays.toString(arr[i]));
			}
		}
	}
	
}
