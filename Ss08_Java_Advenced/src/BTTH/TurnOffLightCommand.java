package BTTH;

public class TurnOffLightCommand implements Command{
    Light light;
    public TurnOffLightCommand(Light light){
        this.light = light;
    }
    @Override
    public  void execute(){
        light.off();
    }
    @Override
    public void undo(){
        light.on();
    }
}
