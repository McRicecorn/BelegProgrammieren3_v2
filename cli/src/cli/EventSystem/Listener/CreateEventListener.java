package cli.EventSystem.Listener;

import cli.CargoEvent;
import cli.EventSystem.Interfaces.ICargoEventListener;
import cli.EventSystem.Payloads.PayloadCustomer;
import cli.EventSystem.Payloads.PayloadDryAndUnitisedCargo;
import cli.EventSystem.Payloads.PayloadDryBulkCargo;
import cli.EventSystem.Payloads.PayloadUnitisedCargo;
import domainLogic.CargoManager;
import domainLogic.ImplCustomer;

public class CreateEventListener implements ICargoEventListener {
    private final CargoManager cargoManager;

    public CreateEventListener(CargoManager cargoManager){
        this.cargoManager = cargoManager;
    }


    @Override
    public boolean onCargoEvent (CargoEvent e) {

        if (e.getPayload() instanceof PayloadDryBulkCargo){

            ImplCustomer customer = cargoManager.getCustomerByName(((PayloadDryBulkCargo) e.getPayload()).owner().getName());

            return this.cargoManager.createDryBulkCargo(
                    customer,
                    ((PayloadDryBulkCargo) e.getPayload()).durationOfStorage(),
                    ((PayloadDryBulkCargo) e.getPayload()).storageLocation(),
                    ((PayloadDryBulkCargo) e.getPayload()).value(),
                    ((PayloadDryBulkCargo) e.getPayload()).hazards(),
                    ((PayloadDryBulkCargo) e.getPayload()).grainSize());


        }

        if (e.getPayload() instanceof PayloadUnitisedCargo){

            ImplCustomer customer = cargoManager.getCustomerByName(((PayloadUnitisedCargo) e.getPayload()).customer().getName());

            return this.cargoManager.createUnitisedCargo(
                    ((PayloadUnitisedCargo) e.getPayload()).isFragile(),
                    ((PayloadUnitisedCargo) e.getPayload()).value(),
                    ((PayloadUnitisedCargo) e.getPayload()).hazards(),
                    customer,
                    ((PayloadUnitisedCargo) e.getPayload()).durationOfStorage(),
                    ((PayloadUnitisedCargo) e.getPayload()).storageLocation());


        }

        if (e.getPayload() instanceof PayloadDryAndUnitisedCargo){

            ImplCustomer customer = cargoManager.getCustomerByName(((PayloadDryAndUnitisedCargo) e.getPayload()).owner().getName());

            return this.cargoManager.createDryBulkCargoAndUnitisedCargo(
                    ((PayloadDryAndUnitisedCargo) e.getPayload()).grainSize(),
                    ((PayloadDryAndUnitisedCargo) e.getPayload()).value(),
                    ((PayloadDryAndUnitisedCargo) e.getPayload()).hazards(),
                    customer,
                    ((PayloadDryAndUnitisedCargo) e.getPayload()).durationOfStorage(),
                    ((PayloadDryAndUnitisedCargo) e.getPayload()).storageLocation(),
                    ((PayloadDryAndUnitisedCargo) e.getPayload()).isFragile()
            );

        }


        if (e.getPayload() instanceof PayloadCustomer){
            return cargoManager.registerCustomer( ((PayloadCustomer) e.getPayload()).name());
        }

        return false;
    }


}
