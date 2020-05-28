package testdome.hc;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Test;

public class Test1 {

    @Test
    public void test1() {
        System.out.println(isValid("[{()}]"));
        System.out.println(isValid("[{()}"));
    }

    public static boolean isValid(String brackets) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pair = new HashMap<>();
        pair.put(')', '(');
        pair.put('}', '{');
        pair.put(']', '[');

        for (int i = 0; i < brackets.length(); i++) {
            char c = brackets.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() == pair.get(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
