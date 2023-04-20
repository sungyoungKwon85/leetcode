package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class PalindromeNumber {
  @Test
  public void test1(){
    Assert.assertEquals(true, isPalindrome(121));
  }
  @Test
  public void test2(){
    Assert.assertEquals(false, isPalindrome(-121));
  }
  @Test
  public void test3(){
    Assert.assertEquals(false, isPalindrome(10));
  }

  @Test
  public void test(){
  }

  public boolean isPalindrome(int n) {
    if (n < 0) return false;
    if (n < 10) return true;

    int x = n;
    int y = 0;
    Palindrome pr = (x1, y1) -> (y1 * 10) + x1 % 10;
    while (x > 0) {
      y = pr.calc(x, y);
      x = x / 10;
    }
    return n == y;
  }
  @FunctionalInterface
  public interface Palindrome {
    int calc(int x, int y);
  }
}
/**
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 * (An integer is a palindrome when it reads the same forward and backward)
 *
 * Could you solve it without converting the integer to a string?
 */