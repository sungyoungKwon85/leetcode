package codility.sktm;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class Number1 {

    @Test
    public void test1() {
        Assert.assertEquals(5, solution(new int[]{3, 2, 1, 1, 2, 3, 1}));
        Assert.assertEquals(6, solution(new int[]{4, 1, 4, 1}));
        Assert.assertEquals(0, solution(new int[]{3, 3, 3}));
    }

    // 주어진 배열의요소는 스텝마다 1씩 줄거나 는다?
    // 모든요소가 같아질때까지 최소값을 리턴하라
    // {3,2,1,1,2,3,1} 리턴 5
    // {4,1,4,1} -> 6
    // {3,3,3} -> 0
    public int solution(int[] A) {
        int sum = Arrays.stream(A).sum();
        int rounded = Math.round((float) sum / (float) A.length);
        int count = 0;
        for (int e : A) {
            count += Math.abs(rounded - e);
        }
        return count;
    }

}
