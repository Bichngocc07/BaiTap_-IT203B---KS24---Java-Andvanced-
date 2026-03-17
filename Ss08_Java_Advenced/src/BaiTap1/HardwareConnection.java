package BaiTap1;

public class HardwareConnection {

    private static HardwareConnection instance;
    private boolean isConnected = false;

    // private constructor
    private HardwareConnection(){}

    public static HardwareConnection getInstance(){
        if(instance == null){
            instance = new HardwareConnection();
        }
        return instance;
    }

    public void connect(){
        if(!isConnected){
            System.out.println("HardwareConnection: Đã kết nối phần cứng.");
            isConnected = true;
        }
    }

    public void disconnect(){
        if(isConnected){
            System.out.println("HardwareConnection: Đã ngắt kết nối.");
            isConnected = false;
        }
    }
}