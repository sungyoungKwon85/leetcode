package 동시성;

import java.util.Random;

import org.junit.Test;

public class 생산자소비자 {

    @Test
    public void test1() {
        IntBuffer b = new IntBuffer();
        Producer p = new Producer(b);
        Consumer c = new Consumer(b);

        p.start();
        c.start();
    }
}

class IntBuffer {

    private int index;
    private int[] buffer = new int[8];


    // 이 접근법에는 두가지 문제가 있다.
    // 1. busy waiting을 사용한다. CPU낭비가 심하다.
    // 2. 공유자원인 버퍼에 대한 접근 제어가 전혀 안된다.
    // 인덱스 갱신 도중 스레드가 전환되면 다음 스레드에서 버퍼의 엉뚱한 원소에 대해 읽기/쓰기를 할 수 있다.
    // 1
//    public void add(int num) {
//        while (true) {
//            if (index < buffer.length) {
//                buffer[index++] = num;
//                return;
//            }
//        }
//    }
//    public int remove() {
//        while (true) {
//            if (index > 0) {
//                return buffer[--index];
//            }
//        }
//    }


    // add, remove에 syncronized를 쓰면 될까?
    // 버퍼가 가득 차 있거나 비어있을 때,
    // add가 busy waiting하게 되면 remove는 들어가지 못하고 영원히 꽉찬 상태가 되어 버린다.
    // 2
//    public synchronized void add(int num) {
//        while (true) {
//            if (index < buffer.length) {
//                buffer[index++] = num;
//                return;
//            }
//        }
//    }
//    public synchronized int remove() {
//        while (true) {
//            if (index > 0) {
//                return buffer[--index];
//            }
//        }
//    }

    // 3
    // 버퍼가 가득차면 생산자가 빈 공간이 생길 때까지 기다릴 수 있도록 하고
    //  버퍼가 비어 있으면 새로운 값이 들어올 때까지 소비자가 기다릴 수 있도록 하자
    public synchronized void add(int num) {
        while (index == buffer.length - 1) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        buffer[index++] = num;
        notifyAll();
    }
    public synchronized int remove() {
        while (index == 0) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        int ret = buffer[--index];
        notifyAll();
        return ret;
    }
}

class Producer extends Thread {

    private IntBuffer buffer;

    public Producer(IntBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        Random r = new Random();
        while (true) {
            int num = r.nextInt();
            buffer.add(num);
            System.out.println("Produced " + num);
        }
    }
}


class Consumer extends Thread {

    private IntBuffer buffer;

    public Consumer(IntBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            int num = buffer.remove();
            System.out.println("Consumed " + num);
        }
    }
}