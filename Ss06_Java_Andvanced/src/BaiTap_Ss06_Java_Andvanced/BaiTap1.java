package BaiTap_Ss06_Java_Andvanced;

import java.util.LinkedList;
import java.util.Queue;

public class BaiTap1 {

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

        public TicketPool(String roomName, int number) {
            this.roomName = roomName;

            for (int i = 1; i <= number; i++) {
                tickets.add(new Ticket(roomName + "-" + String.format("%03d", i)));
            }
        }

        public Ticket getTicket() {
            return tickets.poll();
        }

        public void returnTicket(Ticket ticket) {
            tickets.add(ticket);
        }

        public boolean hasTicket() {
            return !tickets.isEmpty();
        }

        public String getRoomName() {
            return roomName;
        }
    }

    static class BookingCounter extends Thread {

        private String counterName;
        private TicketPool roomA;
        private TicketPool roomB;
        private boolean lockAFirst;

        public BookingCounter(String name, TicketPool roomA, TicketPool roomB, boolean lockAFirst) {
            this.counterName = name;
            this.roomA = roomA;
            this.roomB = roomB;
            this.lockAFirst = lockAFirst;
        }

        public void sellCombo() {

            if (lockAFirst) {

                synchronized (roomA) {

                    Ticket ticketA = roomA.getTicket();
                    if (ticketA == null) {
                        System.out.println(counterName + ": Hết vé phòng A");
                        return;
                    }

                    System.out.println(counterName + ": Đã lấy vé " + ticketA.getId());

                    try { Thread.sleep(100); } catch (Exception e) {}

                    synchronized (roomB) {

                        Ticket ticketB = roomB.getTicket();

                        if (ticketB == null) {
                            System.out.println(counterName + ": Hết vé phòng B -> trả lại vé A");
                            roomA.returnTicket(ticketA);
                            return;
                        }

                        System.out.println(counterName + " bán combo: "
                                + ticketA.getId() + " & " + ticketB.getId());
                    }
                }

            } else {

                synchronized (roomB) {

                    Ticket ticketB = roomB.getTicket();
                    if (ticketB == null) {
                        System.out.println(counterName + ": Hết vé phòng B");
                        return;
                    }

                    System.out.println(counterName + ": Đã lấy vé " + ticketB.getId());

                    try { Thread.sleep(100); } catch (Exception e) {}

                    synchronized (roomA) {

                        Ticket ticketA = roomA.getTicket();

                        if (ticketA == null) {
                            System.out.println(counterName + ": Hết vé phòng A -> trả lại vé B");
                            roomB.returnTicket(ticketB);
                            return;
                        }

                        System.out.println(counterName + " bán combo: "
                                + ticketA.getId() + " & " + ticketB.getId());
                    }
                }
            }
        }

        @Override
        public void run() {

            while (true) {

                sellCombo();

                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
        }
    }

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 3);
        TicketPool roomB = new TicketPool("B", 3);

        BookingCounter counter1 =
                new BookingCounter("Quầy 1", roomA, roomB, true);

        BookingCounter counter2 =
                new BookingCounter("Quầy 2", roomA, roomB, false);

        counter1.start();
        counter2.start();
    }
}