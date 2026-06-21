package cli.EventSystem.Interfaces;
import cli.CargoEvent;

public interface ICargoEventListener {
    boolean onCargoEvent(CargoEvent e);
}
