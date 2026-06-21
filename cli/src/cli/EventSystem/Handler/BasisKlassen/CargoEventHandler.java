package cli.EventSystem.Handler.BasisKlassen;

import cli.CargoEvent;
import cli.EventSystem.Interfaces.ICargoEventListener;

import java.util.ArrayList;
import java.util.List;

public class CargoEventHandler {
    private List<ICargoEventListener> listenerList = new ArrayList<>();
    public void addListener(ICargoEventListener listener){
        listenerList.add(listener);
    }
    public void removeListener(ICargoEventListener listener){
        listenerList.remove(listener);
    }
    public boolean handle(CargoEvent e) {
        boolean handled = false;

        for (ICargoEventListener listener : listenerList) {
            boolean result = listener.onCargoEvent(e);
            if (result) {
                handled = true;
            }
        }

        return handled;
    }





}
