package BaiTap1;

public class Fann implements Device {

    @Override
    public void turnOn() {
        System.out.println("Quạt: Bật");
    }

    @Override
    public void turnOff() {
        System.out.println("Quạt: Tắt");
    }
}