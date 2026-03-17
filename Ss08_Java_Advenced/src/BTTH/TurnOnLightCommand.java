package BTTH;

public class TurnOnLightCommand implements Command{
    Light light;
    public TurnOnLightCommand(Light light){
        this.light = light;
    }
    @Override
    public  void execute(){
        light.on();
    }
    @Override
    public void undo(){
        light.off();
    }
}
