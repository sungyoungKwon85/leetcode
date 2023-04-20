package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class IntegertoRoman {

  @Test
  public void test1(){
    Assert.assertEquals("III", intToRoman(3));
  }
  @Test
  public void test2(){
    Assert.assertEquals("LVIII", intToRoman(58));
  }
  @Test
  public void test3(){
    Assert.assertEquals("MCMXCIV", intToRoman(1994));
  }
  @Test
  public void test4(){
    Assert.assertEquals("I", intToRoman(1));
  }
  @Test
  public void test5(){
    Assert.assertEquals("IV", intToRoman(4));
  }
  @Test
  public void test6(){
    Assert.assertEquals("IX", intToRoman(9));
  }
  @Test
  public void test7(){
    Assert.assertEquals("MMMCMXCIX", intToRoman(3999));
  }
  @Test
  public void test8(){
    Assert.assertEquals("MMMCMXCVIII", intToRoman(3998));
    Assert.assertEquals("MMMCM", intToRoman(3900));
    Assert.assertEquals("MMMDCCC", intToRoman(3800));
    Assert.assertEquals("MMMDC", intToRoman(3600));
    Assert.assertEquals("MMMD", intToRoman(3500));
    Assert.assertEquals("MMMCD", intToRoman(3400));
    Assert.assertEquals("MMMCCC", intToRoman(3300));
  }

  public String intToRoman(int num) {
    StringBuilder sb = new StringBuilder();

    int[] units = {500, 100, 50, 10, 5, 1};
    String[] units2 = {"D", "C", "L", "X", "V", "I"};
    int[] specials = {900, 400, 90, 40, 9, 4};
    String[] specialsStr = {"CM", "CD", "XC", "XL", "IX", "IV"};

    int thousands = num / 1000;
    for (int i = 0; i < thousands; i++) {
      sb.append("M");
      num -= 1000;
    }

    for (int i = 0; i < units.length; i+=2) {
      int first = units[i];
      int second = units[i+1];
      int firstN = num / first;
      int remain = (num % first) / second;
      int secondN = firstN == 1 ? (num - first) / second : num / second;
      if (firstN == 1 && remain == 4) { // 900, 90
        sb.append(specialsStr[i]);
        num -= specials[i];
      } else if (firstN == 0 && remain == 4) { // 400, 40
        sb.append(specialsStr[i+1]);
        num -= specials[i+1];
      } else if (firstN == 1 && remain == 0) { // 500, 50, 5
        sb.append(units2[i]);
        num -= units[i];
      } else if (firstN == 1 && secondN > 0) { // 800, 80, 8
        sb.append(units2[i]);
        num -= units[i];
        for (int j = 0; j < secondN; j++) {
          sb.append(units2[i+1]);
          num -= units[i+1];
        }
      } else if (firstN == 0 && secondN > 0) { // 300, 30, 3
        for (int j = 0; j < secondN; j++) {
          sb.append(units2[i+1]);
          num -= units[i+1];
        }
      }
    }
    return sb.toString();
  }
}
/**
 * 1 <= num <= 3999
 *
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * IV: 4, IX: 9, XL: 40, XC: 90, CD: 400, CM: 900
 *
 */