package codility.sktm;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Number3 {

    @Test
    public void test1() {
//        assertEquals(2, solution(new int[]{1, 3, 2, 1}, new int[]{4, 2, 5, 3, 2}));
//        assertEquals(-1, solution(new int[]{2, 1}, new int[]{3, 3}));
//        assertEquals(2, solution(new int[]{2, 3}, new int[]{1, 1, 2, 3}));
//        assertEquals(3, solution(new int[]{2, 3}, new int[]{4,4,4,4,4,3}));
        assertEquals(4, solution(new int[]{2, 5, 4}, new int[]{4,4,4,4,4,3}));
        assertEquals(1, solution(new int[]{1}, new int[]{4,4,6,3,6,8,3,1,2,1}));
    }

    // 비어있지 않은 배열
    // 양수
    // 리턴 최소값 of 양 배열에 있는 것
    // 없으면 -1
    // 아래코드는 비정확함
    int solution(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0;
        for (int k = 0; k < n; k++) {
            while (i < m - 1 && B[i] < A[k]) {
                i += 1;
            }
            if (A[k] == B[i]) {
                return A[k];
            }
        }
        return -1;
    }

}
