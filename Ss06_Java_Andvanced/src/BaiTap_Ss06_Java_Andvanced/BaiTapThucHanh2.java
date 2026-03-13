package BaiTap_Ss06_Java_Andvanced;

public class BaiTapThucHanh2 {

    // =============================
    // Lớp quản lý việc in số
    // =============================
    static class NumberPrinter {

        private int count = 1;
        private final int MAX = 10;
        private boolean isOddTurn = true;

        // In số lẻ
        public synchronized void printOdd() {

            while (count <= MAX) {

                while (!isOddTurn) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count <= MAX) {
                    System.out.println("Thread lẻ: " + count);
                    count++;
                    isOddTurn = false;
                    notify();
                }
            }
        }

        // In số chẵn
        public synchronized void printEven() {

            while (count <= MAX) {

                while (isOddTurn) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count <= MAX) {
                    System.out.println("Thread chẵn: " + count);
                    count++;
                    isOddTurn = true;
                    notify();
                }
            }
        }
    }

    // =============================
    // Main
    // =============================
    public static void main(String[] args) {

        NumberPrinter printer = new NumberPrinter();

        Thread oddThread = new Thread(() -> {
            printer.printOdd();
        });

        Thread evenThread = new Thread(() -> {
            printer.printEven();
        });

        oddThread.start();
        evenThread.start();
    }
}