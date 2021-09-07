package wb2021;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * User: kkwonsy
 * Date: 2021/07/24 4:17 오후
 */
public class Test0724_3 {
    @Test
    public void test2() throws Exception {
        System.out.println(solution(new int[]{3, 2, -2, 5, 3})); // 2
        System.out.println(solution(new int[]{3, 2, -2, 5, -3})); // 3
        System.out.println(solution(new int[]{5, 2, -2, 3, -3})); // 3
        System.out.println(solution(new int[]{1, 1, 2, -1, 2, -1})); // 1
        System.out.println(solution(new int[]{1, 2, 3, -4})); // 0
    }

    // 효율적으로 짜기
    // 만약 K, -K가 존재하면 그거중에 젤 큰거
    // 없으면 0
    public int solution(int[] A) {
        List<Integer> list = new ArrayList<>();
        for (int a : A) {
            list.add(a);
        }
        Collections.sort(list, (o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);
            if (abs1 > abs2) {
                return -1;
            } else if (abs1 < abs2) {
                return 1;
            }
            return 0;
        });

        for (int i = 0; i < list.size()-1; i++) {
            Integer value1 = list.get(i);
            Integer value2 = list.get(i+1);
            if (value1 == value2 * -1) {
                return Math.abs(value1);
            }
        }

        return 0;
    }


    @Test
    public void test4() throws Exception {
        System.out.println(solution(new int[]{1,3,2,1},new int[]{4,2,5,3,2})); // 2
        System.out.println(solution(new int[]{2,1},new int[]{3,3})); // -1
        System.out.println(solution(new int[]{7,3,5,1},new int[]{7,2,5,3,2})); // 3
        System.out.println(solution(new int[]{7,3,5,1},new int[]{7,2,5,3,1})); // 1
        // 1357, 12357
    }

    // 양수 어레이 2개
    // 둘다 있는 최소값
    // 없으면 -1
    int solution(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;;
        Arrays.sort(A);
        Arrays.sort(B);
//        System.out.print("A: ");
//        for (int a: A) {
//            System.out.print(a + ", ");
//        }
//        System.out.println();
//        System.out.print("B: ");
//        for (int b: B) {
//            System.out.print(b + ", ");
//        }
//        System.out.println();
        int i = 0;
        for (int k = 0; k < n-1;) {
//            System.out.print(", aIndex: " + k);
//            System.out.print(", bIndex: " + i);
//            System.out.print(", A: " + A[k]);
//            System.out.print(", B: " + B[i]);
//            System.out.println();
            if (i < m - 1 && B[i] < A[k])
                i += 1;
            else if (B[i] > A[k]) k++;

            if (A[k] == B[i])
                return A[k];
        }
        return -1;
    }

}
