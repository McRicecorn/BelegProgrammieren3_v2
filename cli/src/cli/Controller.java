package cli;

import administration.Customer;
import cargo.Cargo;
import cargo.Hazard;
import cli.Events.CustomerEvent;
import cli.Handler.BasisKlassen.CargoEventHandler;
import cli.Handler.BasisKlassen.ReadCargoEventHandler;
import cli.Handler.ReadCustomerEventHandler;
import cli.Payloads.*;
import domainLogic.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class Controller {


    public Controller() {
    }

    private CargoEventHandler createHandler;
    private ReadCargoEventHandler readCargoEventHandler;
    private ReadCustomerEventHandler readCustomerEventHandler;
    public void setCreateHandler(CargoEventHandler createHandler, ReadCargoEventHandler readCargoEventHandler, ReadCustomerEventHandler readCustomerEventHandler) {
        this.createHandler = createHandler;
        this.readCargoEventHandler = readCargoEventHandler;
        this.readCustomerEventHandler = readCustomerEventHandler;
    }


    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Cargo Management System CLI!"
                    + "\nPlease select an option:"
                    + "\n1. Create Cargo"
                    + "\n2. Update Cargo inspection date"
                    + "\n3. Delete Cargo"
                    + "\n4. View Cargos"
                    + "\n5. View Customers"
                    + "\n6. Add Customer"
                    + "\n7. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:

                    System.out.println(
                            "What type of cargo do you want to create?" +
                                    "\n1. Dry Bulk Cargo" +
                                    "\n2. Unitised Cargo" +
                                    "\n3. Dry Bulk and Unitised Cargo");
                    int cargoType = scanner.nextInt();

                    switch (cargoType) {
                        case 1:
                            System.out.println("Creating Dry Bulk Cargo." +
                                    "\nPlease enter the following details:");

                            scanner.nextLine();
                            System.out.println("Enter Customer name: ");

                            String name = scanner.nextLine();
                            System.out.println("The chosen name is: " + name);
                            ImplCustomer customer = new ImplCustomer(name);

                            System.out.println("Enter duration of storage (in days): ");
                            int duration = scanner.nextInt();
                            System.out.println("The chosen duration is: " + duration + " days");
                            Duration durationOfStorage = Duration.ofDays(duration);

                            System.out.println("Enter storage location (integer): ");
                            int storageLocation = scanner.nextInt();
                            System.out.println("The chosen storage location is: " + storageLocation);

                            System.out.println("Enter value (decimal): ");
                            double value = scanner.nextDouble();
                            System.out.println("The chosen value is: " + value);
                            BigDecimal value1 = new BigDecimal(value);

                            scanner.nextLine();
                            System.out.println("Enter the hazards: (example: 1,3,5 "
                                    + "\n1. Flammable" +
                                    "\n2. Toxic" +
                                    "\n3. Explosive" +
                                    "\n4. Radioactive)");
                            String hazardInput = scanner.nextLine();
                            String[] hazardArray = hazardInput.split(",");
                            Collection<Hazard> hazards = new ArrayList<>();

                            for (String p : hazardArray) {
                                switch (p.trim()) {
                                    case "1" -> hazards.add(Hazard.FLAMMABLE);
                                    case "2" -> hazards.add(Hazard.TOXIC);
                                    case "3" -> hazards.add(Hazard.EXPLOSIVE);
                                    case "4" -> hazards.add(Hazard.RADIOACTIVE);
                                    default -> System.out.println("Unknown hazard");
                                }
                            }
                            System.out.println("The chosen hazards are: " + hazards);

                            System.out.println("Enter grain size (integer): ");
                            int grainSize = scanner.nextInt();
                            System.out.println("The chosen grain size is: " + grainSize);

                            PayloadDryBulkCargo payload = new PayloadDryBulkCargo(grainSize, value1, hazards, customer, durationOfStorage, storageLocation);
                            CargoEvent event = new CargoEvent(this,payload);

                            boolean handled = false;
                            if(null!= createHandler){
                                boolean handler= createHandler.handle(event);
                                if(handler){
                                    handled = true;
                                }
                            }
                            if (handled) {
                                System.out.println("Dry Bulk Cargo created successfully!");

                            }else{
                                System.out.println("Failed to create Dry Bulk Cargo.");
                            }




                            break;
                        case 2:
                            System.out.println("Creating Unitised Cargo.");
                            scanner.nextLine(); // Buffer leeren

                            System.out.print("Enter Customer name: ");
                            String nameUni = scanner.nextLine();
                            ImplCustomer customerUni = new ImplCustomer(nameUni);

                            System.out.print("Enter duration of storage (in days): ");
                            int durationUni = scanner.nextInt();
                            Duration durationOfStorageUni = Duration.ofDays(durationUni);

                            System.out.print("Enter storage location (integer): ");
                            int storageLocationUni = scanner.nextInt();

                            System.out.print("Enter value (decimal): ");
                            double valueUni = scanner.nextDouble();
                            BigDecimal valueUnitised = BigDecimal.valueOf(valueUni);

                            scanner.nextLine(); // Buffer leeren

                            System.out.println(
                            """
                            Enter the hazards (example: 1,3,5)
                            1. Flammable
                            2. Toxic
                            3. Explosive
                            4. Radioactive
                            """);

                            String hazardUni = scanner.nextLine();
                            String[] hazardUniArray = hazardUni.split(",");
                            Collection<Hazard> hazardsUni = new ArrayList<>();

                            for (String p : hazardUniArray) {
                                switch (p.trim()) {
                                    case "1" -> hazardsUni.add(Hazard.FLAMMABLE);
                                    case "2" -> hazardsUni.add(Hazard.TOXIC);
                                    case "3" -> hazardsUni.add(Hazard.EXPLOSIVE);
                                    case "4" -> hazardsUni.add(Hazard.RADIOACTIVE);
                                    default -> System.out.println("Unknown hazard");
                                }
                            }

                            System.out.print("Is the cargo fragile? (true/false): ");
                            boolean isFragile = scanner.nextBoolean();

                            PayloadUnitisedCargo payloadUnitisedCargo = new PayloadUnitisedCargo(isFragile, valueUnitised, hazardsUni, customerUni, durationOfStorageUni, storageLocationUni);
                            CargoEvent eventUnitisedCargo = new CargoEvent(this,payloadUnitisedCargo);

                                boolean handledUnitisedCargo = false;
                                if(null!= createHandler){
                                    boolean handler= createHandler.handle(eventUnitisedCargo);
                                    if(handler){
                                        handledUnitisedCargo = true;
                                    }
                                }
                                if (handledUnitisedCargo) {
                                    System.out.println("Unitised Cargo created successfully!");
                                }else {
                                    System.out.println("Failed to create Unitised Cargo.");
                                }



                            break;

                        case 3:
                            System.out.println("Creating Dry Bulk and Unitised Cargo.");
                            scanner.nextLine(); // Buffer leeren

                            System.out.print("Enter Customer name: ");
                            String nameDryAndUni = scanner.nextLine();
                            ImplCustomer customerDryAndUni = new ImplCustomer(nameDryAndUni);

                            System.out.print("Enter duration of storage (in days): ");
                            int durationDryAndUni = scanner.nextInt();
                            Duration durationOfStorageDryAndUni = Duration.ofDays(durationDryAndUni);

                            System.out.print("Enter storage location (integer): ");
                            int storageLocationDryAndUni = scanner.nextInt();

                            System.out.print("Enter value (decimal): ");
                            double valueDryAndUniTemp = scanner.nextDouble();
                            BigDecimal valueDryAndUni = BigDecimal.valueOf(valueDryAndUniTemp);

                            System.out.print("Enter grain size (integer): ");
                            int grainSizeDryAndUni = scanner.nextInt();

                            scanner.nextLine(); // Buffer leeren

                            System.out.println(
                            """
                            Enter the hazards (example: 1,3,5)
                            1. Flammable
                            2. Toxic
                            3. Explosive
                            4. Radioactive
                            """);

                            String hazardDryAndUni = scanner.nextLine();
                            String[] hazardDryAndUniArray = hazardDryAndUni.split(",");
                            Collection<Hazard> hazardsDryAndUni = new ArrayList<>();

                            for (String p : hazardDryAndUniArray) {
                                switch (p.trim()) {
                                    case "1" -> hazardsDryAndUni.add(Hazard.FLAMMABLE);
                                    case "2" -> hazardsDryAndUni.add(Hazard.TOXIC);
                                    case "3" -> hazardsDryAndUni.add(Hazard.EXPLOSIVE);
                                    case "4" -> hazardsDryAndUni.add(Hazard.RADIOACTIVE);
                                    default -> System.out.println("Unknown hazard");
                                }
                            }

                            System.out.print("Is the cargo fragile? (true/false): ");
                            boolean isFragileDryAndUni = scanner.nextBoolean();

                            PayloadDryAndUnitisedCargo payloadDryAndUnitisedCargo = new PayloadDryAndUnitisedCargo(grainSizeDryAndUni, valueDryAndUni, hazardsDryAndUni, customerDryAndUni, durationOfStorageDryAndUni, storageLocationDryAndUni, isFragileDryAndUni);

                            CargoEvent eventDryAndUnitisedCargo = new CargoEvent(this,payloadDryAndUnitisedCargo);

                                boolean handledDryAndUnitisedCargo = false;
                                if(null!= createHandler){
                                    boolean handler= createHandler.handle(eventDryAndUnitisedCargo);
                                    if(handler){
                                        handledDryAndUnitisedCargo = true;
                                    }
                                }
                                if (handledDryAndUnitisedCargo) {
                                    System.out.println("Dry Bulk and Unitised Cargo created successfully!");
                                }else {
                                    System.out.println("Failed to create Dry Bulk and Unitised Cargo.");
                                }

                            break;

                        default:
                            System.out.println("Invalid cargo type. Please try again.");
                            break;
                    }

                    break;
                case 2:
                    // Call updateCargo method here
                    System.out.println("Updating Cargo inspection date." +
                            "\nWhich Cargo do you want to update? (Enter Storage Location): ") ;
                    int storageLocation = scanner.nextInt();
                    scanner.nextLine();


                    System.out.println("Enter new inspection date (YYYY-MM-DD): ");
                    String newInspectionDateStr = scanner.nextLine();
                    Date newInspectionDate = java.sql.Date.valueOf(newInspectionDateStr);

                    PayloadUpdate payloadUpdate = new PayloadUpdate(storageLocation, newInspectionDate);
                    CargoEvent eventUpdate = new CargoEvent(this, payloadUpdate);
                    boolean handledUpdate = false;
                    if(null!= createHandler){
                        boolean handler= createHandler.handle(eventUpdate);
                        if(handler){
                            handledUpdate = true;
                        }
                    }

                        if (handledUpdate) {
                                        System.out.println("Cargo inspection date updated successfully!");

                                    }else{
                                        System.out.println("Failed to update Cargo inspection date.");
                                    }








                    boolean updated = false;



                    break;

                case 3:

                    // Call deleteCargo method here
                    System.out.println("Deleting Cargo.");
                    System.out.println("Which Cargo do you want to delete? (Enter Storage Location): ");

                    int deleteLocation = scanner.nextInt();
                    scanner.nextLine(); // Buffer leeren




                break;
                case 4:
                    //View Cargos

                    System.out.println("Which Cargo type do you want to view?" +
                            "\n1. Dry Bulk Cargo" +
                            "\n2. Unitised Cargo" +
                            "\n3. Dry Bulk and Unitised Cargo"
                    //        "\n4. All Cargos"
                    );
                    int viewCargoType = scanner.nextInt();

                    switch (viewCargoType) {

                        case 1:
                            System.out.println("List of Dry Bulk Cargos:");
                            // Call method to get and display Dry Bulk Cargos
                            PayloadDryBulkCargo payloadDryBulkCargo = new PayloadDryBulkCargo(0, null, null, null, null, 0);
                            CargoEvent eventViewDryBulkCargo = new CargoEvent(this, payloadDryBulkCargo);

                            List<? extends Cargo> temp = readCargoEventHandler.handle(eventViewDryBulkCargo);
                            List<Cargo> dryBulk = new ArrayList<>(temp);
                            for (Cargo cargo : dryBulk) {
                                if (cargo instanceof ImplDryBulkCargo) {
                                    System.out.println(
                                            "Customer: " + ((ImplDryBulkCargo) cargo).getOwner().getName() +
                                            ", Duration of Storage: " + ((ImplDryBulkCargo) cargo).getDurationOfStorage() + " days" +
                                            ", Storage Location: " + ((ImplDryBulkCargo) cargo).getStorageLocation() +
                                            ", Last Inspectiondate: " + ((ImplDryBulkCargo) cargo).getLastInspectionDate() +
                                            ", Value: " + cargo.getValue() +
                                            ", Hazards: " + cargo.getHazards() +
                                            ", Grain Size: " + ((ImplDryBulkCargo) cargo).getGrainSize()
                                    );
                                }

                            }




                            break;
                        case 2:
                            System.out.println("List of Unitised Cargos:");

                                PayloadUnitisedCargo payloadUnitisedCargo = new PayloadUnitisedCargo(false, null, null, null, null, 0);
                                CargoEvent eventViewUnitisedCargo = new CargoEvent(this, payloadUnitisedCargo);


                            List<? extends Cargo> tempUni = readCargoEventHandler.handle( eventViewUnitisedCargo);
                            List<Cargo> unitised = new ArrayList<>(tempUni);
                            for (Cargo cargo : unitised) {
                                if (cargo instanceof ImplUnitisedCargo) {
                                    System.out.println(
                                            "Customer: " + ((ImplUnitisedCargo) cargo).getOwner().getName() +
                                            "Fragile: " + ((ImplUnitisedCargo) cargo).isFragile() +
                                            ", Duration of Storage: " + ((ImplUnitisedCargo) cargo).getDurationOfStorage() + " days" +
                                            ", Storage Location: " + ((ImplUnitisedCargo) cargo).getStorageLocation() +
                                            ", Last Inspectiondate: " + ((ImplUnitisedCargo) cargo).getLastInspectionDate() +
                                            ", Value: " + cargo.getValue() +
                                            ", Hazards: " + cargo.getHazards()
                                    );
                                }

                            }



                            break;
                        case 3:
                            System.out.println("List of Dry Bulk and Unitised Cargos:");

                            PayloadDryAndUnitisedCargo payloadDryAndUnitisedCargo = new PayloadDryAndUnitisedCargo(0, null, null, null, null, 0, false);
                            CargoEvent eventViewDryAndUnitisedCargo = new CargoEvent(this, payloadDryAndUnitisedCargo);

                            List<? extends Cargo> tempDryAndUnitised = readCargoEventHandler.handle(eventViewDryAndUnitisedCargo);
                            List<Cargo> dryAndUnitised = new ArrayList<>(tempDryAndUnitised);
                            for (Cargo cargo : dryAndUnitised) {
                                if (cargo instanceof ImplDryBulkAndUnitisedCargo) {
                                    System.out.println(
                                            "Customer: " + ((ImplDryBulkAndUnitisedCargo) cargo).getOwner().getName() +
                                            "Fragile: " + ((ImplDryBulkAndUnitisedCargo) cargo).isFragile() +
                                            ", Duration of Storage: " + ((ImplDryBulkAndUnitisedCargo) cargo).getDurationOfStorage() + " days" +
                                            ", Storage Location: " + ((ImplDryBulkAndUnitisedCargo) cargo).getStorageLocation() +
                                            ", Last Inspectiondate: " + ((ImplDryBulkAndUnitisedCargo) cargo).getLastInspectionDate() +
                                            ", Value: " + cargo.getValue() +
                                            ", Hazards: " + cargo.getHazards() +
                                            ", Grain Size: " + ((ImplDryBulkAndUnitisedCargo) cargo).getGrainSize()
                                    );
                                }

                            }

                            break;
                            /*
                        case 4:
                            System.out.println("List of All Cargos:");
                            System.out.println("Dry Bulk Cargos:");

                            System.out.println("Unitised Cargos:");

                            System.out.println("Dry Bulk and Unitised Cargos:");

                            break;

                             */
                        default:
                            System.out.println("Invalid option. Please try again.");

                    }


                    break;
                case 5:
                    // View Customers
                     System.out.println("List of Customers:");


                     CustomerEvent eventCustomer = new CustomerEvent(this, null);

                    List<? extends Customer> customers = readCustomerEventHandler.handle(eventCustomer);

                    for (Customer customer : customers) {
                        System.out.println("Customer Name: " + customer.getName());
                    }


                    break;

                case 6:
                    // Add Customer
                    scanner.nextLine();
                    System.out.println("Adding a new customer." +
                            "\nEnter Customer name: ");

                    ImplCustomer customerName = new ImplCustomer(scanner.nextLine());

                    PayloadCustomer payloadCustomer = new PayloadCustomer(customerName);

                    CargoEvent newCustomer = new CargoEvent(this,payloadCustomer);

                    boolean handled = createHandler.handle(newCustomer);

                        if (handled) {
                                    System.out.println("Customer added successfully!");

                                }else{
                                    System.out.println("Failed to add Customer.");
                                }

                    break;

                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);


                default:
                    System.out.println("Invalid option. Please try again.");

            }


        }

    }
}