package Entities;

//import javax.xml.transform.Source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainService {
    public static final Scanner scanner = new Scanner(System.in);

    public static boolean isTrainExists(String trainName, List<Train> trains){
        return trains.stream()
                .anyMatch(train -> train.getTrainName().equalsIgnoreCase(trainName));
    }

    public static void addTrain(Train train) throws IOException {
        List<Train> trains = JsonUtility.readTrains();
        if(!isTrainExists(train.getTrainName(),trains)){
            trains.add(train);
            JsonUtility.writeToTrain(trains);
            System.out.println("Train added SuccessFully");
        }
        else{
            System.out.println("Train already exists");
        }
    }

    public static List<Train> loadTrains(){
        List<Train> loadedTrains = JsonUtility.readTrains();
        return loadedTrains != null ? loadedTrains : new ArrayList<>();
    }

    public static void printTrains(){
        List<Train> trains = loadTrains();
        if(trains.isEmpty()){
            System.out.println("No train Found.");
        }else {
            System.out.println("Existing Trains: ");
            for(Train train : trains){
                System.out.println("Train Name: " + train.getTrainName() + " ,TrainId: " + train.getTrainId() + " ,source: "+ train.getSource() + " ,destination: " + train.getDestination() + " ,Available seats: " + train.getAvailableSeats() + " ,Fare: " + train.getPrice());
            }
        }
    }

    public static String  getTrain(String trainId, String trainName){
        List<Train> trains = JsonUtility.readTrains();
        if(trains.isEmpty()){
            System.out.println("Sorry Currently no trains are available.");
            return null;
        }

       Train matchingTrains = trains.stream()
               .filter(train -> train.getTrainName().equalsIgnoreCase(trainName) || String.valueOf(train.getTrainId()).equals(trainId))
               .findFirst()
               .orElse(null);

        return (matchingTrains != null) ?
                "Train ID: " + matchingTrains.getTrainId() + ", Name: " + matchingTrains.getTrainName() : "Train not found";
    }

    public static void bookTrain(String source, String destination){
        try {
            List<Train> trains = JsonUtility.readTrains();

            if (trains.isEmpty()) {
                System.out.println("❌ Sorry, Currently no trains are available.");
                return;
            }

            System.out.print("Enter TrainID or Name to book: ");
            String trainIdOrName = scanner.nextLine();

            System.out.print("Enter the number of seats to book: ");
            int seatsBooked = scanner.nextInt();
            scanner.nextLine();

            Train trainToBook = trains.stream()
                    .filter(train ->
                            (train.getTrainId() == Integer.parseInt(trainIdOrName) ||
                                    train.getTrainName().equalsIgnoreCase(trainIdOrName)) &&
                                    train.getSource().equalsIgnoreCase(source) &&
                                    train.getDestination().equalsIgnoreCase(destination))
                    .findFirst().orElse(null);

            if (trainToBook == null) {
                System.out.println("❌ Train not Found with the given ID/Name and route.");
                return;
            }

            if (trainToBook.getAvailableSeats() >= seatsBooked) {
                trainToBook.setAvailableSeats(trainToBook.getAvailableSeats() - seatsBooked);

                JsonUtility.writeToTrain(trains);
                System.out.println("✅ Booking successful!");
                System.out.println("Train: " + trainToBook.getTrainName());
                System.out.println("Route: " + source + "->" + destination);
                System.out.println("Remaining Seats: " + trainToBook.getAvailableSeats());
            } else {
                System.out.println("❌ Insufficient seats available");
            }
        } catch (NumberFormatException e){
            System.out.println("❌ Invalid train ID format. Please enter a valid ID.");
        }catch (Exception e){
            System.out.println("❌ An error occurred during booking: " + e.getMessage());
        }
    }
}
