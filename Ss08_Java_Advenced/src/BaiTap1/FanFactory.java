package BaiTap1;

import BTTH.Fan;

public class FanFactory extends DeviceFactory {

    @Override
    public Device createDevice() {
        System.out.println("FanFactory: Đã tạo quạt mới.");
        return new Fann();
    }
}