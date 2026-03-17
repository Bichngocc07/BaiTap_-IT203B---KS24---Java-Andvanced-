package BTTH;

import BTTH.Command;
import BTTH.Fan;

public class TurnOnFanCommand implements Command {
    private Fan fan;

    public TurnOnFanCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }

    @Override
    public void undo() {
        fan.off();
    }
}