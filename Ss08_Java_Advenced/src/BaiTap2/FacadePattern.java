package BaiTap2;

public class FacadePattern {

    private Light light;
    private Fan fan;
    private AirConditioner ac;
    private TemperatureSensor sensor;

    public FacadePattern(Light light, Fan fan, AirConditioner ac, TemperatureSensor sensor) {
    }

    public void SmartHomeFacade(Light light, Fan fan, AirConditioner ac, TemperatureSensor sensor){
        this.light = light;
        this.fan = fan;
        this.ac = ac;
        this.sensor = sensor;
    }

    // rời nhà
    public void leaveHome(){
        light.off();
        fan.off();
        ac.off();
    }

    // ngủ
    public void sleepMode(){
        light.off();
        ac.setTemperature(28);
        fan.lowSpeed();
    }

    // lấy nhiệt độ
    public void getCurrentTemperature(){
        double temp = sensor.getTemperatureCelsius();
        System.out.println("Nhiệt độ hiện tại: " + String.format("%.1f", temp) + "°C");
    }
}