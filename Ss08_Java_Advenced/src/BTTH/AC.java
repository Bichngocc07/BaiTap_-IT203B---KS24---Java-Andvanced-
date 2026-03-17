package BTTH;

public class AC {
    private int temp = 25;
    private int newTemp;
    private int oldTemp;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getNewTemp() {
        return newTemp;
    }

    public void setNewTemp(int newTemp) {
        this.newTemp = newTemp;
    }

    public int getOldTemp() {
        return oldTemp;
    }

    public void setOldTemp(int oldTemp) {
        this.oldTemp = oldTemp;
    }
    public void  changeTemp(int newTemp){
        // Lưu lại nhiệt độ cũ
        this.oldTemp = this.newTemp;
        // Lưu lại nhiệt độ hiện tại
        temp = newTemp;
        // Lưu lại nhiệt độ mới
        this.newTemp = newTemp;
    }
}
