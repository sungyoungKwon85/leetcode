package leetcode;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class RomantoInteger {

  @Test
  public void test1(){
    Assert.assertEquals(3, romanToInt("III"));
  }
  @Test
  public void test2(){
    Assert.assertEquals(58, romanToInt("LVIII"));
  }
  @Test
  public void test3(){
    Assert.assertEquals(1994, romanToInt("MCMXCIV"));
  }

  public int romanToInt(String s) {
    int result = 0;
    Map<String, Integer> map = new HashMap<>();
    map.put("M", 1000);
    map.put("D", 500);
    map.put("C", 100);
    map.put("L", 50);
    map.put("X", 10);
    map.put("V", 5);
    map.put("I", 1);
    map.put("CM", 900);
    map.put("CD", 400);
    map.put("XC", 90);
    map.put("XL", 40);
    map.put("IX", 9);
    map.put("IV", 4);

    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      String cur = String.valueOf(chars[i]);
      if (i != chars.length - 1 && ("C".equals(cur) || "X".equals(cur) || "I".equals(cur))) {
        Integer value = map.get(cur + chars[i + 1]);
        if (value != null) {
          result += value;
          i+= 1;
          continue;
        }
      }
      Integer value = map.get(cur);
      result += value;
    }
    return result;
  }

}
/**
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * IV: 4, IX: 9, XL: 40, XC: 90, CD: 400, CM: 900
 */