package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddTwoNumbers {

  /**
   * You are given two non-empty linked lists representing two non-negative integers.
   * The digits are stored in reverse order, and each of their nodes contains a single digit.
   * Add the two numbers and return the sum as a linked list.
   * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
   */
  public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  @Test
  public void test1() throws Exception {
    // l1 = [1,4,3], l2 = [5,6,4]
    // [7,0,9]
    ListNode node11 = new ListNode(1);
    ListNode node12 = new ListNode(4);
    ListNode node13 = new ListNode(3);
    ListNode node21 = new ListNode(5);
    ListNode node22 = new ListNode(6);
    ListNode node23 = new ListNode(4);
    node11.next = node12;
    node12.next = node13;
    node21.next = node22;
    node22.next = node23;
    ListNode result = addTwoNumbers(node11, node21);
    System.out.println(result.val);
    System.out.println(result.next.val);
    System.out.println(result.next.next.val);
    assertEquals(6, result.val);
    assertEquals(0, result.next.val);
    assertEquals(8, result.next.next.val);
  }
  @Test
  public void test2() throws Exception {
    // l1 = [0], l2 = [0]
    // [0]
    ListNode node11 = new ListNode(0);
    ListNode node21 = new ListNode(0);
    ListNode result = addTwoNumbers(node11, node21);
    System.out.println(result.val);
    assertEquals(0, result.val);
  }
  @Test
  public void test3() throws Exception {
    // l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    // [8,9,9,9,0,0,0,1]
    ListNode node11 = new ListNode(9);
    ListNode node12 = new ListNode(9);
    ListNode node13 = new ListNode(9);
    ListNode node14 = new ListNode(9);
    ListNode node15 = new ListNode(9);
    ListNode node16 = new ListNode(9);
    ListNode node17 = new ListNode(9);
    ListNode node21 = new ListNode(9);
    ListNode node22 = new ListNode(9);
    ListNode node23 = new ListNode(9);
    ListNode node24 = new ListNode(9);
    node11.next = node12;
    node12.next = node13;
    node13.next = node14;
    node14.next = node15;
    node15.next = node16;
    node16.next = node17;
    node21.next = node22;
    node22.next = node23;
    node23.next = node24;
    ListNode result = addTwoNumbers(node11, node21);
    assertEquals(8, result.val);
    assertEquals(9, result.next.val);
    assertEquals(9, result.next.next.val);
    assertEquals(9, result.next.next.next.val);
    assertEquals(0, result.next.next.next.next.val);
    assertEquals(0, result.next.next.next.next.next.val);
    assertEquals(0, result.next.next.next.next.next.next.val);
    assertEquals(1, result.next.next.next.next.next.next.next.val);
  }

  private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    int carry = 0;

    while (l1 != null || l2 != null) {
      int sum = carry;
      if (l1 != null) {
        sum += l1.val;
        l1 = l1.next;
      }
      if (l2 != null) {
        sum += l2.val;
        l2 = l2.next;
      }
      curr.next = new ListNode(sum % 10);
      curr = curr.next;
      carry = sum / 10;
    }

    if (carry > 0) {
      curr.next = new ListNode(carry);
    }

    return dummy.next;
  }
}
