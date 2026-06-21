package cli.EventSystem.Listener;

import administration.Customer;
import cli.EventSystem.Events.CustomerEvent;
import cli.EventSystem.Interfaces.IReadCustomerEventListener;
import domainLogic.CargoManager;

import java.util.List;

public class ReadCustomerEventListener implements IReadCustomerEventListener {
    private final CargoManager cargoManager;

    public ReadCustomerEventListener(CargoManager cargoManager){
        this.cargoManager = cargoManager;
    }

    @Override
    public List<? extends Customer> handle(CustomerEvent e) {
        return this.cargoManager.readCustomers();
    }



}
