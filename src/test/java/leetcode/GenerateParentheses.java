package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

public class GenerateParentheses {

  @Test
  public void test1(){
    String[] result = new String[]{"((()))","(()())","(())()","()(())","()()()"};
    Assert.assertArrayEquals(Arrays.stream(result).sorted().toArray(), generateParenthesis(3).stream().sorted().toArray());
  }

  @Test
  public void test2(){
    String[] result = new String[]{"()"};
    Assert.assertArrayEquals(result, generateParenthesis(1).toArray());
  }

  @Test
  public void test3(){
    String[] result = new String[]{"()()", "(())"};
    Assert.assertArrayEquals(Arrays.stream(result).sorted().toArray(), generateParenthesis(2).stream().sorted().toArray());
  }

  @Test
  public void test(){
    char[] chars = {'a', 'b'};
    System.out.println(Stream.of(chars).map(String::valueOf).collect(Collectors.joining()));
  }

  /**
   * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
   */
  static List<String> ans;
  static StringBuilder sb;
  public List<String> generateParenthesis(int n) {
    sb = new StringBuilder();
    ans = new LinkedList<>();
    dfs(0, 0, 0, n);
    return ans;
  }

  private void dfs(int depth, int openCnt, int closeCnt, int n) {

  }
































  private void dfs2(int depth, int openCnt, int closeCnt, int n) {
    if (depth ==  2 * n) {
      ans.add(sb.toString());
      return;
    }

    if (openCnt < n) {
      sb.append("(");
      dfs(depth + 1, openCnt + 1, closeCnt, n);
      sb.setLength(sb.length() - 1);
    }

    if(closeCnt < openCnt) {
      sb.append(")");
      dfs(depth + 1, openCnt, closeCnt + 1, n);
      sb.setLength(sb.length() - 1);
    }
  }

  public List<String> generateParenthesis2(int n) {
    List<String> res = new ArrayList<>();
    recursive(res, "(", n - 1, n);
    return res;
  }

  public void recursive(List<String> res, String s, int open, int close) {
    System.out.println("s: " + s);
    System.out.println("open: " + open);
    System.out.println("close: " + close);
    if (open == 0 && close == 0) {
      res.add(s);
      return;
    }

    if (open > 0) {
      recursive(res, s + "(", open - 1, close);
    }

    if (close > open) {
      recursive(res, s + ")", open, close - 1);
    }
  }
}
