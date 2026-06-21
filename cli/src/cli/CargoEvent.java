package cli;

import cli.Events.EventObject;
import cli.EventSystem.IPayload;

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

