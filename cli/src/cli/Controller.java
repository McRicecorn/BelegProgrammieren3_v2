package cli;

import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Cargo Management System CLI!"
        + "\nPlease select an option:"
                + "\n1. Create Cargo"
                + "\n2. Update Cargo"
                + "\n3. Delete Cargo"
                + "\n4. View Cargo"
                + "\n5. Exit");

        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("You selected: Create Cargo");
                // Call createCargo method here
                break;
            case 2:
                System.out.println("You selected: Update Cargo");
                // Call updateCargo method here
                break;
            case 3:
                System.out.println("You selected: Delete Cargo");
                // Call deleteCargo method here
                break;
            case 4:
                System.out.println("Exiting the system. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");

        }





    }

}
