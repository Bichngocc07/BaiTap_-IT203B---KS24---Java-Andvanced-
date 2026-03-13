package BaiTap_Ss06_Java_Andvanced;

import java.util.LinkedList;
import java.util.Queue;

public class BaiTap3 {

    // ================= TICKET =================
    static class Ticket {
        private String id;

        public Ticket(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    // ================= TICKET POOL =================
    static class TicketPool {

        private Queue<Ticket> tickets = new LinkedList<>();
        private String roomName;
        private int counter = 1;

        public TicketPool(String roomName, int initialTickets) {
            this.roomName = roomName;

            for (int i = 0; i < initialTickets; i++) {
                addTicket();
            }
        }

        private void addTicket() {
            String id = roomName + "-" + String.format("%03d", counter++);
            tickets.add(new Ticket(id));
        }

        public synchronized Ticket sellTicket() {
            return tickets.poll();
        }

        public synchronized void addTickets(int count) {
            for (int i = 0; i < count; i++) {
                addTicket();
            }
            System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
        }

        public synchronized int remainingTickets() {
            return tickets.size();
        }
    }

    // ================= TICKET COUNTER =================
    static class TicketCounter implements Runnable {

        private String counterName;
        private TicketPool pool;
        private int sold = 0;

        public TicketCounter(String counterName, TicketPool pool) {
            this.counterName = counterName;
            this.pool = pool;
        }

        @Override
        public void run() {

            while (true) {

                Ticket ticket = pool.sellTicket();

                if (ticket == null) {
                    break;
                }

                System.out.println(counterName + " đã bán vé " + ticket.getId());
                sold++;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(counterName + " bán được: " + sold + " vé");
        }
    }

    // ================= TICKET SUPPLIER =================
    static class TicketSupplier implements Runnable {

        private TicketPool roomA;
        private TicketPool roomB;
        private int supplyCount;
        private int interval;
        private int rounds;

        public TicketSupplier(TicketPool roomA, TicketPool roomB,
                              int supplyCount, int interval, int rounds) {
            this.roomA = roomA;
            this.roomB = roomB;
            this.supplyCount = supplyCount;
            this.interval = interval;
            this.rounds = rounds;
        }

        @Override
        public void run() {

            for (int i = 0; i < rounds; i++) {

                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                roomA.addTickets(supplyCount);
                roomB.addTickets(supplyCount);
            }
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) throws InterruptedException {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        TicketCounter counter1 = new TicketCounter("Quầy 1", roomA);
        TicketCounter counter2 = new TicketCounter("Quầy 2", roomB);

        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 3, 3000, 3);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);
        Thread t3 = new Thread(supplier);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Kết thúc chương trình");
        System.out.println("Vé còn lại phòng A: " + roomA.remainingTickets());
        System.out.println("Vé còn lại phòng B: " + roomB.remainingTickets());
    }
}