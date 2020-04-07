import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SortOrder;

import org.junit.Assert;
import org.junit.Test;

public class SameSumDice {
/*
There are two sets of traditional six-faced dice, represented by two arrays A and B consisting of N and M integers respectively. Each array element has a value between 1 and 6, inclusive, representing the value of the corresponding die.

Write a function:

class Solution { public int solution(int[] A, int[] B); }

that, given two arrays A and B, returns the minimum number of dice to be turned in order to make the sums of dice in both sets equal, or -1 if this is not possible.



Examples:

1. Given A = [5], B = [1, 1, 6], the function should return 1. We have to turn the third die in B from 6 to 3; then the arrays will have the same sums (5 = 1 + 1 + 3). 2. Given A = [2, 3, 1, 1, 2], B = [5, 4, 6], the function should return 2. We can turn last two dice in B to get [5, 1, 3]; then the arrays will have the same sums.

3. Given A = [5, 4, 1, 2, 6, 5], B = [2], the function should return 6. We can turn ve dice in A to get [1, 1, 1, 1, 1, 1] and one dice in B to get [6]; then the arrays will have the same sums.

4. Given A = [1, 2, 3, 4, 3, 2, 1], B = [6], the function should return -1, because it is not possible for the arrays to have the same sums. Write an ecient algorithm for the following assumptions:

N and M are integers within the range [1..100,000];
each element of arrays A, B is an integer within the range [1..6].
 */

    @Test
    public void test1() {
        int solution = solution(new int[]{5}, new int[]{1,1,6});
        Assert.assertEquals(solution, 1);
    }
    @Test
    public void test2() {
        int solution2 = solution(new int[]{2, 3, 1, 1, 2}, new int[]{5, 4, 6});
        Assert.assertEquals(solution2, 2);
    }
    @Test
    public void test3() {
        int solution3 = solution(new int[]{5,4,1,2,6,5}, new int[]{2});
        Assert.assertEquals(solution3, 6);
    }
    @Test
    public void test4() {
        int solution4 = solution(new int[]{1,2,3,4,3,2,1}, new int[]{6});
        Assert.assertEquals(solution4, -1);
    }
    @Test
    public void test5() {
        int solution3 = solution(new int[]{5,4,1,2,6,5}, new int[]{2,3});
        Assert.assertEquals(solution3, 5);
    }
    @Test
    public void test6() {
        int solution3 = solution(new int[]{5,4,3,2,6,5}, new int[]{1});
        Assert.assertEquals(solution3, 7);
    }
    @Test
    public void test7() {
        int solution3 = solution(new int[]{5,4,3}, new int[]{1,1,3});
        Assert.assertEquals(solution3, 2);
    }
    @Test
    public void test8() {
        int solution3 = solution(new int[]{5,4,3}, new int[]{1,1,1});
        Assert.assertEquals(solution3, 3);
    }

    public int solution(int[] A, int[] B) {
        int count = 0;

        if (A.length == B.length) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] != B[i]) count++;
            }
            return count;
        }

        int[] shorter;
        int[] larger;

        if (A.length > B.length) {
            shorter = Arrays.stream(B).sorted().toArray();
            larger = Arrays.stream(A).sorted().toArray();
        } else {
            shorter = Arrays.stream(A).sorted().toArray();
            larger = Arrays.stream(B).sorted().toArray();
        }


        int sSum = Arrays.stream(shorter).sum();
        int lSum = Arrays.stream(larger).sum();

        int sMax = shorter.length * 6;

        int lMin = larger.length;

        // 짧은 배열이 최대치여도 마출 수 없음
        if (lMin > sMax) return -1;

        // 짧은 배열이 커서 먼저 바꿔봄
        if (sSum > lSum) {
            for (int i = shorter.length-1; i >=0; i--) {
                sSum += (1 - shorter[i]);
                count++;
                if (sSum == lSum || sSum < lSum) {
                    return count;
                }
            }
        }

        // 짧은 배열이 너무 작아 무조건 손봐야함
        if (sSum < lMin) {
            for (int i = 0; i < shorter.length; i++) {
                sSum += (6 - shorter[i]);
                count++;
                if (sSum >= lMin)
                    break;
            }
        }

        int gap = sSum - lMin;
        if (sMax == sSum) {
            count += Arrays.stream(larger).filter(i -> i != 1).count();
        } else {
            for (int i = 0; i < larger.length; i++) {
                gap -= larger[i];
                if (gap < 0) {
                    count += (larger.length - i);
                    break;
                }
            }
        }
        return count;
    }
}
