package cli.EventSystem.Handler.BasisKlassen;

import cargo.Cargo;
import cli.CargoEvent;
import cli.EventSystem.Interfaces.ICargoEventListener;
import cli.EventSystem.Interfaces.IReadCargoEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadCargoEventHandler  {

    private List<IReadCargoEventListener> listenerList = new ArrayList<>();
    public void addListener(IReadCargoEventListener listener){
        listenerList.add(listener);
    }
    public void removeListener(ICargoEventListener listener){
        listenerList.remove(listener);
    }
    public List<? extends Cargo> handle(CargoEvent e) {
        for (IReadCargoEventListener listener : listenerList) {

            IReadCargoEventListener readListener = listener;
            List<? extends Cargo> result = readListener.handle(e);
            if (result != null) {
                return result;
            }

        }

        return new ArrayList<>();


    }



}
