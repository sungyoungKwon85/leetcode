package ygy;


import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * User: kkwonsy
 * Date: 2021/06/10 6:27 오후
 */
public class Testygy {
    @Test
    public void test1() {
        System.out.println(solution1(1073));
    }

    public int solution1(int N) {
        // 같은 자리수 젤 작은거??
        char[] chars = String.valueOf(N).toCharArray();
        int length = chars.length;
        if (length == 1) return 0;

        chars[0] = '1';
        for (int i = 1; i < length; i++) {
            chars[i] = '0';
        }

        return Integer.parseInt(String.valueOf(chars));
    }

    @Test
    public void test2() throws Exception {
        System.out.println(solution2(new int[]{1,2,1}));
        System.out.println(solution2(new int[]{1,1,1,1,1,1}));
        System.out.println(solution2(new int[]{6, 2, 3, 5, 6, 3}));
        System.out.println(solution2(new int[]{9, 9, 9, 9, 9, 9, 9, 9}));
        System.out.println(solution2(new int[]{6, 6, 6, 6, 6, 6}));
        System.out.println(solution2(new int[]{6, 6, 6, 4, 3, 2}));
        System.out.println(solution2(new int[]{6, 6, 6, 5, 4, 3}));
        System.out.println(solution2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    private int solution2(int[] ints) {
        // 배열의 한 요소를 한번에 1만큼 줄이거나 늘릴 수 있음
        // 1~N까지만 변경됨(요소의 최대값 이상 늘릴 수 없음)
        // 최소이동수를 구하라
        // 배열의 모든 요소를 쌍으로 표시
        // [1,2,1] -> 2: [1,2,2], [1,2,3]
        // [2,1,4,4] -> 1: [2,1,4,3]
        // [6,2,3,5,6,3] -> 4

        // 6 6 5 3 3 2 -> 0 1 2 0 1
        // 6 6 5 4 3 1 -> 0 1 1 1 2
        // 6 6 5 4 3 3 -> 0 1 1 1 0
        // 6 6 6 4 3 2 -> 0 0 2 1 1: 5+1=6
        // 6 6 6 5 4 3 -> 0 0 1 1 1: 5+4=9
        // 6 6 6 6 6 6 -> 0 0 0 0 0: 1+2+3+4+5=15
        // 9 9 9 9 9 9 -> 0 0 0 0 0: 15
        // 9 9 9 5 4 3 -> 0 0 4 1 1: 1+2=3
        // 3. 0이면 줄여야 하는데 다음 배열이 1이면 계속 1추가(0이어도 추가). 0이 나타나면 걔도 똑같이.
        int length = ints.length;
        int[] sorted = Arrays.stream(ints).sorted().toArray();
        Stack stack = new Stack<Integer>();
        for (int i = 0; i < length; i++) {
            stack.push(sorted[i]);
        }
        boolean shouldMinus = true;
        if (sorted[length - 1] < length) shouldMinus = false;

        int count = 0;
        Map checkMap = new HashMap<Integer, Boolean>();
        while (!stack.isEmpty()) {
            int value = (int) stack.pop();
            if (checkMap.get(value) == null) {
                checkMap.put(value, true);
            } else {
                int newValue = shouldMinus ? value - 1 : value + 1;
                stack.push(newValue);
                count++;
            }
        }

        return count;
    }

}
