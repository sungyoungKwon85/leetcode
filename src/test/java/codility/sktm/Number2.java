package codility.sktm;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Number2 {

    @Test
    public void test1() {
        assertEquals(7, solution("23"));
        assertEquals(11, solution("0081"));
        assertEquals(9, solution("022"));
    }

    // 숫자로 구성된 String S
    // 문자열에서 최대 한 자리를 다른 자리로 변경할 수 있습니다.
    // 이런 식으로 3으로 나눌 수있는 다른 수는 얼마나 되는가?
    public int solution(String S) {
        int result = 0;
        if (Integer.parseInt(S) % 3 == 0) {
            result++;
        }
        int i = 0;
        char[] changes = {'0','1','2','3','4','5','6','7','8','9'};
        while (i < S.length()) {
            char[] origin = S.toCharArray();
            char[] changeable = S.toCharArray();

            for (char change : changes) {
                if (change != origin[i]) {
                    changeable[i] = change;
                    String str = String.valueOf(changeable);
                    if (Integer.parseInt(str) % 3 == 0) {
                        result++;
                    }
                }
            }
            i++;
        }

        return result;
    }
}
