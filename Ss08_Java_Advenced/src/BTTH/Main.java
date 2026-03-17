import BTTH.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Light light = new Light();
        Fan fan = new Fan();
        AC ac = new AC();

        RemoteControl remote = new RemoteControl();

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\n===== REMOTE CONTROL =====");
            System.out.println("1. Gán nút");
            System.out.println("2. Nhấn nút");
            System.out.println("3. Undo");
            System.out.println("0. Thoát");

            int choice = sc.nextInt();

            switch(choice){

                case 1:
                    System.out.print("Nhập số nút: ");
                    int button = sc.nextInt();

                    System.out.println("1.Light ON");
                    System.out.println("2.Light OFF");
                    System.out.println("3.Fan ON");
                    System.out.println("4.Fan OFF");
                    System.out.println("5.AC Set Temp");

                    int cmd = sc.nextInt();

                    Command command = null;

                    switch(cmd){

                        case 1:
                            command = new TurnOnLightCommand(light);
                            break;

                        case 2:
                            command = new TurnOffLightCommand(light);
                            break;

                        case 3:
                            command = new TurnOnFanCommand(fan);
                            break;

                        case 4:
                            command = new TurnOffFanCommand(fan);
                            break;

                        case 5:
                            System.out.print("Nhập nhiệt độ: ");
                            int temp = sc.nextInt();
                            command = new ACCommand(ac,temp);
                            break;
                    }

                    remote.setCommand(button,command);
                    break;

                case 2:
                    System.out.print("Nhập nút muốn bấm: ");
                    int press = sc.nextInt();
                    remote.pressButton(press);
                    break;

                case 3:
                    remote.undo();
                    break;

                case 0:
                    return;
            }
        }
    }
}