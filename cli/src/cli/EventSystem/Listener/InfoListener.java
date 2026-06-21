package cli.EventSystem.Listener;

import cli.EventSystem.Interfaces.ICargoEventListener;

public class InfoListener implements ICargoEventListener {
/*
    @Override
    public void onCargoEvent(cli.CargoEvent e) {
        System.out.println("InfoListener: CargoEvent received: " + e.getSource());
    }
 */

    @Override
    public boolean onCargoEvent(cli.CargoEvent e) {
        System.out.println("InfoListener: CargoEvent received: " + e.getSource());
        return true;
    }
}

