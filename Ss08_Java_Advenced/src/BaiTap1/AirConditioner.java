package BaiTap1;

public class AirConditioner implements Device {

    @Override
    public void turnOn() {
        System.out.println("Điều hòa: Bật");
    }

    @Override
    public void turnOff() {
        System.out.println("Điều hòa: Tắt");
    }
}