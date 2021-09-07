import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * User: kkwonsy
 * Date: 2021/06/13 12:43 오후
 */
public class MainKE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        try {
            List<String> list = new ArrayList<>();
            int loop = Integer.parseInt(input);
            for (int i = 0; i < loop; i++) {
                String matter = br.readLine();
                list.add(matter);
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println(fun3(list.get(i)));
            }

        } catch (IllegalArgumentException e) {

        }
    }

    private static String fun3(String s) {
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

    //    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String input = br.readLine();
//        try {
//            int numberOfQuestions = Integer.parseInt(input);
//            for (int i = 0; i < numberOfQuestions; i++) {
//                String question = br.readLine();
//                boolean isPreviousSpace = false;
//                StringBuffer sb = new StringBuffer();
//                for (int j = 0; j < question.length(); j++) {
//                    char c = question.charAt(j);
//                    if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))) {
//                        if (!isPreviousSpace) {
//                            sb.append(" ");
//                            isPreviousSpace = true;
//                        } else {
//                        }
//                    } else {
//                        sb.append(c);
//                        isPreviousSpace = false;
//                    }
//                }
//                String result = sb.toString();
//                System.out.println(result);
//            }
//
//        } catch (NumberFormatException e) {
//            System.out.println("Enter number!!");
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String input1 = br.readLine();
//            int m = Integer.parseInt(input1);
//            String contents1 = br.readLine();
//            int[] arr1 = Arrays.stream(contents1.split(" ")).mapToInt(Integer::parseInt).toArray();
//
//            String input2 = br.readLine();
//            int n = Integer.parseInt(input2);
//            String contents2 = br.readLine();
//            int[] arr2 = Arrays.stream(contents2.split(" ")).mapToInt(Integer::parseInt).toArray();
//
//            String input3 = br.readLine();
//            int k = Integer.parseInt(input3);
//
//            int[] union = new int[m + n];
//            int i = 0, j = 0, index = 0;
//            while (i < m && j < n) {
//                if (arr1[i] < arr2[j]) {
//                    union[index++] = arr1[i++];
//                } else {
//                    union[index++] = arr2[j++];
//                }
//            }
//            while (i < m) {
//                union[index++] = arr1[i++];
//            }
//            while (j < n) {
//                union[index++] = arr2[j++];
//            }
//            System.out.println();
//
//
//        } catch (IllegalArgumentException e) {
//            System.out.println("Input is wrong!!");
//        }
//
//
//        // 두 정렬된 인트 리스트, 사이즈를 받고 리스트를 받음. 두번.
//        // 두 리스트의 유니온안에서 k번째 가장 작은 것 찾기
//    }
}
