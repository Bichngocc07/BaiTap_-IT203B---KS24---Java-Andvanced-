package BaiTap_Ss06_Java_Andvanced;

import java.util.*;

public class BaiTapTongHop {

    // =========================
    // Ticket
    // =========================
    static class Ticket {
        private String ticketId;
        private String roomName;
        private boolean isSold;

        public Ticket(String ticketId, String roomName) {
            this.ticketId = ticketId;
            this.roomName = roomName;
            this.isSold = false;
        }

        public String getTicketId() {
            return ticketId;
        }

        public String getRoomName() {
            return roomName;
        }

        public boolean isSold() {
            return isSold;
        }

        public void setSold(boolean sold) {
            isSold = sold;
        }
    }

    // =========================
    // TicketPool
    // =========================
    static class TicketPool {

        private String roomName;
        private List<Ticket> tickets = new ArrayList<>();
        private int counter = 1;

        public TicketPool(String roomName, int initial) {
            this.roomName = roomName;

            for (int i = 0; i < initial; i++) {
                tickets.add(new Ticket(roomName + "-" + String.format("%03d", counter++), roomName));
            }
        }

        public synchronized Ticket sellTicket() {

            while (tickets.isEmpty()) {
                try {
                    System.out.println("Kho " + roomName + " hết vé, đang chờ thêm...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Ticket t = tickets.remove(0);
            t.setSold(true);
            return t;
        }

        public synchronized void addTickets(int count) {

            for (int i = 0; i < count; i++) {
                tickets.add(new Ticket(roomName + "-" + String.format("%03d", counter++), roomName));
            }

            System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);

            notifyAll();
        }

        public synchronized int getAvailableCount() {
            return tickets.size();
        }
    }

    // =========================
    // BookingCounter
    // =========================
    static class BookingCounter implements Runnable {

        private String counterName;
        private TicketPool roomA;
        private TicketPool roomB;
        private int totalSold = 0;
        private Random random = new Random();

        public BookingCounter(String name, TicketPool a, TicketPool b) {
            this.counterName = name;
            this.roomA = a;
            this.roomB = b;
        }

        public int getTotalSold() {
            return totalSold;
        }

        @Override
        public void run() {

            try {

                while (true) {

                    Ticket ticket;

                    if (random.nextBoolean()) {
                        ticket = roomA.sellTicket();
                    } else {
                        ticket = roomB.sellTicket();
                    }

                    if (ticket != null) {

                        totalSold++;

                        System.out.println(counterName + " bán vé "
                                + ticket.getTicketId() + " (Phòng " + ticket.getRoomName() + ")");
                    }

                    Thread.sleep(500);

                }

            } catch (InterruptedException e) {
                System.out.println(counterName + " kết thúc.");
            }
        }
    }

    // =========================
    // TicketSupplier
    // =========================
    static class TicketSupplier implements Runnable {

        private TicketPool roomA;
        private TicketPool roomB;
        private int supplyCount;
        private int interval;
        private int times;

        public TicketSupplier(TicketPool a, TicketPool b, int supplyCount, int interval, int times) {
            this.roomA = a;
            this.roomB = b;
            this.supplyCount = supplyCount;
            this.interval = interval;
            this.times = times;
        }

        @Override
        public void run() {

            try {

                for (int i = 0; i < times; i++) {

                    Thread.sleep(interval);

                    roomA.addTickets(supplyCount);
                    roomB.addTickets(supplyCount);

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) throws Exception {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter c1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter c2 = new BookingCounter("Quầy 2", roomA, roomB);
        BookingCounter c3 = new BookingCounter("Quầy 3", roomA, roomB);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);

        TicketSupplier supplier =
                new TicketSupplier(roomA, roomB, 5, 5000, 2);

        Thread supplierThread = new Thread(supplier);

        t1.start();
        t2.start();
        t3.start();
        supplierThread.start();

        Thread.sleep(20000);

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("\n===== TỔNG KẾT =====");

        System.out.println("Quầy 1 bán: " + c1.getTotalSold());
        System.out.println("Quầy 2 bán: " + c2.getTotalSold());
        System.out.println("Quầy 3 bán: " + c3.getTotalSold());

        System.out.println("Vé còn lại phòng A: " + roomA.getAvailableCount());
        System.out.println("Vé còn lại phòng B: " + roomB.getAvailableCount());
    }
}