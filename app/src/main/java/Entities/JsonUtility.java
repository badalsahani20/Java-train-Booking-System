package Entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtility {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // File paths for Users and Trains
    private static final String USER_FILE_PATH = "app/src/main/resources/Users.JSON";
    private static final String TRAIN_FILE_PATH = "app/src/main/resources/Trains.JSON";

    public JsonUtility() {}

    // Generic method to read JSON files
    private static <T> List<T> readFromFile(String filePath, TypeReference<List<T>> typeReference) {
        List<T> data = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No previous data found: " + filePath);
            return data;
        }

        try {
            data = objectMapper.readValue(file, typeReference);
        } catch (IOException ex) {
            System.out.println("Failed to read from " + filePath + ": " + ex.getMessage());
        }

        return data;
    }

    // Method to write JSON files
    public static void writeToUser(List<User> users){
        try {
            String filePath = "app/src/main/resources/Users.JSON";
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new java.io.File(filePath), users);
            System.out.println("Users file saved successfully.");
        }
        catch (IOException ex){
            System.out.println("Failed to write to Users file: " + ex.getMessage());
        }
    }


    public static void writeToTrain(List<Train> trains){
        try {
            String filePath = "app/src/main/resources/Trains.JSON";
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new java.io.File(filePath), trains);
            System.out.println("Trains file saved successfully.");
        }
        catch (IOException ex){
            System.out.println("Failed to write to Trains file: " + ex.getMessage());
        }
    }


    // Methods for Users
    public static List<User> readUsers() {
        return readFromFile(USER_FILE_PATH, new TypeReference<>() {
        });
    }



    // Methods for Trains
    public static List<Train> readTrains() {
        return readFromFile(TRAIN_FILE_PATH, new TypeReference<>() {
        });
    }

}
