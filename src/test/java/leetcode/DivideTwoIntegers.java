package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class DivideTwoIntegers {

  @Test
  public void test1(){
    Assert.assertEquals(3, divide(10, 3));
  }
  @Test
  public void test2(){
    Assert.assertEquals(-2, divide(7, -3));
  }
  @Test
  public void test4(){
    Assert.assertEquals(1, divide(2, 2));
  }
  @Test
  public void test5(){
    Assert.assertEquals(2147483647, divide(-2147483648, -1));
  }
  @Test
  public void test6(){
    Assert.assertEquals(-1073741824, divide(-2147483648, 2));
  }
  @Test
  public void test7(){
    Assert.assertEquals(-536870912, divide(-2147483648, 4));
  }

  @Test
  public void test(){
    Integer a = 10;
    String b = "10";
    System.out.println(3 << 2); // 12 mask 1
    System.out.println(1 << 1); // let 2
    System.out.println(3 << 1); // base 10 - 6 = 4
    System.out.println(3 << 1); // 6 mask 0
    System.out.println(1 << 0); // let 2+1 = 3
    System.out.println(3 << 0); // base 4 - 3 = 1
    // break;
  }

  /**
   * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
   * multiplication: 곱셈
   * dividend: 피제수
   * divisor: 제수
   *
   * The integer division should truncate toward zero, which means losing its fractional part.
   * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
   *
   * Return the quotient after dividing dividend by divisor.
   *
   * Note: Assume we are dealing with an environment
   * that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem,
   * if the quotient is strictly greater than 231 - 1, then return 231 - 1,
   * and if the quotient is strictly less than -231, then return -231.
   */

  public int divide(int dividend, int divisor) {
    final int max = Integer.MAX_VALUE;
    final int min = Integer.MIN_VALUE;

    boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
    long base = Math.abs((long) dividend);
    long div = Math.abs((long) divisor);

    long result = 0;

    while (base >= div) {
      int bit;
      for (bit = 0; bit < 30; bit++) {
        if (div << (bit + 1) > base) break;
      }

      result = result + (1 << bit);
      base -= div << bit;
    }

    if (isNegative) result *= -1;
    if (result > max) result = max;
    if (result < min) result = min;

    return (int) result;
  }

  public int divide2(int dividend, int divisor) {
    final int max = Integer.MAX_VALUE;
    final int min = Integer.MIN_VALUE;

    boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
    long base = Math.abs((long)dividend);
    long minus = Math.abs((long)divisor);

    if (minus == 1) {
      if (!isNegative && dividend == min) return max;
      if (divisor == 1) return dividend;
      if (divisor == -1) return -dividend;
    }

    long howMany = 0;
    while(base >= minus) {
      base -= minus;
      if (base >= 0) {
        howMany++;
      }
    }

    if (howMany > max) howMany = max;
    else if (howMany < min) howMany = min;

    if (isNegative) howMany *= -1;

    return (int) howMany;
  }
}
