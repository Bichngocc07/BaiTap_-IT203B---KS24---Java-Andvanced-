package BaiTap1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Device> devices = new ArrayList<>();

        HardwareConnection connection = HardwareConnection.getInstance();

        while(true){

            System.out.println("\n===== SMART HOME =====");
            System.out.println("1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Tắt thiết bị");
            System.out.println("0. Thoát");

            int choice = sc.nextInt();

            switch(choice){

                case 1:
                    connection.connect();
                    break;

                case 2:
                    System.out.println("1. Light");
                    System.out.println("2. Fan");
                    System.out.println("3. AirConditioner");

                    int type = sc.nextInt();

                    DeviceFactory factory = null;

                    switch(type){
                        case 1: factory = new LightFactory(); break;
                        case 2: factory = new FanFactory(); break;
                        case 3: factory = new AirConditionerFactory(); break;
                    }

                    if(factory != null){
                        Device device = factory.createDevice();
                        devices.add(device);
                    }
                    break;

                case 3:
                    if(devices.isEmpty()){
                        System.out.println("Chưa có thiết bị");
                        break;
                    }

                    System.out.print("Chọn thiết bị: ");
                    int onIndex = sc.nextInt();

                    devices.get(onIndex - 1).turnOn();
                    break;

                case 4:
                    if(devices.isEmpty()){
                        System.out.println("Chưa có thiết bị");
                        break;
                    }

                    System.out.print("Chọn thiết bị: ");
                    int offIndex = sc.nextInt();

                    devices.get(offIndex - 1).turnOff();
                    break;

                case 0:
                    return;
            }
        }
    }
}