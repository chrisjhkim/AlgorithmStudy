package math.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class {@code ChrisMathUtil} contains methods for performing 
 * numeric operations that {@link Math} class doesn't provides
 *
 * @author  chrisjhkim
 */
public final class ChrisMathUtil {
	/**
	 * Don't let anyone instantiate this class.
	 * methods of ChrisMathUtil should be accessed in a static way
	 */
	private ChrisMathUtil() {} 

	/**
	 * 
	 * @param numString1 String form positive integer
	 * @param numString2 String form positive integer 
	 * @return sum of numString1 and numString2 in String form
	 */
	public static String numberAdd(String numString1, String numString2) {
		int maxLength = Math.max(numString1.length(), numString2.length());
		
		final char[] arr1 = new char[maxLength];
		final char[] arr2 = new char[maxLength];
		
		System.arraycopy(numString1.toCharArray(), 0, arr1, maxLength-numString1.length(), numString1.length());
		System.arraycopy("0".repeat(maxLength-numString1.length()).toCharArray(), 0, arr1, 0, maxLength-numString1.length());
		
		System.arraycopy(numString2.toCharArray(), 0, arr2, maxLength-numString2.length(), numString2.length());
		System.arraycopy("0".repeat(maxLength-numString2.length()).toCharArray(), 0, arr2, 0, maxLength-numString2.length());
		boolean up = false;
		for ( int i = 0 ; i < maxLength ; i ++ ) {
			arr1[maxLength-1-i] += arr2[maxLength-1-i] + (up?1:0) -48;
			up = false;
			if ( arr1[maxLength-1-i] > 57) {
				arr1[maxLength-1-i] -=10;					
				up = true;
			}
		}
		if ( up ) {
			return new StringBuilder("1").append(arr1).toString();
		}else {
			return new String(arr1);
		}
		
	}
	
	public static String numberMultiply(String input1, String input2) {
		if ( input1.equals("1") ) return input2;
		if ( input2.equals("1") ) return input1;
		if ( input1.equals("0") || input2.equals("0") ) return "0";
		
		
		int maxAnswerLength = input1.length() + input2.length();
		
		final char[] arr1 = input1.toCharArray();
		final char[] arr2 = input2.toCharArray();
		System.out.println(Arrays.toString(arr1));
		System.out.println("X");
		System.out.println(Arrays.toString(arr2));
		char[][] note = new char[input2.length()][maxAnswerLength];
		System.err.println(Arrays.deepToString(note));
		int u,d;
		for ( int i = arr2.length-1 ; i >= 0 ; i -- ) {
			//de
			int up = 0;
			for ( int j = arr1.length-1 ; j >= 0 ; j -- ) {
				// abc
				u = (arr1[j]-48);
				d = (arr2[i]-48);
				note[i][j] = (char) (((u*d)+up)%10);
				up = ((u*d)+up)/10;
				
				System.out.println(j+ " " + i + " " +  (arr1[j]-48) + "x" +  (arr2[i]-48) +"="+ up + " " + (int)(note[i][j]));
				
			}
		}
		
		System.err.println(Arrays.deepToString(note));
		
		return null;
	}
/**
   abc
  X de
  ----
 EFGH
  ABCD 
 
  
 * 
 * 	
 */
	
	
	
	
	
	/**
	 * validate if the number is prime or not
	 * note that 1 , 0 and negetive numbers are not prime number
	 * @param input 
	 * @return true if the number is prime
	 */
	public static boolean isPrime(int input) {
		if ( input < 2) return false;
		for ( int i = 2 ; i <= Math.sqrt(input) ; i ++ ) {
			if ( input%i == 0 ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * num 을 소인수분해해서 소수들의 곱으로 표현합
	 * @param num
	 * @throws IOException
	 */
	public static String getFactorizationInPrimeFactors(int num) throws IOException {
		int input = num;
		System.out.println("input="+num);
		
		List<Integer> bases = new ArrayList<>();
		List<Integer> factors = new ArrayList<>();
		
		int testNum = num;
		int denominator = 2;
		while ( testNum >= denominator ) {
			boolean isFactor = false;
			int factorCount = 0;
			while ( testNum %denominator == 0) {
				isFactor = true;
				factorCount++;
				testNum /= denominator;
			}
			if ( isFactor ) {
				bases.add(denominator);
				factors.add(factorCount);
			}
			denominator++;
		}
		
		System.out.println("bases  = "+ bases);
		System.out.println("factors = "+ factors);
		StringBuilder sb = new StringBuilder();
		for ( int i = 0 ; i < bases.size(); i ++ ) {
			if ( i != 0 ) {
				sb.append(" + ");
			}
			sb.append(String.valueOf(bases.get(i)));
			sb.append('^');
			sb.append(String.valueOf(factors.get(i)));
		}
		sb.append(" = ");
		sb.append(String.valueOf(input));
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * factorialBaseNumber! ( factorialBaseNumber 의 factorial )값 을 소인수 분해 했을때 baseNum의 차수를 구함
	 * 예를들어 입력값으로 5, 2 가 주어지면
	 * 5! = 5*4*3*2*1 = 2^3*3*5 이므로 3을 return 한다.
	 * 
	 * @param factorialBaseNumber
	 * @param baseNum
	 * @return
	 */
	public static int getPrimeNumFactorOfFactorial(int factorialBaseNumber, int baseNum) {
		if ( baseNum == 0 ) {
			throw new IllegalArgumentException("zero is not prime number");
		}
		int counter = 0;
		while ( factorialBaseNumber >= baseNum ) {
			counter += factorialBaseNumber/baseNum;
			factorialBaseNumber /= baseNum;
		}
		return counter;
		
	}
	/**
	 * get GCD(Greatest Common Denominator :최대 공약수)를 구한다.
	 * @param n
	 * @param m
	 * @return
	 */
	public static int getGcdOf(int n, int m) {
		int result =1;
		int denominator = 2;
		
		while ( Math.min(n, m)/denominator >=1) {
			while ( n%denominator==0 && m%denominator==0) {
				result *= denominator;
				n /= denominator;
				m /= denominator;
			}
			denominator ++;
		}

		return result;
	}
	/**
	 * get LCM(Least Common Multiple : 최소공배수)를 구한다.
	 * @param n
	 * @param m
	 * @return
	 */
	public static int getLcmOf(int n, int m) {
		return n*m/getGcdOf(n, m);
	}
	

	/**
	 * n! / (r!*(n-r)!) 
	 * 이항계수 nCr 을 구함
	 * @param n
	 * @param r
	 * @return
	 */
	public static int binomial(int n, int r) {
		return binomialUsingDynamicProgramming(n, r);
	}
	
	
	
	/**
	 * 이항계수를 구할때 파스칼의 삼각형을 이용하면 
	 * binomail(n,r) = binomial(n-1,r-1) + binomial(n-1,r); 이 성립함을 이용한다.
	 * 
	 * factorial(n)/(factorial(r)*factorial(n-r)) 을 구하는것과 동일하다
	 * 
	 * _(n)C_(r)을 구하는것과 동일하다.
	 * 
	 * 예)
	 * AAAABB 를 나열하는 순서와 상관없이 배열하는 방법으로는 A,B의 총 개수 n=6 , A의 개수 r=4 (혹은 B의 개수 r=2)로
	 * 6!/(4!*2!) 로 구할 수 있으며 _(6)C_(4) 혹은 _(6)C_(2) 로 표현할 수 있는데 
	 * 이때 이 함수를 쓰면 된다.
	 *  
	 * 이때 n이 어느정도 값 이상되면 int 범위를 바로넘어가기 때문에 long 혹은 BigInteger 로 바꿔서 사용해야 하며
	 * 보통 문제의 경우 그 값을 10007 등의 소수로 나눠서 나온 나머지를 구하라는 식으로 출제된다. 
	 * 이런경우에는 페르마의 소정리를 추가로 적용해서 풀어야 한다.
	 *   
	 * @param n
	 * @param r
	 * @return
	 */
	private static int binomialUsingDynamicProgramming(int n, int r) {
		if ( dpArr == null ) {
			dpArr = new int[n+1][];
			for ( int i = 0 ; i < n+1 ; i ++ ) {
				dpArr[i] = new int[i+1];
			}
		}else if ( dpArr.length < n+1  ) {
			int[][] tempArr = new int[n+1][];
			for ( int i = 0 ; i < n+1 ; i ++ ) {
				tempArr[i] = new int[i+1];
				if ( dpArr.length-1 > i ) {
					System.arraycopy(dpArr[i], 0, tempArr[i], 0, dpArr[i].length);
				}
			}
			dpArr = tempArr;
		}
		
		
		if ( r == 0 || r == n ) {
			return 1;
		}else {
			if ( dpArr[n][r] == 0 ) {
				dpArr[n][r] = binomial(n-1,r-1) + binomial(n-1,r);
			}
			return dpArr[n][r];
		}
	}
	private static int[][] dpArr; // 동적 프로그래밍 
	
	
}
