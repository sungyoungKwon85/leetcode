package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class StringtoInteger {

  @Test
  public void test(){
    Assert.assertEquals(42, myAtoi("42"));
  }
  @Test
  public void test2(){
    Assert.assertEquals(-42, myAtoi("   -42"));
  }
  @Test
  public void test3(){
    Assert.assertEquals(4193, myAtoi("4193 with words"));
  }
  @Test
  public void test4(){
    Assert.assertEquals(0, myAtoi("words and 987"));
  }
  @Test
  public void test5(){
    Assert.assertEquals(2147483647, myAtoi("91283472332"));
  }
  @Test
  public void test7(){
    Assert.assertEquals(-2147483648, myAtoi("-91283472332"));
  }
  @Test
  public void test6(){
    Assert.assertEquals(3472332, myAtoi("0003472332"));
  }
  @Test
  public void test8(){
    Assert.assertEquals(0, myAtoi("00000-42a1234"));
  }
  @Test
  public void test9(){
    Assert.assertEquals(0, myAtoi("  +  413"));
  }

  @Test
  public void te12st(){
    String a = "09Az +-";// 48, 57
    char[] chars = a.toCharArray();
    for (char c: chars) {
      System.out.println((int)c);
    }

    StringBuilder sb = new StringBuilder();
    String s = sb.toString();
    System.out.println("".equals(s));
  }

  public int myAtoi(String s) {
    StringBuilder sb = new StringBuilder();
    boolean hasStarted = false;
    boolean isPositive = true;
    boolean hasPlusMinus = false;

    char[] chars = s.toCharArray();
    for (char c: chars) {
      if (c >= 48 && c <= 57) { // digit
        sb.append(c);
        hasPlusMinus = false;
        hasStarted = true;
      } else if (c == 45) { // minus
        if (sb.length() > 0) break;
        hasStarted = true;
        isPositive = !isPositive;
        if (hasPlusMinus) {
          return 0;
        } else {
          hasPlusMinus = true;
        }
      } else if (c == 43) { // plus
        if (sb.length() > 0) break;
        hasStarted = true;
        if (hasPlusMinus) {
          return 0;
        } else {
          hasPlusMinus = true;
        }
      } else if (c == 32) { // white splace
        if (sb.length() > 0 || hasStarted) break;
        hasPlusMinus = false;
      } else {
        break;
      }
    }

    int digit;
    if ("".equals(sb.toString())) {
      return 0;
    } else {
      try {
        digit = Integer.parseInt(sb.toString());
        return !isPositive ? digit * -1 : digit;
      } catch (NumberFormatException e) {
        return !isPositive ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      }
    }
  }
}
/**
 * converts a string to a 32-bit signed integer
 *
 * ignore any leading whitespace.
 *
 * '-' or '+' This determines if the final result is negative or positive respectively
 *  positive if neither is present.
 *
 * Read in next the characters until the next non-digit character or the end of the input is reached
 */
