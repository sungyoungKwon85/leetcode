package testdome.hc;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Ship {
//    private Map<Integer, Integer> containers;
    private int[] containers;

    public Ship(int containerCount, Function<Integer, Integer> fillContainer) {
//        this.containers = new ConcurrentHashMap<>();
        this.containers = new int[containerCount];


        for (int i = 0; i < containerCount; i++) {
            this.containers[i] = fillContainer.apply(i);
//            this.containers.put(i, fillContainer.apply(i));
        }
    }

    public int peekContainer(int containerIndex) {
        return this.containers[containerIndex];
//        return this.containers.get(containerIndex);
    }

    public static void main(String[] args) {
        Ship ship = new Ship(100000, containerIndex -> containerIndex);

        for (int i = 0; i < 10; i++) {
            System.out.println("Container: " + i + ", cargo: " + ship.peekContainer(i));
        }
    }
}