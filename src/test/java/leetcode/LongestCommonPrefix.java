package leetcode;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class LongestCommonPrefix {

  @Test
  public void test1(){
    Assert.assertEquals("fl", longestCommonPrefix(new String[]{"flower","flow","flight"}));
  }
  @Test
  public void test2(){
    Assert.assertEquals("", longestCommonPrefix(new String[]{"dog","racecar","car"}));
  }
  @Test
  public void test4(){
    Assert.assertEquals("dog", longestCommonPrefix(new String[]{"dog","doga","dogba"}));
  }
  @Test
  public void test5(){
    Assert.assertEquals("a", longestCommonPrefix(new String[]{"a"}));
  }
  @Test
  public void test6(){
    Assert.assertEquals("", longestCommonPrefix(new String[]{"", "bb"}));
  }
  @Test
  public void test7(){
    Assert.assertEquals("a", longestCommonPrefix(new String[]{"ab", "a"}));
  }
  @Test
  public void test8(){
    Assert.assertEquals("", longestCommonPrefix(new String[]{"caa","","a","acb"}));
  }
  @Test
  public void test9(){
    Assert.assertEquals("", longestCommonPrefix(new String[]{"baab","bacb","b","cbc"}));
  }
  @Test
  public void test10(){
    Assert.assertEquals("", longestCommonPrefix(new String[]{"ca","c","bba","bacb","bcb"}));
  }
  @Test
  public void test11(){
    Assert.assertEquals("ap", longestCommonPrefix(new String[]{"apa","apbc","ap","apb","apb"}));
  }
//  @Test
  public void test3(){ // 중간에 있으면??
    Assert.assertEquals("do", longestCommonPrefix(new String[]{"adog","rdoacecar","cdoar"}));
  }

  public String longestCommonPrefix(String[] strs) {
    StringBuilder sb = new StringBuilder();

    if (strs.length == 1) return strs[0];

    Map<Integer, String> map = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {

      if (i > 0 && map.isEmpty()) break;

      char[] chars = strs[i].toCharArray();
      if (chars.length == 0) return "";
      for (int j = 0; j < chars.length; j++) {
        String savedValue = map.get(j);
        if (i == 0 && savedValue == null) {
          map.put(j, String.valueOf(chars[j]));
        } else {
          if (savedValue == null || !savedValue.equals(String.valueOf(chars[j]))) {
            map.remove(j);
            break;
          }
        }
      }
      if (chars.length < map.size()) {
        map.remove(chars.length);
      }
    }

    if (map.get(0) == null) return "";
    for (int i = 0; i < map.size(); i++) {
      String s = map.get(i);
      if (s != null) sb.append(s);
      else break;
    }

    return sb.toString();
  }

  public String longestCommonPrefix2(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    String prefix = strs[0];

    for (int i = 1; i < strs.length; i++) {
      while (!strs[i].startsWith(prefix)) {
        prefix = prefix.substring(0, prefix.length() - 1);
        if (prefix.isEmpty()) {
          return "";
        }
      }
    }

    return prefix;
  }
}
