package li2020;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * User: kkwonsy
 * Date: 2020/07/21 10:00 오후
 */
public class Test3 {

    @Test
    public void test1() {
        MyData<Integer, Integer> myData = new MyData();

        try {
            myData.add(5, 3);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.add(1, 2);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(5));
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.evict();
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(1));
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(5));
        } catch (Exception e) {
            System.out.println(-1);
        }
    }

    @Test
    public void test2() {
        MyData<Integer, Integer> myData = new MyData();

        try {
            myData.add(5, 3);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.add(1, 2);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(5)); // 3
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(1)); // 2
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.evict();
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(5)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
    }

    @Test
    public void test3() {
        MyData<Integer, Integer> myData = new MyData();

        try {
            System.out.println(myData.get(5)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(1)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.evict(); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(5)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
    }

    @Test
    public void test4() {
        MyData<Integer, Integer> myData = new MyData();

        try {
            System.out.println(myData.get(5)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(1)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.evict();
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(5)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
    }

    @Test
    public void test5() {
        MyData<Integer, Integer> myData = new MyData();

        try {
            myData.add(5, 3);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.add(1, 2);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.add(3, 7);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(5)); // 3
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(1)); // 2
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(3)); // 7
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.evict();
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(5)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.evict();
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(1)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(3)); // 7
        } catch (Exception e) {
            System.out.println(-1);
        }
    }

    @Test
    public void test6() {
        MyData<Integer, Integer> myData = new MyData();

        try {
            myData.add(5, 3);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.add(1, 2);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.add(3, 7);
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(5)); // 3
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(1)); // 2
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.get(3)); // 7
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.evict();
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(5)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            myData.evict();
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(1)); // -1
        } catch (Exception e) {
            System.out.println(-1);
        }
        try {
            System.out.println(myData.remove(3)); // 7
        } catch (Exception e) {
            System.out.println(-1);
        }
    }

}
class Node<K, V> {
    K key;
    V value;
    Node next;
    Node prev;
}

class MyData<K, V> {
    private Map<K, Node<K, V>> map;
    private Node first;
    private Node last;

    public MyData() {
        this.map = new HashMap<>();
    }

    public void evict() {
        if (last == null) {
            return;
        }
        map.remove(last.key);
        last = last.next;
    }

    public void add(K key, V value) {
        Node<K, V> node = new Node<>();
        node.key = key;
        node.value = value;
        if (map.isEmpty()) {
            first = node;
            last = node;
        } else {
            first.next = node;
            node.prev = first;
            first = node;
        }
        map.put(key, node);
        System.out.println();
    }

    public V get(K key) throws Exception {
        Node<K, V> node = map.get(key);
        if (node == null) {
            throw new Exception();
        }

        if (node == first) {
        } else if (node == last) {
            node.next.prev = null;
            node.next.next = node;
            last = node.next;
            node.next = null;
            node.prev = first;
            first.next = node;
            first = node;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = first;
            first.next = node;
            first = node;
        }

        return node.value;
    }

    public V remove(K key) throws Exception {
        Node<K, V> node = map.get(key);
        if (node == null) {
            throw new Exception();
        }
        map.remove(key);

        return node.value;
    }
}
/*public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        while(sc.hasNext()) {
            String input = sc.nextLine();
            String[] strings = input.split(" ");
            String command = strings[0];
            try {
                switch (command) {
                    case "add":
                        if (strings.length != 3) {
                            System.out.println("add command missed key value");
                            break;
                        }
                        myData.add(Integer.valueOf(strings[1]), Integer.valueOf(strings[2]));
                        break;
                    case "get":
                        if (strings.length != 2) {
                            System.out.println("get command missed key");
                            break;
                        }
                        System.out.println(myData.get(Integer.valueOf(strings[1])));
                        break;
                    case "remove":
                        if (strings.length != 2) {
                            System.out.println("remove command missed key");
                            break;
                        }
                        System.out.println(myData.remove(Integer.valueOf(strings[1])));
                        break;
                    case "evict":
                        myData.evict();
                        break;
                    case "exit":
                        return;
                }
            } catch (Exception e) {
                System.out.println(-1);
            }
        }
    }*/