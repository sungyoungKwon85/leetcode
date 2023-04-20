package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

public class LetterCombinationsofaPhoneNumber {

  @Test
  public void test1(){
    String[] result = Stream.of("ad","ae","af","bd","be","bf","cd","ce","cf").sorted().toArray(String[]::new);
    Assert.assertArrayEquals(result, letterCombinations("23").stream().sorted().toArray());
  }
  @Test
  public void test2(){
    String[] result = new String[]{};
    Assert.assertArrayEquals(result, letterCombinations("").toArray());
  }
  @Test
  public void test3(){
    String[] result = Stream.of("b", "c", "a").sorted().toArray(String[]::new);
    Assert.assertArrayEquals(result, letterCombinations("2").stream().sorted().toArray());
  }
  @Test
  public void test4(){
    List<String> strings = letterCombinations("234");
  }

  @Test
  public void test(){
    String a = "23";
    System.out.println(a.charAt(0));
  }

  static String[] phone = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
  static String[] phone2 = {"", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
  static List<String> ans;

  public List<String> letterCombinations(String digits) {
    ans = new LinkedList<>();
    if ("".equals(digits)) {
      return ans;
    }

    StringBuilder sb = new StringBuilder();
    dfs(0, digits, sb);
    return ans;
  }

  private void dfs(int depth, String digits, StringBuilder sb) {
    if (depth == digits.length()) {
      ans.add(sb.toString());
      return;
    }

    int index = digits.charAt(depth) - 48;
    String candidates = phone2[index];
    for (int i = 0; i < candidates.length(); i++) {
      char value = candidates.charAt(i);
      sb.append(value);
      dfs(depth + 1, digits, sb);
      sb.setLength(sb.length() - 1);
    }
  }



























  public List<String> letterCombinations2(String digits) {
    ans = new LinkedList<>();
    if (digits.equals("")) return ans;
    StringBuilder sb = new StringBuilder();
    dfs2(0, digits, sb);
    return ans;
  }
  private void dfs2(int depth, String digits, StringBuilder sb) {
    if (depth == digits.length()) {
      ans.add(sb.toString());
      return;
    }

    int pos = digits.charAt(depth) - '2';
    String text = phone[pos];
    int n = text.length();
    for (int i = 0; i < n; i++){
      sb.append(text.charAt(i));
      dfs2(depth + 1, digits, sb);
      sb.setLength(sb.length() - 1);
    }
  }
}
/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 */
