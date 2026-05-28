package cli;

import cargo.Cargo;
import cargo.Hazard;
import cargo.UnitisedCargo;
import domainLogic.*;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.*;

public class Controller {
    public static void main(String[] args) {
        CargoManager cargoManager = new CargoManager();
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
                            Customer customer = cargoManager.getCustomerByName(name);

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


                            boolean created = cargoManager.createDryBulkCargo(customer, durationOfStorage, storageLocation, value1, hazards, grainSize);
                            if (created) {
                                System.out.println("Dry Bulk Cargo created successfully!");
                            } else {
                                System.out.println("Failed to create Dry Bulk Cargo. Please check your input and try again.");
                            }


                            break;
                        case 2:
                            System.out.println("Creating Unitised Cargo.");
                            scanner.nextLine(); // Buffer leeren

                            System.out.print("Enter Customer name: ");
                            String nameUni = scanner.nextLine();
                            Customer customerUni = new Customer(nameUni);

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

                            boolean createdUni = cargoManager.createUnitisedCargo(
                                    isFragile,
                                    valueUnitised,
                                    hazardsUni,
                                    customerUni,
                                    durationOfStorageUni,
                                    storageLocationUni
                            );

                            if (createdUni) {
                                System.out.println("Unitised Cargo created successfully!");
                            } else {
                                System.out.println("Failed to create Unitised Cargo.");
                            }

                            break;

                        case 3:
                            System.out.println("Creating Dry Bulk and Unitised Cargo.");
                            scanner.nextLine(); // Buffer leeren

                            System.out.print("Enter Customer name: ");
                            String nameDryAndUni = scanner.nextLine();
                            Customer customerDryAndUni = new Customer(nameDryAndUni);

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

                            boolean createdDryAndUni = cargoManager.createDryBulkCargoAndUnitisedCargo(
                                    grainSizeDryAndUni,
                                    valueDryAndUni,
                                    hazardsDryAndUni,
                                    customerDryAndUni,
                                    durationOfStorageDryAndUni,
                                    storageLocationDryAndUni,
                                    isFragileDryAndUni
                            );

                            if (createdDryAndUni) {
                                System.out.println("Dry Bulk and Unitised Cargo created successfully!");
                            } else {
                                System.out.println("Failed to create Dry Bulk and Unitised Cargo.");
                            }

                            break;

                        default:
                            System.out.println("Invalid cargo type. Please try again.");


                            // Call createCargo method here
                            break;
                    }

                    break;
                case 2:
                    // Call updateCargo method here
                    System.out.println("Updating Cargo inspection date." +
                            "\nWhich Cargo do you want to update? (Enter Storage Location): ") ;
                    int storageLocation = scanner.nextInt();
                    scanner.nextLine();
                    Cargo cargoTypeToUpdate = cargoManager.getCargoByStorageLocation(storageLocation);
                    if (cargoTypeToUpdate == null) {
                        System.out.println("No cargo found at this storage location.");
                        break;
                    }

                    System.out.println("Enter new inspection date (YYYY-MM-DD): (example: 2024-12-31)");
                    String newInspectionDateStr = scanner.nextLine();

                    Date newInspectionDate;
                    try {
                        newInspectionDate = java.sql.Date.valueOf(newInspectionDateStr);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        break;
                    }


                    boolean updated = false;

                    if (cargoTypeToUpdate instanceof ImplDryBulkCargo dry) {
                        updated = cargoManager.updateDryBulkCargo(dry, newInspectionDate);
                    } else if (cargoTypeToUpdate instanceof ImplUnitisedCargo uni) {
                        updated = cargoManager.updateUnitisedCargo(uni, newInspectionDate);
                    } else if (cargoTypeToUpdate instanceof ImplDryBulkAndUnitisedCargo both) {
                        updated = cargoManager.updateDryBulkAndUnitisedCargo(both, newInspectionDate);
                    }

                    if (updated) {
                        System.out.println("Inspection date updated successfully!");
                    } else {
                        System.out.println("Failed to update inspection date.");
                    }

                    break;

                case 3:

                    // Call deleteCargo method here
                    System.out.println("Deleting Cargo.");
                    System.out.println("Which Cargo do you want to delete? (Enter Storage Location): ");

                    int deleteLocation = scanner.nextInt();
                    scanner.nextLine(); // Buffer leeren

                    Cargo cargoToDelete = cargoManager.getCargoByStorageLocation(deleteLocation);

                    if (cargoToDelete == null) {
                        System.out.println("No cargo found at this storage location.");
                        break;
                    }

                    boolean deleted = false;

                    if (cargoToDelete instanceof ImplDryBulkCargo dry) {
                        deleted = cargoManager.deleteDryBulkCargo(dry);
                    } else if (cargoToDelete instanceof ImplUnitisedCargo uni) {
                        deleted = cargoManager.deleteUnitisedCargo(uni);
                    } else if (cargoToDelete instanceof ImplDryBulkAndUnitisedCargo both) {
                        deleted = cargoManager.deleteDryBulkAndUnitisedCargo(both);
                    }

                    if (deleted) {
                        System.out.println("Cargo deleted successfully!");
                    } else {
                        System.out.println("Failed to delete cargo.");
                    }



                break;
                case 4:
                    //View Cargos

                    System.out.println("Which Cargo type do you want to view?" +
                            "\n1. Dry Bulk Cargo" +
                            "\n2. Unitised Cargo" +
                            "\n3. Dry Bulk and Unitised Cargo" +
                            "\n4. All Cargos");
                    int viewCargoType = scanner.nextInt();

                    switch (viewCargoType) {

                        case 1:
                            System.out.println("List of Dry Bulk Cargos:");
                            // Call method to get and display Dry Bulk Cargos

                            List<ImplDryBulkCargo> dryBulkCargos = cargoManager.readDryBulkCargo();



                            System.out.println(dryBulkCargos.toString());

                            break;
                        case 2:
                            System.out.println("List of Unitised Cargos:");

                            List<ImplUnitisedCargo> unitisedCargos = cargoManager.readUnitisedCargo();

                            System.out.println(unitisedCargos.toString());


                            break;
                        case 3:
                            System.out.println("List of Dry Bulk and Unitised Cargos:");

                            List<ImplDryBulkAndUnitisedCargo> dryBulkAndUnitisedCargos = cargoManager.readDryBulkAndUnitisedCargo();

                            System.out.println(dryBulkAndUnitisedCargos.toString());

                            break;
                        case 4:
                            System.out.println("List of All Cargos:");
                            System.out.println("Dry Bulk Cargos:");
                            List<ImplDryBulkCargo> allDryBulkCargos = cargoManager.readDryBulkCargo();
                            System.out.println(allDryBulkCargos.toString());

                            System.out.println("Unitised Cargos:");
                            List<ImplUnitisedCargo> allUnitisedCargos = cargoManager.readUnitisedCargo();
                            System.out.println(allUnitisedCargos.toString());

                            System.out.println("Dry Bulk and Unitised Cargos:");
                            List<ImplDryBulkAndUnitisedCargo> allDryBulkAndUnitisedCargos = cargoManager.readDryBulkAndUnitisedCargo();
                            System.out.println(allDryBulkAndUnitisedCargos.toString());

                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");

                    }


                    break;
                case 5:
                    // View Customers
                     System.out.println("List of Customers:");
                     List<Customer> customers = cargoManager.readCustomers();
                     System.out.println(customers.toString());


                    break;

                case 6:
                    // Add Customer
                    scanner.nextLine();
                    System.out.println("Adding a new customer." +
                            "\nEnter Customer name: ");

                    String customerName = scanner.nextLine();
                    boolean registered = cargoManager.registerCustomer(new Customer(customerName));
                    if (registered) {
                        System.out.println("Customer added successfully!");
                    } else {
                        System.out.println("Failed to add customer. Please check your input and try again.");
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