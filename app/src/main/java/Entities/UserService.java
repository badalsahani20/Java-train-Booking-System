package Entities;

import java.util.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    // ✅ Function to check if a user exists
    public static boolean isUserExists(String username, List<User> users) {
        return users.stream()
                .anyMatch(user -> user.getUserName().equalsIgnoreCase(username));
    }

    // ✅ Function to add a new user
    public static void addUser(User user) {
        List<User> users = JsonUtility.readUsers();

        if (!isUserExists(user.getUserName(), users)) {
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            users.add(user);
            JsonUtility.writeToUser(users);
            System.out.println("✅ User added successfully!");
        } else {
            System.out.println("❌ Username already exists.");
        }
    }

    // ✅ Function to load users
    public static List<User> loadUser() {
        List<User> loadedUsers = JsonUtility.readUsers();
        return loadedUsers != null ? loadedUsers : new ArrayList<>();
    }

    // ✅ Function to print all users
    public static void printUsers() {
        List<User> users = loadUser();

        if (users.isEmpty()) {
            System.out.println("No Users Found!");
        } else {
            System.out.println("Existing Users:");
            for (User user : users) {
                System.out.println("Username: " + user.getUserName() + ", Password: " + user.getPassword());
            }
        }
    }

    // ✅ Function to remove a user
    public static void removeUser() {
        List<User> users = loadUser();

        if (users.isEmpty()) {
            System.out.println("❌ No User Found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Username you want to delete: ");
        String input = scanner.nextLine();

        boolean found = users.removeIf(user -> user.getUserName().equalsIgnoreCase(input));

        if (found) {
            System.out.println("✅ User removed successfully.");

            try {
                JsonUtility.writeToUser(users);
                System.out.println("✅ Updated user list saved.");
            } catch (Exception e) {
                System.err.println("❌ Failed to save updated list: " + e.getMessage());
            }
        } else {
            System.out.println("❌ User not found.");
        }
    }


    // ✅ Function to log in
    public static void login() {
        List<User> users = loadUser();
        boolean found = false;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter UserName: ");
        String userName = scanner.nextLine();

        System.out.print("Enter Password: ");
        String userPassword = scanner.nextLine();

        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(userName)) {

                System.out.println("Stored Password: " + user.getPassword());

                boolean isPasswordValid;

                if (user.getPassword().startsWith("$2a$")) {
                    // BCrypt hashed password
                    isPasswordValid = PasswordUtil.verifyPassword(userPassword, user.getPassword());
                } else {
                    // Plaintext or differently hashed password → rehash it
                    System.out.println("Migrating old password to BCrypt...");
                    String newHashedPassword = PasswordUtil.hashPassword(userPassword);

                    // Store the new hashed password
                    user.setPassword(newHashedPassword);
                    JsonUtility.writeToUser(users);

                    isPasswordValid = PasswordUtil.verifyPassword(userPassword, newHashedPassword);
                }

                if (isPasswordValid) {
                    found = true;
                    System.out.println("✅ Login Successful!");
                    break;
                } else {
                    System.out.println("❌ Invalid Password. Please try again.");
                    return;
                }
            }
        }

        if (!found) {
            System.out.println("❌ No User data found.");
            System.out.println("Please Sign Up!");
        }
    }

}
