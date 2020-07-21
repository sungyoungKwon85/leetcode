import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static MyData<Integer, Integer> myData = new MyData<>();

    public static void main(String[] args) {
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
    }

    public V get(K key) throws Exception {
        Node<K, V> node = map.get(key);
        if (node == null) {
            throw new Exception();
        }

        if (node == first) {
        } else if (node == last) {
            last = node.next;
            node.next = null;
            node.prev = first.next;
            first = node;

        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = first;
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
