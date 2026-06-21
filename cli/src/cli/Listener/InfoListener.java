package cli.Listener;

import cli.EventSystem.ICargoEventListener;

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

