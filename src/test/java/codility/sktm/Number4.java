package codility.sktm;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Number4 {

    @Test
    public void test1() {
//        assertEquals(2, solution(new int[]{5, 3, 6, 1, 3}, 2));
//        assertEquals(0, solution(new int[]{8, 8, 4, 3,}, 2));
        assertEquals(1, solution(new int[]{3, 5, 1, 3, 9, 8}, 4));
    }

    // 연속된 요소를 k만큼 지워야 함
    // 남은 숫자의 큰값과 작은값의 차이가 가장 작도록 지워야 함
    public int solution(int[] A, int K) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;

        int i = 0;
        int kk = K - 1 + i;
        while (i+K <= A.length) {
            List<Integer> list = getListWith(A);
            while(kk >= i) {
                list.remove(kk--);
            }
            for (Integer e : list) {
                if (max < e) {
                    if (max != Integer.MIN_VALUE && max < min) {
                        min = max;
                    }
                    max = e;
                }
                else if (min > e) {
                    min = e;
                }
            }

            if (result > max - min) {
                result = max - min;
            }

            i++;
            kk = K - 1 + i;
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
        }


        return result;
    }

    private List getListWith(int[] A) {
        List<Integer> list = new ArrayList<>();
        for (int e : A) {
            list.add(e);
        }
        return list;
    }
}
