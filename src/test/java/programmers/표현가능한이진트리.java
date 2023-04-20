package programmers;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class 표현가능한이진트리 {

  @Test
  public void test1(){
    int[] result = {1, 1, 0};
    Assert.assertArrayEquals(solution(new long[]{7, 42, 5}), result);
  }

  @Test
  public void test2(){
    int[] result = {1, 1, 0, 1};
    int[] solution = solution(new long[]{63, 111, 95, 250});
    Assert.assertArrayEquals(solution, result);
  }

  @Test
  public void test3(){
    int[] result = {1};
    int[] solution = solution(new long[]{2});
    Assert.assertArrayEquals(solution, result);
  }

  @Test
  public void test4(){
    int[] result = {0};
    int[] solution = solution2(new long[]{1023});
    Assert.assertArrayEquals(solution, result);
  }

  @Test
  public void test5(){
    long[] numbers = {1};
    int[] solution = solution(numbers);
    int[] solution2 = solution2(numbers);
    System.out.println(solution[0]);
    System.out.println(solution2[0]);
    Assert.assertEquals(solution2[0], solution[0]);
  }


  @Test
  public void test6(){
    long[] numbers = {423};
    int[] solution = solution(numbers);
    int[] solution2 = solution2(numbers);
    System.out.println(solution[0]);
    System.out.println(solution2[0]);
    Assert.assertEquals(solution2[0], solution[0]);
  }


  private boolean isBinaryTree2(String str) {
    int length = str.length();
    if (length == 0) return true;
      int root = length / 2;
      String leftStr = str.substring(0, root);
      String rightStr = str.substring(root + 1, length);

      if (str.charAt(root) == '0') {
        return isZeroTree(leftStr) && isZeroTree(rightStr);
      }

      return isBinaryTree2(leftStr) && isBinaryTree2(rightStr);
  }

  public int[] solution2(long[] numbers) {
    int[] answers = new int[numbers.length];
    for (int k = 0; k < numbers.length; k++) {
      String binary = getFullBinary(numbers[k]);
      if (binary.charAt(binary.length() / 2) == '0') {
        answers[k] = 0;
        continue;
      }
      if (isBinaryTree2(binary)) {
        answers[k] = 1;
      } else {
        answers[k] = 0;
      }
    }
    return answers;
  }

  private static String getFullBinary(long numbers) {
    String binary = Long.toBinaryString(numbers);
    int level = 1;
    double pow = Math.pow(2, level);
    while(binary.length() > pow - 1) {
      level++;
      pow = Math.pow(2, level);
    }
    int nodeCount = (int) pow -1;

    if (binary.length() < nodeCount) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < nodeCount - binary.length(); i++) {
        sb.append("0");
      }
      binary = sb.append(binary).toString();
    }
    return binary;
  }

  public int[] solution(long[] numbers) {
    List<Integer> answer = new ArrayList<>();

    for (long number : numbers) {
      if (isBinaryTree(number)) {
        answer.add(1);
      } else {
        answer.add(0);
      }
    }

    return answer.stream().mapToInt(Integer::intValue).toArray();
  }

  private boolean isBinaryTree(long number) {
    String binary = Long.toBinaryString(number);
    String fullBinary = getFullBinary(binary);
    int len = fullBinary.length();

    int root = len / 2;
    String leftSubTree = fullBinary.substring(0, root);
    String rightSubTree = fullBinary.substring(root + 1);

    if (fullBinary.charAt(root) == '0') {
      return false;
    }

    return isBinaryTree(leftSubTree) && isBinaryTree(rightSubTree);
  }

  private String getFullBinary(String binary) {

    int length = binary.length();
    int nodeCount = 1;
    int level = 1;
    while (length > nodeCount) {
      level *= 2;
      nodeCount += level;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < nodeCount - length; i++) {
      sb.append("0");
    }
    return sb.append(binary).toString();
  }

  private boolean isBinaryTree(String binary) {
    int len = binary.length();
    if (binary.length() == 0) return true;

    int root = len / 2;
    String leftSubTree = binary.substring(0, root);
    String rightSubTree = binary.substring(root + 1);

    if (binary.charAt(root) == '0') {
      return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
    }

    return isBinaryTree(leftSubTree) && isBinaryTree(rightSubTree);
  }

  private boolean isZeroTree(String binary) {
    int len = binary.length();
    if (binary.length() == 0) return true;

    int root = len / 2;
    String leftSubTree = binary.substring(0, root);
    String rightSubTree = binary.substring(root + 1);

    if (binary.charAt(root) == '1') {
      return false;
    }

    return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
  }

}

/**
 * https://acisliver.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%91%9C%ED%98%84-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%9D%B4%EC%A7%84%ED%8A%B8%EB%A6%AC-JAVA
 * 이진수를 저장할 빈 문자열을 생성
 * 주어진 이진트리에 더미 노드를 추가하여 포화 이진트리로 만듭니다. 루트 노드는 그대로 유지합니다.
 *
 * 포화 이진트리의 노드들을 가장 왼쪽 노드부터 가장 오른쪽 노드까지, 왼쪽에 있는 순서대로 살펴봅니다. 노드의 높이는 살펴보는 순서에 영향을 끼치지 않습니다.
 * 살펴본 노드가 더미 노드라면, 문자열 뒤에 0을 추가합니다. 살펴본 노드가 더미 노드가 아니라면, 문자열 뒤에 1을 추가합니다.
 * 문자열에 저장된 이진수를 십진수로 변환합니다.
 *
 *
 */