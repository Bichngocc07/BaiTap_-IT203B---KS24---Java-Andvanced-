package BaiTap_Ss06_Java_Andvanced;

import java.util.*;

public class BaiTap4 {

    // ================= TICKET =================
    static class Ticket {
        String ticketId;
        String roomName;
        boolean isSold;

        public Ticket(String ticketId, String roomName) {
            this.ticketId = ticketId;
            this.roomName = roomName;
            this.isSold = false;
        }
    }

    // ================= TICKET POOL =================
    static class TicketPool {

        String roomName;
        List<Ticket> tickets = new ArrayList<>();

        public TicketPool(String roomName, int totalTickets) {
            this.roomName = roomName;

            for (int i = 1; i <= totalTickets; i++) {
                String id = roomName + "-" + String.format("%03d", i);
                tickets.add(new Ticket(id, roomName));
            }
        }

        // synchronized để tránh 2 thread bán cùng 1 vé
        public synchronized Ticket sellTicket() {

            for (Ticket t : tickets) {
                if (!t.isSold) {
                    t.isSold = true;
                    return t;
                }
            }
            return null;
        }

        public synchronized int remainingTickets() {
            int count = 0;
            for (Ticket t : tickets) {
                if (!t.isSold) count++;
            }
            return count;
        }
    }

    // ================= BOOKING COUNTER =================
    static class BookingCounter implements Runnable {

        String counterName;
        TicketPool roomA;
        TicketPool roomB;
        int soldCount = 0;
        Random random = new Random();

        public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
            this.counterName = counterName;
            this.roomA = roomA;
            this.roomB = roomB;
        }

        @Override
        public void run() {

            while (true) {

                Ticket ticket = null;

                // chọn ngẫu nhiên phòng
                if (random.nextBoolean()) {
                    ticket = roomA.sellTicket();
                    if (ticket == null) {
                        ticket = roomB.sellTicket();
                    }
                } else {
                    ticket = roomB.sellTicket();
                    if (ticket == null) {
                        ticket = roomA.sellTicket();
                    }
                }

                if (ticket == null) {
                    break;
                }

                System.out.println(counterName + " đã bán vé " + ticket.ticketId);
                soldCount++;

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(counterName + " bán được: " + soldCount + " vé");
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) throws InterruptedException {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA, roomB);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Kết thúc chương trình");

        System.out.println("Vé còn lại phòng A: " + roomA.remainingTickets());
        System.out.println("Vé còn lại phòng B: " + roomB.remainingTickets());
    }
}