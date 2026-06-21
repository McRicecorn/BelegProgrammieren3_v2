package cli.EventSystem;
import cli.CargoEvent;

public interface ICargoEventListener {
    boolean onCargoEvent(CargoEvent e);
}
