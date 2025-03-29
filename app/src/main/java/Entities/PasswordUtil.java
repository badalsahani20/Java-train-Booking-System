package Entities;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword,BCrypt.gensalt(12));
    }

    public static boolean verifyPassword(String enteredPassword, String storedHashedPassword){
        return BCrypt.checkpw(enteredPassword,storedHashedPassword);
    }
}
