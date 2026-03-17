package BaiTap2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Adapter
        OldThermometer oldThermometer = new OldThermometer();
        TemperatureSensor sensor = new ThermometerAdapter(oldThermometer);

        // Devices
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        // Facade
        FacadePattern facade = new FacadePattern(light, fan, ac, sensor);

        while(true){

            System.out.println("\n===== SMART HOME =====");
            System.out.println("1. Xem nhiệt độ");
            System.out.println("2. Rời nhà");
            System.out.println("3. Chế độ ngủ");
            System.out.println("0. Thoát");

            int choice = sc.nextInt();

            switch(choice){

                case 1:
                    facade.getCurrentTemperature();
                    break;

                case 2:
                    facade.leaveHome();
                    break;

                case 3:
                    facade.sleepMode();
                    break;

                case 0:
                    return;
            }
        }
    }
}