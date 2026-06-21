package cli.EventSystem;

import cargo.Cargo;
import cli.CargoEvent;

import java.util.List;

public interface IReadCargoEventListener {
    List<? extends Cargo> handle(CargoEvent e);
}
