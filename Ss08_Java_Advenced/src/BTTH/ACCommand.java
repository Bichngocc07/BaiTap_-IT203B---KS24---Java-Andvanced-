package BTTH;

public class ACCommand implements Command {

    private AC ac;
    private int newTemp;
    private int oldTemp;

    public ACCommand(AC ac, int newTemp){
        this.ac = ac;
        this.newTemp = newTemp;
    }

    @Override
    public void execute() {
        oldTemp = ac.getTemp();   // lưu nhiệt độ cũ
        ac.setTemp(newTemp);      // set nhiệt độ mới
        System.out.println("Điều hòa: Nhiệt độ = " + newTemp);
    }

    @Override
    public void undo() {
        ac.setTemp(oldTemp);
        System.out.println("Undo: Điều hòa: Nhiệt độ = " + oldTemp);
    }
}