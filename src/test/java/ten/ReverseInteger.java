package ten;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/*
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:
Input: 123
Output: 321

Example 2:
Input: -123
Output: -321

Example 3:
Input: 120
Output: 21

Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
[−2^31,  2^31 − 1].
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {


    @Test
    public void test1() {
        Assertions.assertThat(reverse(123)).isEqualTo(321);
    }
    @Test
    public void test2() {
        Assertions.assertThat(reverse(-123)).isEqualTo(-321);
    }
    @Test
    public void test3() {
        Assertions.assertThat(reverse(120)).isEqualTo(21);
    }
    @Test
    public void test4() {
        Assertions.assertThat(reverse(1)).isEqualTo(1);
    }
    @Test
    public void test5() {
        Assertions.assertThat(reverse(-1)).isEqualTo(-1);
    }
    @Test
    public void test6() {
        Assertions.assertThat(reverse(-12301)).isEqualTo(-10321);
    }
    @Test
    public void test7() {
        Assertions.assertThat(reverse(1010101180)).isEqualTo(811010101);
    }
    @Test
    public void test8() {
//        Assertions.assertThat(reverse(9646324351)).isEqualTo(0);
    }

    public int reverse(int x) {
        String reversed = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try {
            return (x < 0) ? Integer.parseInt(reversed) * -1 : Integer.parseInt(reversed);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int reverse2(int x) {
        try {
            boolean isUnsigned = x < 0 ? true : false;
            long y = isUnsigned ? -1 * x : x;
            char[] chars = String.valueOf(y).toCharArray();
            for (int i = 0; i < chars.length / 2; i++) {
                char temp = chars[i];
                chars[i] = chars[chars.length - 1 - i];
                chars[chars.length - 1 - i] = temp;
            }
            int result = Integer.parseInt(String.valueOf(chars));
            return isUnsigned ? -1 * result : result;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
