package wb2021;

import org.junit.Assert;
import org.junit.Test;

public class Test1002_1
{

    /**
     * 완전이진트리
     * abdcef -> debfca
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        Solution solution = new Solution();
        String result = solution.solution("ABCDEF");
        System.out.println(result);
        Assert.assertEquals(result, "DEBFCA");
    }

}

class Solution {
    public String solution(String message) {
        BinaryTree bt = new BinaryTree();
        for (int i = 0; i <message.length(); i++) {
            char c = message.charAt(i);
            bt.insert(c+"");
        }

        String s = bt.backTraversal();
        System.out.println(s);
        return s;
    }
}
class BinaryTree {
    public Node root;
    private String searchData;

    class Node {
        private String data;
        private Node parent;
        private Node left;
        private Node right;

        public Node(String data) {
            this.data = data;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        public boolean isFull() {
            if (data != null && left != null && right != null) return true;
            return false;
         }
    }

    public void insert(String data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return;
        }

        Node current = root;
        Node parent = null;
        while (true) {
            if (current.parent != null && current.parent.left.isFull()) {
                parent = current;
            }
            else if (current.parent != null && !current.parent.left.isFull()) {
                parent = current.parent.left;
            }
            else parent = current;

            current = parent.left;
            if (current == null) {
                parent.left = node;
                parent.left.parent = parent;
                return;
            }
            current = parent.right;
            if (current == null) {
                parent.right = node;
                parent.right.parent = parent;
                return;
            }
        }
    }

    public String backTraversal() {
        backTraversal(root);
        return searchData;
    }
    private void backTraversal(Node node) {
        if (node != null) {
            backTraversal(node.left);
            backTraversal(node.right);
            if (searchData == null) searchData = "";
            searchData += node.data;
            System.out.println(node.data);
        }
    }
}
