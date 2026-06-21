package cli;

import cli.Handler.BasisKlassen.CargoEventHandler;
import cli.Handler.ReadCustomerEventHandler;
import cli.Handler.ReadEventHandler;
import cli.Listener.CreateEventListener;
import cli.Listener.ReadCustomerEventListener;
import cli.Listener.ReadEventListener;
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



        cli.setCreateHandler(cargoEventHandler, readEventHandler, readCustomerEventHandler);
        cli.start();
    }
}
