package com.weston.tools.util;

import java.math.BigDecimal;

/**
 * 编辑距离的两字符串相似度
 * 
 * @author jianpo.mo
 */
public class SimilarityUtil {

	private static int min(int one, int two, int three) {
		int min = one;
		if (two < min) {
			min = two;
		}
		if (three < min) {
			min = three;
		}
		return min;
	}

	public static int ld(String str1, String str2) {
		int d[][]; // 矩阵
		int n = str1.length();
		int m = str2.length();
		int i; // 遍历str1的
		int j; // 遍历str2的
		char ch1; // str1的
		char ch2; // str2的
		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // 初始化第一列
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) { // 初始化第一行
			d[0][j] = j;
		}
		for (i = 1; i <= n; i++) { // 遍历str1
			ch1 = str1.charAt(i - 1);
			// 去匹配str2
			for (j = 1; j <= m; j++) {
				ch2 = str2.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 左边+1,上边+1, 左上角+temp取最小
				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
				// printMarixInt(d);
			}
		}
		return d[n][m];
	}

	public static double sim(String str1, String str2) {
		int ld = ld(str1, str2);
		return 1 - (double) ld / Math.max(str1.length(), str2.length());
	}

	public static void printMarix(BigDecimal[][] marx) {
		for (int i = 0; i < marx.length; i++) {
			BigDecimal[] js = marx[i];
			for (int j = 0; j < js.length; j++) {
				System.out.print(marx[i][j].toString() + "	");
			}
			System.out.println();
		}
	}

	public static void printMarixInt(int[][] marx) {
		for (int i = 0; i < marx.length; i++) {
			int[] js = marx[i];
			for (int j = 0; j < js.length; j++) {
				System.out.print(marx[i][j] + "	");
			}
			System.out.println("");
		}
		System.out.println("-------------------");
	}

	public static BigDecimal[][] forMarix(String str1, String str2) {
		BigDecimal[][] result = new BigDecimal[str1.length()][str2.length()];
		for (int i = 0; i < result.length; i++) {
			BigDecimal[] js = result[i];
			for (int j = 0; j < js.length; j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					if (i == j) {
						result[i][j] = BigDecimal.ONE;
					} else {
						BigDecimal tmp = new BigDecimal(Math.min(i, j));
						BigDecimal pix = tmp.divide(new BigDecimal(Math.max(i, j)), 2, BigDecimal.ROUND_HALF_UP);
						result[i][j] = pix;
					}
				} else {
					result[i][j] = BigDecimal.ZERO;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {

		String str1 = "marhtaaaaaaaa";
		String str2 = "marthabbbbbb";
		String str3 = "marthabaaaaaa";
		System.out.println(sim(str1, str2));
		System.out.println(sim(str1, str3));
		// BigDecimal[][] marix = forMarix(str1, str2);
		// printMarix(marix);
		// System.out.println(countSimilar2(marix));
		printMarix(forMarix(str1, str3));
		System.out.println(countSimilar(forMarix(str1, str2)));
		System.out.println(countSimilar2(forMarix(str1, str2)));
	}

	public static int countSimilar(BigDecimal[][] marix) {
		BigDecimal sameCount = BigDecimal.ZERO;
		BigDecimal pixSum = BigDecimal.ZERO;
		boolean flag = true;
		for (int i = 0; i < marix.length; i++) {
			BigDecimal[] js = marix[i];
			for (int j = 0; j < js.length; j++) {
				if (i == j) {
					if (marix[i][j].compareTo(BigDecimal.ONE) != 0) {
						flag = false;
					}
				}
				if (marix[i][j].compareTo(BigDecimal.ZERO) == 0) {
					sameCount = sameCount.add(BigDecimal.ONE);
					pixSum = pixSum.add(BigDecimal.ONE);
				} else if (marix[i][j].compareTo(BigDecimal.ONE) == 0) {
					continue;
				} else {
					sameCount = sameCount.add(BigDecimal.ONE);
					pixSum = pixSum.add(marix[i][j]);
				}
			}
		}
		System.out.println(pixSum.divide(sameCount, 2, BigDecimal.ROUND_HALF_UP));
		if (marix.length == marix[0].length && flag) {
			return 100;
		} else {
			return pixSum.divide(sameCount, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_UP).intValue();
		}
	}

	public static int countSimilar2(BigDecimal[][] marix) {
		boolean flag = true;
		BigDecimal pp = BigDecimal.ZERO;
		for (int i = 0; i < marix.length; i++) {
			BigDecimal[] js = marix[i];
			BigDecimal inner = BigDecimal.ZERO;
			int innerCount = 0;
			boolean equalFlag = true;
			for (int j = 0; j < js.length; j++) {
				//System.out.println("处理:["+i+"]["+j+"]:"+marix[i][j]);
				if (i == j) {
					if (marix[i][j].compareTo(BigDecimal.ONE) == 0) {
						pp = pp.add(BigDecimal.ONE);
						equalFlag = false;
						break;
					}
				}
				if (marix[i][j].compareTo(BigDecimal.ZERO) != 0) {
					//System.out.println("I:" + i + "J" + j + "Value:" + marix[i][j].toString());
					inner = inner.add(marix[i][j]);
					innerCount++;
				}
			}
			if (innerCount != 0 && !equalFlag) {
				pp = pp.add(inner.divide(BigDecimal.valueOf(marix[i].length), 2, BigDecimal.ROUND_HALF_UP));
			}
			//System.out.println("PP："+pp);
		}
		if (marix.length == marix[0].length && !flag) {
			return 100;
		} else {
			BigDecimal temp = pp.divide(BigDecimal.valueOf(marix.length), 2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(Math.min(marix.length, marix[0].length)))
					.divide(BigDecimal.valueOf(Math.max(marix.length, marix[0].length)), 2, BigDecimal.ROUND_HALF_UP);
			return temp.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_UP).intValue();
		}
	}
}