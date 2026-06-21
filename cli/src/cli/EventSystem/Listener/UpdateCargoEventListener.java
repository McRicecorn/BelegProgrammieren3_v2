package cli.EventSystem.Listener;

import cargo.Cargo;
import cli.CargoEvent;
import cli.EventSystem.Interfaces.ICargoEventListener;
import cli.EventSystem.Payloads.PayloadUpdate;
import domainLogic.CargoManager;
import domainLogic.ImplDryBulkAndUnitisedCargo;
import domainLogic.ImplDryBulkCargo;
import domainLogic.ImplUnitisedCargo;

import java.util.Date;

public class UpdateCargoEventListener implements ICargoEventListener {

    private final CargoManager cargoManager;

    public UpdateCargoEventListener(CargoManager cargoManager) {
        this.cargoManager = cargoManager;
    }

    @Override
    public boolean onCargoEvent(CargoEvent e) {

        if (!(e.getPayload() instanceof PayloadUpdate p)) {
            return false; // nicht zuständig
        }

        Cargo cargo = cargoManager.getCargoByStorageLocation(p.StoragelocationId());
        if (cargo == null) {
            return false; // kein Cargo gefunden
        }

        Date newDate = p.inspectionDate();

        if (cargo instanceof ImplDryBulkCargo dry) {

            return cargoManager.updateDryBulkCargo(dry, newDate);
        }

        if (cargo instanceof ImplUnitisedCargo uni) {
            return cargoManager.updateUnitisedCargo(uni, newDate);
        }

        if (cargo instanceof ImplDryBulkAndUnitisedCargo both) {
            return cargoManager.updateDryBulkAndUnitisedCargo(both, newDate);
        }

        return false;
    }

}
