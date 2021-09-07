package kkoent;

import org.junit.Test;

import java.util.Stack;

/**
 * User: kkwonsy
 * Date: 2021/06/13 12:39 오후
 */
public class TestK1
{
    @Test
    public void test1() throws Exception {
        int[] arr1 = new int[] {19,21,31,42,44};
        int m = 5;
        int n = 12;
        int[] arr2 = new int[] {1,2,3,4,15,16,17,28,29,30,41,43};

        int[] union = new int[m + n];
        int i = 0, j = 0, index = 0;
        while (i < m && j < n) {
            if (arr1[i] < arr2[j]) {
                union[index++] = arr1[i++];
            } else {
                union[index++] = arr2[j++];
            }
        }
        while (i < m) {
            union[index++] = arr1[i++];
        }
        while (j < n) {
            union[index++] = arr2[j++];
        }
        System.out.println();
    }

    @Test
    public void test333() throws Exception {
        char c = '3';
        int i = c - '0';
        System.out.println(i);
    }


    @Test
    public void test3() throws Exception {
//        System.out.println(fun3("2[ab]"));
        System.out.println(fun3("2[ab3[cd]]"));
        System.out.println(fun3("2[ab]3[cd]"));
        System.out.println(fun3("3[a2[bc]d]"));
    }

    private String fun3(String s) {
        Stack sbStack = new Stack<String>();
        Stack numStack = new Stack<Integer>();
        StringBuffer ssb = new StringBuffer();
        StringBuffer bsb = new StringBuffer();
        int n = 1;
        int numberOfGroup = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '2' && c <= '9') {
                if (numberOfGroup > 0 && n != 1) {
                    numStack.push(n);
                }
                n = c - '0';
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
                ssb.append(c);
            } else {
                if (c == '[') {
                    if (numberOfGroup > 0) {
                        sbStack.push(ssb.toString());
                        ssb = new StringBuffer();
                    }
                    numberOfGroup++;
                } else if (c == ']') {
                    numberOfGroup--;
                    if (ssb.length() > 0) {
                        String s1 = ssb.toString();
                        for (int j = 0; j < n - 1; j++) {
                            ssb.append(s1);
                        }
                        bsb.append(ssb);
                        ssb = new StringBuffer();
                        n = 1;
                    }

                    if (numberOfGroup < numStack.size()) {
                        Integer loop = (Integer) numStack.pop();
                        String str = (String) sbStack.pop();
                        StringBuffer newSb = new StringBuffer();
                        newSb.append(str).append(bsb);
                        bsb = new StringBuffer();
                        for (int j = 0; j < loop; j++) {
                            bsb.append(newSb);
                        }
                    }
                }
            }
        }

        return bsb.toString();
    }


    @Test
    public void test4() throws Exception {
        System.out.println(fun4("coding *ing"));
    }

    private boolean fun4(String s) {
        String[] s1 = s.split(" ");
        String content = s1[0];
        String pattern = s1[1];

        


        return false;
    }


}
