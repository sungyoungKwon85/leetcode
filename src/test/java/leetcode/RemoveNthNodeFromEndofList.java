package leetcode;

import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class RemoveNthNodeFromEndofList {

  @Test
  public void test1(){
    ListNode fifth = new ListNode(5);
    ListNode fourth = new ListNode(4, fifth);
    ListNode third = new ListNode(3, fourth);
    ListNode second = new ListNode(2, third);
    ListNode first = new ListNode(1, second);

    ListNode res = removeNthFromEnd(first, 2);
    System.out.println();
    // 1,2,3,5
  }

  @Test
  public void test2(){
    ListNode root = new ListNode(1);

    ListNode res = removeNthFromEnd(root, 1);
    System.out.println();
    // []
  }

  @Test
  public void test3(){
    ListNode second = new ListNode(2);
    ListNode root = new ListNode(1, second);

    ListNode res = removeNthFromEnd(root, 1);
    System.out.println();
    // [1]
  }

  @Test
  public void test4(){
    ListNode second = new ListNode(2);
    ListNode root = new ListNode(1,second);

    ListNode res = removeNthFromEnd(root, 2);
    System.out.println();
    // [1]
  }

  @Test
  public void test5(){
    // 3,7,9,3,5,8,0
    ListNode r7 = new ListNode(0);
    ListNode r6 = new ListNode(8, r7);
    ListNode r5 = new ListNode(5, r6);
    ListNode r4 = new ListNode(3, r5);
    ListNode r3 = new ListNode(9, r4);
    ListNode r2 = new ListNode(7, r3);
    ListNode root = new ListNode(3, r2);

    ListNode res = removeNthFromEnd(root, 1);
    // 3 7 9 5 5 8
    System.out.println();
    // [1]
  }

  /**
   * Given the head of a linked list, remove the nth node from the end of the list and return its head.
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    // (뒤로는 못감)
    // 끝점을 찾는다
    // 카운트 한다
      // count = 5
    // 맵에 넣는다
      // key가.. 중복될 수 있구나...
      // (1, head) (2, se) (3, th) (4, fo) ...
    // count - n = 5 - 2 = 3
    // third
    //  다음의 fourth의 next = fifth
    // third와 fifth를 연결한다
    if (head.next == null && n == 1) return null;
    ListNode result = head;

    int count = 0;
    HashMap<Integer, ListNode> map = new HashMap<>();
    map.put(count++, head);
    while(head.next != null) {
      head = head.next;
      map.put(count++, head);
    }

    if (count > n) {
      ListNode prev = map.get(count - n - 1);
      prev.next = prev.next.next;

      return result;
    } else if (count == n) {
      ListNode next = result.next;
      result.next = null;
      return next;
    } else {
      return result;
    }
  }



  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


}
