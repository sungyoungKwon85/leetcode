package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import org.junit.Test;

public class LongestSubstring {
  /**
   * Given a string s, find the length of the longest substring without repeating characters.
   */

  @Test
  public void test1() throws Exception {
    String str = "abcabcbb";
    assertEquals(3, lengthOfLongestSubstring(str));
  }

  @Test
  public void test2() throws Exception {
    String str = "bbbbb";
    assertEquals(1, lengthOfLongestSubstring(str));
  }

  @Test
  public void test3() throws Exception {
    String str = "pwwkew";
    assertEquals(3, lengthOfLongestSubstring(str));
  }

  public int lengthOfLongestSubstring(String s) {
    char[] chars = s.toCharArray();
    int length = 0;
    LinkedList<Character> list = new LinkedList<>();
    for (int i = 0; i < chars.length; i++) {
      int indexOf = list.indexOf(chars[i]);
      if (indexOf != -1) {
        for (int j = 0; j <= indexOf; j++) {
          list.remove(0);
        }
      }
      list.add(chars[i]);
      length = length < list.size() ? list.size() : length;
    }
    return length;
  }


}
