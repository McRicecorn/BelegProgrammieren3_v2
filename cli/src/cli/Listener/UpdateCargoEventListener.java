package cli.Listener;

import cargo.Cargo;
import cli.CargoEvent;
import cli.EventSystem.ICargoEventListener;
import cli.Payloads.PayloadDryBulkCargo;
import cli.Payloads.PayloadUpdate;
import domainLogic.CargoManager;
import domainLogic.ImplDryBulkAndUnitisedCargo;
import domainLogic.ImplDryBulkCargo;
import domainLogic.ImplUnitisedCargo;

public class UpdateCargoEventListener implements ICargoEventListener {

    private final CargoManager cargoManager;

    public UpdateCargoEventListener(CargoManager cargoManager) {
        this.cargoManager = cargoManager;
    }

    @Override
    public boolean onCargoEvent(CargoEvent e) {
       if (e.getPayload() instanceof PayloadUpdate) {
           Cargo cargoDry = cargoManager.getCargoByStorageLocation(((PayloadUpdate) e.getPayload()).StoragelocationId());
           if (cargoDry instanceof ImplDryBulkCargo) {
               return this.cargoManager.updateDryBulkCargo(
                       (ImplDryBulkCargo) cargoDry,
                       ((PayloadUpdate) e.getPayload()).inspectionDate()
               );
           }
       }
       if (e.getPayload() instanceof PayloadUpdate){
           Cargo cargoUni = cargoManager.getCargoByStorageLocation(((PayloadUpdate) e.getPayload()).StoragelocationId());
           if(cargoUni instanceof ImplUnitisedCargo){
               return this.cargoManager.updateUnitisedCargo(
                       (ImplUnitisedCargo) cargoUni,
                       ((PayloadUpdate) e.getPayload()).inspectionDate()
               );
           }
       }
       if (e.getPayload() instanceof PayloadUpdate){
               Cargo cargo = cargoManager.getCargoByStorageLocation(((PayloadUpdate) e.getPayload()).StoragelocationId());
               if(cargo instanceof ImplDryBulkAndUnitisedCargo){
                   return this.cargoManager.updateDryBulkAndUnitisedCargo(
                           (ImplDryBulkAndUnitisedCargo) cargo,
                           ((PayloadUpdate) e.getPayload()).inspectionDate()
                   );
               }

       }


        return false;
    }
}
