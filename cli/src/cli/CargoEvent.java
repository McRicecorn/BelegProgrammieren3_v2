package cli;

import cli.EventSystem.Events.EventObject;
import cli.EventSystem.Interfaces.IPayload;

//CargoEvent?
public class CargoEvent extends EventObject {
    private IPayload payload;
    public CargoEvent(Object source, IPayload payload) {
        super(source);
        this.payload = payload;
    }


    public IPayload getPayload() {
        return payload;
    }



}

