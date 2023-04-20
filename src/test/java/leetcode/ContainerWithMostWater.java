package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class ContainerWithMostWater {
  @Test
  public void test1(){
    int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    int result = 49;
    Assert.assertEquals(result, maxArea(height));
  }
  @Test
  public void test3(){
    int[] height = {1, 8, 6, 20, 5, 4, 8, 3, 7};
    int result = 49;
    Assert.assertEquals(result, maxArea(height));
  }
  @Test
  public void test2(){
    int[] height = {1, 1};
    int result = 1;
    Assert.assertEquals(result, maxArea(height));
  }

  public int maxArea(int[] height) {
    int max = 0;
    int diff = height.length - 1;
    int left = 0;
    int right = height.length - 1;
    while(diff > 0) {
      int min;
      if (height[left] > height[right]) {
        min = height[right];
        right--;
      } else {
        min = height[left];
        left++;
      }
      if (max < min * diff) max = min * diff;
      diff--;
    }

    return max;
  }

}
/**
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 */