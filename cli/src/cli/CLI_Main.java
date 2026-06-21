package cli;

import cli.EventSystem.Handler.BasisKlassen.CargoEventHandler;
import cli.EventSystem.Handler.ReadCustomerEventHandler;
import cli.EventSystem.Handler.ReadEventHandler;
import cli.EventSystem.Listener.*;
import domainLogic.CargoManager;

public class CLI_Main {
    public static void main(String[] args) {
        CargoManager cargoManager = new CargoManager();
        Controller cli = new Controller();


        CargoEventHandler cargoEventHandler = new CargoEventHandler();
        ReadEventHandler readEventHandler = new ReadEventHandler();
        ReadCustomerEventHandler readCustomerEventHandler = new ReadCustomerEventHandler();


        CreateEventListener createEventListener = new CreateEventListener(cargoManager);
        cargoEventHandler.addListener(createEventListener);


        ReadEventListener readEventListener = new ReadEventListener(cargoManager, cli);
        readEventHandler.addListener(readEventListener);


        ReadCustomerEventListener readCustomerEventListener = new ReadCustomerEventListener(cargoManager);
        readCustomerEventHandler.addListener(readCustomerEventListener);

        UpdateCargoEventListener updateCargoEventListener = new UpdateCargoEventListener(cargoManager);
        cargoEventHandler.addListener(updateCargoEventListener);

            DeleteCargoEventHandler deleteCargoEventHandler = new DeleteCargoEventHandler(cargoManager);
            cargoEventHandler.addListener(deleteCargoEventHandler);


        cli.setCreateHandler(cargoEventHandler, readEventHandler, readCustomerEventHandler);
        cli.start();
    }
}
