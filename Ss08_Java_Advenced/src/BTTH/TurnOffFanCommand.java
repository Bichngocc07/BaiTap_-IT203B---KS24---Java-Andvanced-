package BTTH;

public class TurnOffFanCommand implements Command {

    private Fan fan;

    public TurnOffFanCommand(Fan fan){
        this.fan = fan;
    }

    @Override
    public void execute(){
        fan.off();
    }

    @Override
    public void undo(){
        fan.on();
    }
}