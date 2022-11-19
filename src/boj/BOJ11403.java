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

public class BOJ11403 {
	public static void main(String[] args) throws IOException {
		BOJSolver solver = new BOJSolver();

		int size = solver.readInt();
		
		int[][] map = new int[size][size];
		int[][] result = new int[size][size];
		boolean[] checked;
		
		for ( int i = 0 ; i < size ; i ++ ) {
			solver.nextLine();
			
			for ( int j = 0 ; j < size ; j ++ ) {
				map[i][j] = solver.readInt();
			}
			
		}

		for ( int i = 0 ; i < size ; i ++ ) {
			checked = new boolean[size];
			
//			checked[i] = true;
			Deque<Integer> deque1 = new ArrayDeque<>();
			Deque<Integer> deque2 = new ArrayDeque<>();
			deque1.add(i);
			while( !deque1.isEmpty()) {
				while( !deque1.isEmpty()) {
					int item = deque1.poll();  
					for ( int j = 0 ; j < size ; j ++ ) {
						if ( map[item][j]==1 && !checked[j] ) {
							deque2.add(j);
							checked[j] = true;
						}
					}
				}
				
				while( !deque2.isEmpty() ) {
					deque1.push(deque2.poll());
				}
			}
			
			for ( int j = 0 ; j < checked.length ; j ++ ) {
				result[i][j] = checked[j]?1:0;
			}
		}
		
		
		for ( int i = 0 ; i < size ; i ++ ) {
			if ( i != 0 ) 
				solver.writeNewLine();
			
			for ( int j = 0 ; j < size ; j ++ ) {
				if ( j != 0 ) 
					solver.write(" ");
				
				solver.write(result[i][j]);
			}
			
		}
		
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
		/**
		 * Buffered Reader.nextLine()
		 */
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
/*
1 초	256 MB	36526	21498	15729	58.516%
문제
가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서
, i에서 j로 가는 경로가 있는지 없는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다. 
i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다. 
i번째 줄의 i번째 숫자는 항상 0이다.

출력
총 N개의 줄에 걸쳐서 문제의 정답을 인접행렬 형식으로 출력한다. 
정점 i에서 j로 가는 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력해야 한다.

예제 입력 1 
3
0 1 0
0 0 1
1 0 0
예제 출력 1 
1 1 1
1 1 1
1 1 1
예제 입력 2 
7
0 0 0 1 0 0 0
0 0 0 0 0 0 1
0 0 0 0 0 0 0
0 0 0 0 1 1 0
1 0 0 0 0 0 0
0 0 0 0 0 0 1
0 0 1 0 0 0 0

예제 출력 2 
1 0 1 1 1 1 1
0 0 1 0 0 0 1
0 0 0 0 0 0 0
1 0 1 1 1 1 1
1 0 1 1 1 1 1
0 0 1 0 0 0 1
0 0 1 0 0 0 0



*/