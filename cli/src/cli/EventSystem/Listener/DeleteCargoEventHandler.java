package cli.EventSystem.Listener;

import cargo.Cargo;
import cli.EventSystem.Interfaces.ICargoEventListener;
import cli.EventSystem.Payloads.PayloadDeleteCargo;
import domainLogic.CargoManager;

public class DeleteCargoEventHandler implements ICargoEventListener {

    private final CargoManager cargoManager;

    public DeleteCargoEventHandler(CargoManager cargoManager){
        this.cargoManager = cargoManager;
    }

    @Override
    public boolean onCargoEvent(cli.CargoEvent e) {

        if (!(e.getPayload() instanceof PayloadDeleteCargo)){
            return false;
        }

        Cargo cargo = cargoManager.getCargoByStorageLocation(((PayloadDeleteCargo) e.getPayload()).StoragelocationId());
        if (cargo == null){
            return false;
        }

        if (cargo instanceof domainLogic.ImplDryBulkCargo dry){
            return cargoManager.deleteDryBulkCargo(dry);
        }

        if (cargo instanceof domainLogic.ImplUnitisedCargo uni){
            return cargoManager.deleteUnitisedCargo(uni);
        }

        if (cargo instanceof domainLogic.ImplDryBulkAndUnitisedCargo both){
            return cargoManager.deleteDryBulkAndUnitisedCargo(both);
        }

        return false;
    }




}
