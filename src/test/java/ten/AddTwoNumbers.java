package ten;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddTwoNumbers {

    /**
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Example:
     *
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     */
    @Test
    public void test1() {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;

        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(4);
        n4.next = n5;
        n5.next = n6;

        ListNode listNode = secondSolution(n1, n4);
        assertEquals(listNode.val, 7);
        assertEquals(listNode.next.val, 0);
        assertEquals(listNode.next.next.val, 8);
    }

    @Test
    public void test2() {
        ListNode n1 = new ListNode(0);
        ListNode n4 = new ListNode(3);
        ListNode listNode = secondSolution(n1, n4);
        assertEquals(listNode.val, 3);
    }

    @Test
    public void test3() {
        ListNode n1 = new ListNode(6);
        ListNode n4 = new ListNode(4);
        ListNode listNode = secondSolution(n1, n4);
        assertEquals(listNode.val, 0);
        assertEquals(listNode.next.val, 1);
    }

    @Test
    public void test4() {
        ListNode n1 = new ListNode(8);
        ListNode n2 = new ListNode(9);
        ListNode n3 = new ListNode(9);
        n1.next = n2;
        n2.next = n3;

        ListNode n4 = new ListNode(2);

        ListNode listNode = secondSolution(n1, n4);
        assertEquals(listNode.val, 0);
        assertEquals(listNode.next.val, 0);
        assertEquals(listNode.next.next.val, 0);
        assertEquals(listNode.next.next.next.val, 1);
    }

    @Test
    public void test5() {
        ListNode n1 = new ListNode(9);
        ListNode n2 = new ListNode(9);
        ListNode n3 = new ListNode(9);
        ListNode n4 = new ListNode(9);
        ListNode n5 = new ListNode(9);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode nn = new ListNode(1);

        ListNode listNode = secondSolution(n1, nn);
        assertEquals(listNode.val, 0);
        assertEquals(listNode.next.val, 0);
        assertEquals(listNode.next.next.val, 0);
        assertEquals(listNode.next.next.next.val, 0);
        assertEquals(listNode.next.next.next.next.val, 0);
        assertEquals(listNode.next.next.next.next.next.val, 1);
    }

    public ListNode secondSolution(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        ListNode result = new ListNode(0);
        ListNode current = result;
        int overTen = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = overTen + x + y;
            overTen = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            p = p != null ? p.next : null;
            q = q != null ? q.next : null;
        }
        if (overTen > 0) {
            current.next = new ListNode(overTen);
        }
        return result.next;
    }

    public ListNode firstSolution(ListNode l1, ListNode l2) {
        ListNode tempNode1 = l1;
        String s1 = String.valueOf(l1.val);
        while(tempNode1.next != null) {
            tempNode1 = tempNode1.next;
            s1 = tempNode1.val + s1;
        }
        ListNode tempNode2 = l2;
        String s2 = String.valueOf(l2.val);
        while(tempNode2.next != null) {
            tempNode2 = tempNode2.next;
            s2 = tempNode2.val + s2;
        }

        String addup = "";
        String compare1 = s2;
        String compare2 = s1;
        int biggerSize = s2.length();
        if (s1.length() > s2.length()) {
            biggerSize = s1.length();
            compare1 = s1;
            compare2 = s2;
        }
        boolean isOverTen = false;
        int gap = Math.abs(s1.length() - s2.length());
        for (int i = biggerSize-1; i >= 0 ; i--) {
            int pick1 = compare1.charAt(i) - '0';
            if (i - gap < 0) {
                if (isOverTen) pick1++;
                if (pick1 >= 10) {
                    addup = (String.valueOf(pick1).charAt(1) - '0') + addup;
                    isOverTen = true;
                } else {
                    addup = pick1 + addup;
                    isOverTen = false;
                }
                continue;
            }
            int pick2 = compare2.charAt(i - gap) - '0';
            int sum = pick1 + pick2;
            if (isOverTen) sum++;
            if (sum >= 10) {
                addup = (String.valueOf(sum).charAt(1) - '0') + addup;
                isOverTen = true;
            } else {
                addup = sum + addup;
                isOverTen = false;
            }
        }
        if (isOverTen) addup = "1" + addup;

        ListNode result = null;
        ListNode next = null;
        for (int i = addup.length()-1; i >= 0 ; i--) {
            int val = addup.charAt(i) - '0';
            if (result == null) {
                result = new ListNode(val);
            } else {
                if (next == null) {
                    next = new ListNode(val);
                    result.next = next;
                } else {
                    ListNode nnext = new ListNode(val);
                    next.next = nnext;
                    next = nnext;
                }
            }
        }
        return result;
    }

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
