package cli.Listener;

import administration.Customer;
import cargo.Cargo;
import cli.CargoEvent;
import cli.EventSystem.IReadCargoEventListener;
import cli.Payloads.PayloadDryAndUnitisedCargo;
import cli.Payloads.PayloadDryBulkCargo;
import cli.Payloads.PayloadReadCargoByStorageLocation;
import cli.Payloads.PayloadUnitisedCargo;
import domainLogic.CargoManager;


import java.util.ArrayList;
import java.util.List;

public class ReadEventListener implements IReadCargoEventListener {
    private final CargoManager cargoManager;
    private Object source;

    public ReadEventListener(CargoManager cargoManager, Object source){
        this.cargoManager = cargoManager;
        this.source = source;
    }

    public List<? extends Cargo> handle(CargoEvent e) {
        if (e.getPayload() instanceof PayloadDryBulkCargo){
            return this.cargoManager.readDryBulkCargo();
        }

        if (e.getPayload() instanceof PayloadUnitisedCargo){
            return this.cargoManager.readUnitisedCargo();
        }

        if (e.getPayload() instanceof PayloadDryAndUnitisedCargo){
            return this.cargoManager.readDryBulkAndUnitisedCargo();
        }

        if (e.getPayload() instanceof PayloadReadCargoByStorageLocation){
            Cargo c = cargoManager.getCargoByStorageLocation(((PayloadReadCargoByStorageLocation) e.getPayload()).storageLocation());
            return c == null ? List.of() : List.of(c);
        }


        return null;


    }

    public List<? extends Customer> readCustomers(){
        return this.cargoManager.readCustomers();
    }


    public Object getSource() {
        return source;
    }

}
