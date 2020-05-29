package 동시성;

public class 철학자들의저녁식사 {

    public static void main(String[] args) {
//        String a = "qna_ask.question";
//        System.out.println(a.indexOf("{") > 0
//            && a.indexOf("}") > 0);

        try {
            DiningPhilosophers d = new DiningPhilosophers(5);
            d.startEating();
        } catch (InterruptedException e) {
        }
    }


    static class DiningPhilosophers {
        private Object[] forks;
        private Philosopher[] philosophers;

        // 포크와 철학자 준비
        private DiningPhilosophers(int num) {
            forks = new Object[num];
            philosophers = new Philosopher[num];

            for (int i = 0; i < num; i++) {
                forks[i] = new Object();

                // #2
                // 철학자가 홀수번 포크를 짝수번 포크보다 먼저 집어야 한다는 규칙을 만들자
                // 데드락을 피하는 방법이 될 수 있다
                int fork1 = i;
                int fork2 = (i + 1) % num;
                if ((i%2) == 0) {
                    philosophers[i] = new Philosopher(i, fork2, fork1);
                } else {
                    philosophers[i] = new Philosopher(i, fork1, fork2);
                }

                // #1
//                philosophers[i] = new Philosopher(i, i, (i + 1) % num);
            }
        }


        public void startEating() throws InterruptedException {
            for (int i = 0; i < philosophers.length; i++) {
                // 스케쥴러에서 각 스레드를 언제 돌릴지 알 수 없으므로 순서는 알 수 없음
                // (이게 멀티 스레드 코드를 디버깅 하기 어려운 이유 중 하나)
                philosophers[i].start();
            }

            // 첫번째 철학자가 먹는것을 중단할 때까지 주 스레드를 중단시킴
            // #1 하지만 첫번쩨 철학자가 중단하지 않으므로 시뮬레이션이 무한정 돌아간다
            philosophers[0].join();
        }


        class Philosopher extends Thread {
            private int id;
            private int fork1;
            private int fork2;

            Philosopher(int id, int fork1, int fork2) {
                this.id = id;
                this.fork1 = fork1;
                this.fork2 = fork2;
            }

            // #1
            // 왼쪽 포크를 모두 가지기 때문에 데드락에 빠진다
            public void run() {
                status("Ready to eat using forks " + fork1 + " and " + fork2);

                while (true) {
                    status("Picking up fork " + fork1);
                    synchronized (forks[fork1]) {
                        status("Picking up fork " + fork2);
                        synchronized (forks[fork2]) {
                            status("Eating");
                        }
                    }
                }
            }

            private void status(String msg) {
                System.out.println("Philosopher " + id + ": " + msg);
            }
        }
    }


}
