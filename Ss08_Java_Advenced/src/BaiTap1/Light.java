package BaiTap1;

import BaiTap1.Device;

public class Light implements Device {

    @Override
    public void turnOn() {
        System.out.println("Đèn: Bật sáng");
    }

    @Override
    public void turnOff() {
        System.out.println("Đèn: Tắt");
    }
}