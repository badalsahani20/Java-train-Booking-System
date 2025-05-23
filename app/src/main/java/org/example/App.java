/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;
//import Entities.Train;
import Entities.TrainService;
import Entities.UserService;
import Entities.User;

//import java.util.List;
import javax.xml.transform.Source;
import java.util.Scanner;

public class App {
//    private static ArrayList<User> users = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("************************************");
        System.out.println("Welcome to the Train Booking System!");
        System.out.println("************************************");

        System.out.println("Our Services: ");

        while (true){
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. View all Users");
            System.out.println("4. Remove a User");
            System.out.println("5. View All Trains");
            System.out.println("6. Book a Train");
            System.out.println("7. Exit");
            System.out.println("Choose an Option");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){

                case 1 :
                    System.out.println("Enter a Username");
                    String username = scanner.nextLine();
                    System.out.println("Enter password");
                    String password = scanner.nextLine();
                    User user = new User(username, password);
                    UserService.addUser(user);
                    break;

                case 2:
                    UserService.login();
                    break;

                case 3:
                    UserService.printUsers();
                    break;

                case 4:
                    UserService.removeUser();
                    break;

                case 5:
                    System.out.println("\nAvailable Trains: ");
                    TrainService.printTrains();
                    break;
                case 6:
//                    System.out.print("Enter TrainId or Train Name: ");
//                    String trainIdOrName = scanner.nextLine();

                    System.out.print("Enter Source: ");
                    String source = scanner.nextLine();

                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();

//                    System.out.print("Enter Number of Seats: ");
//                    String seatsToBook = scanner.nextLine();
//                    scanner.nextLine();

                    TrainService.bookTrain(source,destination);
                    break;

                case 7:
                    System.out.println("Exiting the program.Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }
}
