package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
/**
 * BOJ 11724 연결 요소의 개수
 * 
 */
public class BOJ11724 {
	public static void main(String[] args) throws IOException {
		BOJSolver solver = new BOJSolver();

		int dotCountN = solver.readInt();
		int lineCountM = solver.readInt();
		
		
		boolean[][] map = new boolean[dotCountN+1][dotCountN+1];
		boolean[] checked = new boolean[dotCountN+1];
		checked[0] = true;
		
		for ( int i = 0 ;i < lineCountM ; i ++ ) {
			solver.nextLine();
			int a = solver.readInt();
			int b = solver.readInt();
			map[a][b] = true;
			map[b][a] = true;
		}
		int checkedCount = 0;
		
		
		Deque<Integer> deque = new ArrayDeque<>();
		Deque<Integer> deque2 = new ArrayDeque<>();
		
		int answerCount = 0;
		while ( checkedCount < dotCountN ) {
			int initDot = 0;
			for ( int i = 1 ; i < checked.length ; i ++ ) {
				if ( !checked[i]) {
					initDot = i;
					break;
				}
			}
			
			deque.add(initDot);
			
			while ( !deque.isEmpty() ) {
				while ( !deque.isEmpty()) {
					int item = deque.poll();
					for ( int j = 0 ; j < map.length; j ++ ) {
						if ( map[item][j] && !checked[j]) {
							deque2.add(j);
							checked[j] = true;
						}
					}
					checked[item] = true;
					checkedCount ++;
				}
				
				while ( !deque2.isEmpty() ) {
					deque.add(deque2.poll());
				}
				
			}
			answerCount ++;
		}
		
		
		
		solver.write(answerCount);
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
		
		public String readLine() throws IOException {
			return br.readLine();
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
