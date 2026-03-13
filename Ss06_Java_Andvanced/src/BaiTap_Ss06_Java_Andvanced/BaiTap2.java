package BaiTap_Ss06_Java_Andvanced;

import java.util.LinkedList;
import java.util.Queue;

public class BaiTap2 {

    static class Ticket {
        private String id;

        public Ticket(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    static class TicketPool {

        private String roomName;
        private Queue<Ticket> tickets = new LinkedList<>();
        private int ticketCounter = 1;

        public TicketPool(String roomName, int number) {
            this.roomName = roomName;

            for (int i = 1; i <= number; i++) {
                tickets.add(new Ticket(roomName + "-" + String.format("%03d", i)));
                ticketCounter++;
            }
        }

        // bán vé
        public synchronized Ticket sellTicket(String counterName) {

            while (tickets.isEmpty()) {

                try {
                    System.out.println(counterName + ": Hết vé phòng " + roomName + ", đang chờ...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            Ticket ticket = tickets.poll();
            return ticket;
        }

        // thêm vé mới
        public synchronized void addTickets(int number) {

            for (int i = 0; i < number; i++) {
                tickets.add(new Ticket(roomName + "-" + String.format("%03d", ticketCounter++)));
            }

            System.out.println("Nhà cung cấp: Đã thêm " + number + " vé vào phòng " + roomName);

            notifyAll(); // đánh thức tất cả quầy đang chờ
        }
    }

    static class BookingCounter extends Thread {

        private String counterName;
        private TicketPool pool;

        public BookingCounter(String name, TicketPool pool) {
            this.counterName = name;
            this.pool = pool;
        }

        @Override
        public void run() {

            while (true) {

                Ticket ticket = pool.sellTicket(counterName);

                if (ticket != null) {
                    System.out.println(counterName + " bán vé " + ticket.getId());
                }

                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        }
    }

    static class TicketSupplier extends Thread {

        private TicketPool pool;

        public TicketSupplier(TicketPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {

            try {

                while (true) {

                    Thread.sleep(5000); // mỗi 5s thêm vé

                    pool.addTickets(3);

                }

            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 3);
        TicketPool roomB = new TicketPool("B", 5);

        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomB);

        TicketSupplier supplier = new TicketSupplier(roomA);

        counter1.start();
        counter2.start();
        supplier.start();
    }
}