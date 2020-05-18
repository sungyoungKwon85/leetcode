package codility;

import org.assertj.core.api.Assertions;
import org.junit.Test;

// 20200517 nv.fin.1
public class BTest {

    @Test
    public void test1() {
        String cdeo = solution("cdeo", new int[]{3, 2, 0, 1});
        Assertions.assertThat(cdeo).isEqualTo("code");
    }

    @Test
    public void test2() {
        String cdeenetpi = solution("cdeenetpi", new int[]{5, 2, 0, 1, 6, 4, 8, 3, 7});
        Assertions.assertThat(cdeenetpi).isEqualTo("centipede");
    }

    @Test
    public void test3() {
        String bytdag = solution("bytdag", new int[]{4, 3, 0, 1, 2, 5});
        Assertions.assertThat(bytdag).isEqualTo("bat");
    }

    public String solution(String S, int[] A) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        do {
            sb.append(S.charAt(index));
            index = A[index];
        } while (index != 0);

        return sb.toString();
    }
}

