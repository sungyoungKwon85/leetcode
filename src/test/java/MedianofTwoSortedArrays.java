import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */
public class MedianofTwoSortedArrays {

    @Test
    public void test1() {
        int[] a = {1,3};
        int[] b = {2};
        double result = findMedianSortedArrays(a, b);
        Assertions.assertThat(result).isEqualTo(2.0);
    }

    @Test
    public void test2() {
        int[] a = {1,2};
        int[] b = {3,4};
        double result = findMedianSortedArrays(a, b);
        Assertions.assertThat(result).isEqualTo(2.5);
    }

    @Test
    public void test3() {
        int[] a = {3};
        int[] b = {-2, -1};
        double result = findMedianSortedArrays(a, b);
        Assertions.assertThat(result).isEqualTo(-1);
    }

    /*
    Median은 중앙값이라고 표현한다
    Average인 평균값과는 다르다
    평균값은 극단적인 값에 영향을 받는다

    중앙값은 홀수 일때 가운데 값을,
    짝수 일때는 가운데 두 값의 산순평균 값을 가진다.
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] ints = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).sorted().toArray();
        int middleIndex = ints.length / 2;
        if (ints.length % 2 == 0) {
            return ((double)ints[middleIndex] + (double)ints[middleIndex-1]) / 2;
        } else {
            return ints[middleIndex];
        }
    }

}
