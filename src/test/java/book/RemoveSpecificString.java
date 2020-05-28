package book;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * User: kkwonsy
 * Date: 2020/05/26 9:03 오후
 */
public class RemoveSpecificString {
    @Test
    public void remove_specific_string_in_string() {
        final String origin = "Battle of the Vowels: Hawaii vs Grozny";
        final String remove = "aeiou";

        final String result = doFunction(origin, remove);
        Assertions.assertThat(result).isEqualTo("Bttl f th Vwls: Hw vs Grzny");
    }

    private String doFunction(String origin, String remove) {
        boolean[] flags = new boolean[origin.length()];

        for (int i = 0; i < origin.length(); i++) {
            char c = origin.charAt(i);
            if (remove.contains(String.valueOf(c))) {
                flags[i] = true;
            } else {
                flags[i] = false;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < flags.length; i++) {
            if (!flags[i]) {
                sb.append(origin.charAt(i));
            }
        }
        return sb.toString();
    }

    private String doFunction2(String origin, String remove) {
        for (int i = 0; i < remove.length(); i++) {
            char c = remove.charAt(i);
            origin = origin.replaceAll(String.valueOf(c), "");
            System.out.println(origin);
        }
        return origin;
    }
}
