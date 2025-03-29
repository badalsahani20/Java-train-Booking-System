package Entities;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;

import static Entities.JsonUtility.readUsers;

public class User {
    private String userName;
    private String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password =  password;
    }




    public User(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "-----------------------\n" +
                        "Username : %s\n" +
                        "Password : %s\n" +
                        "-----------------------",
                userName, password
        );
    }



}

