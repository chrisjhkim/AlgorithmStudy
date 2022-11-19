package algorithm.sample;

/**
 * 별찍기나 내부 채우기 (BOJ1074 등)와 같이 반복적으로 , recursive 하게 패턴을 갖고 실행되는 경우 이 class를 변경해서 사용한다.
 * 
 * 1. MINIMUM_SIZE 를 세팅하고 
 * 2. MINIMUM_SIZE 일때 수행될 (별을 찍거나 count 를 하거나 채우는 등의 행위) 최소 패턴을 구현
 * 3. Position interface 를 적절하게 구현해서 반복하게 만든다.
 * 
 * 
 * 4. 별찍기가 아닌 BOJ1074 와 같이 최종 값을 한번 뽑으면 되는 경우에는 recursiveDraw 를 무조건 호출하지 않고 shouldStopInThisPosition 함수를 수정하고 doCountInsteadOfRepeating 을 통해 반복하지 ㅇ낳고 숫자만 센다.    
 * 5. main method 에서 inputSize를 조절해가며 test 해볼것. 
 *
 */
public class RecursiveDraw {
	// 0 혹은 1로 사이즈 세팅한다.
	private static final int MININUM_SIZE = 0;
	
	/**
	 * recursive 하게 호출할때 처음 포지션
	 */
	private static final PositionImplExample initPosition = new PositionImplExample(0, 0);


	public static void main(String[] args) {
		int inputSize = 3;
		recursiveDraw(initPosition, inputSize);
	}
	

	/**
	 * 반복적으로 그린다.
	 * 
	 * @param position
	 * @param inputSize
	 */
	private static void recursiveDraw(Position positionInput, int inputSize) {
		if ( inputSize == MININUM_SIZE) {
			drawSmallestSize(positionInput);
		}else {
			Position[] innerPositions = positionInput.getInnerPositions(inputSize);

			for ( Position innerPosition : innerPositions ) {
				if ( shouldStopInThisPosition( innerPosition ,inputSize) ) {
					recursiveDraw(innerPosition, inputSize-1);
				}else {
					doCountInsteadOfRepeating(inputSize);
				}
			}
			
		}
		
	}




	/**
	 * position == targetEndPosition 조건을 만족해서 여기서 끝나게 되는지 여부를 확인한다.
	 * @param position
	 * @param inputSize
	 * @return 
	 */
	private static boolean shouldStopInThisPosition(Position position, int inputSize) {
		// TODO Auto-generated method stub
		// if ( position == targetEndPosition ){
			return true;
		//}else{
		// return false;
		//}
	}


	/**
	 * 출력 목표가 아닌 값을 계산해 내는것이 목적이라면 recursiveDraw 를 계속 수행할 필요 없이 
	 * 이 함수를 실행해서 값 계산만 한다.
	 * 예) BOJ 1074 의 경우 모든 Z를 그릴 필요 없이 이 함수를 통해 한개이상의 Z(크기 여부와 관계없음)의 숫자를 count 할 수 있다. 
	 * 
	 * @param inputSize
	 */
	private static void doCountInsteadOfRepeating(int inputSize) {
		// TODO Auto-generated method stub
		// counter += inputSize ; 등으로 숫자만 세고 종료한다.
		
	}

	/**
	 * 반복 패턴중 제일 작은 단위를 수행 
	 * 예) BOJ 1074 로 치면 하나의 Z 가 그려지는것.
	 * @param position
	 */
	private static void drawSmallestSize(Position position) {
		// TODO Auto-generated method stub
		// 여기에 그리는 함수 ( 혹은 반복적으로 실행될 패턴을 수행)
	}


	/**
	 * 필요에 따라서 row , col 이 아닌 다른 방식의 표현이 될 수 있다.
	 * @author chris1108
	 *
	 */
	private static class PositionImplExample implements Position{
		int row;
		int col;
		public PositionImplExample(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		@Override
		public Position getInitPosition(Object... values) {
			return new PositionImplExample(0, 0);
		}
		@Override
		public Position[] getInnerPositions(int size) {
			Position[] result= new Position[3];
			// 안쪽 포지션 1
			result[0] = new PositionImplExample(1111111, 111111111);
			// 안쪽 포지션 2
			result[1] = new PositionImplExample(2222222, 222222222);
			// 안쪽 포지션 3
			result[2] = new PositionImplExample(3333333, 333333333);
			return result;
		}
		
	}
	
	private static interface Position {
		/**
		 * 처음 위치
		 * @param values
		 * @return
		 */
		public Position getInitPosition(Object ... values);
		
		/**
		 * recursive 하게 호출할 안쪽 위치들
		 * @return
		 */
		public Position[] getInnerPositions(int size);
	}
}
