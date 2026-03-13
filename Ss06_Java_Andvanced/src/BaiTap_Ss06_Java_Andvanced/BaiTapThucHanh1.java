package BaiTap_Ss06_Java_Andvanced;

public class BaiTapThucHanh1 {

    // Runnable in số chẵn
    static class EvenRunnable implements Runnable {

        @Override
        public void run() {

            for (int i = 2; i <= 10; i += 2) {

                System.out.println("Số chẵn: " + i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Runnable in số lẻ
    static class OddRunnable implements Runnable {

        @Override
        public void run() {

            for (int i = 1; i <= 9; i += 2) {

                System.out.println("Số lẻ: " + i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Main
    public static void main(String[] args) {

        EvenRunnable evenTask = new EvenRunnable();
        OddRunnable oddTask = new OddRunnable();

        Thread thread1 = new Thread(evenTask);
        Thread thread2 = new Thread(oddTask);

        thread1.start();
        thread2.start();

        System.out.println("Thread chính kết thúc");
    }
}