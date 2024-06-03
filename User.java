package scr;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
/**
 * A class that allows users to create an account as a seller or customer.
 *
 * <p>Purdue University -- CS18000 -- Fall 2023</p>
 *
 * @author Janhavi Palkar
 * @version November 10, 2023
 */

public class User {
    /* creates user with unique email, password, and a role of either Seller or Customer */
    private String email;
    private String password;
    public enum role {
        SELLER, CUSTOMER;
    }
    private role userType;

    public User(String email, String password, role userType) {
        /* A constructor that ensures that the user credentials are unique */
        this.email = email;
        this.password = password;
        this.userType = userType;
        /*
        if (findUser()) {
            System.out.println("User already exits!");
        } else if (!writeAUserVault(email, password)) {
            System.out.println("Failed to create user account " + email);
        }

         */
    }

    private boolean writeAUserVault(String email, String password) {
        /* Method that ensures user exists and adds user info to the
        userVault.txt document using the writeVault() method. */
        ArrayList<String> newVault = new ArrayList<String>();
        if (!findUser()) {
            String[] vault = readVault();
            if (!(vault.length == 1 && vault[0].equals(""))) {
                newVault = new ArrayList<String>(Arrays.asList(vault));
            }
            newVault.add(email + ";" + password + ";");
            vault = newVault.toArray(String[]::new);
            writeVault(vault);
            return true;
        }
        return false;
    }

    public boolean findUser() {
        /* returns true if user exists and login credentials are correct. */
        String[] vault = readVault();
        for (String aUser : vault) {
            String[] lineParts = aUser.split(";");
            if (Objects.equals(lineParts[0], this.email) && Objects.equals(lineParts[1], this.password)) {
                return true;
            }
        }
        return false;
    }

    public boolean editUser(String email, String password, String newEmail, String newPassword) {
        /* Method to edit user information */
        boolean userFound = findUser();
        if (userFound) {
            updateVault(email, password, newEmail, newPassword);
            return true;
        }
        return false;
    }

    private boolean updateVault(String email, String password, String newEmail, String newPassword) {
        /* Method that updates userVault.txt with specified user information */
        boolean wasFound = false;
        String[] vault = readVault();
        ArrayList<String> newVault = new ArrayList<String>();
        for (String aUser : vault) {
            String[] lineParts = aUser.split(";");
            if (Objects.equals(lineParts[0], email) && Objects.equals(lineParts[1], password)) {
                aUser = newEmail + ";" + newPassword + ";";
                wasFound = true;
            }
            newVault.add(aUser);
        }
        vault = newVault.toArray(String[]::new);
        if (wasFound) {
            writeVault(vault);
        }
        return wasFound;
    }

    private boolean writeVault(String[] vault) {
        /* Method that writes information to userVault.txt document */
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("userVault.txt")));
            pw.flush();
            pw.close();

            pw = new PrintWriter(new BufferedWriter(new FileWriter("userVault.txt")));
            for (String aUser : vault) {
                pw.write(aUser + "\n");
            }
            pw.flush();
            pw.close();
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String[] readVault() {
        /* Mehtod that reads the userVault.txt document */
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("userVault.txt"));
            String s;
            StringBuilder sb = new StringBuilder();
            while ((s = br.readLine()) != null) {
                if (!(s.equals(""))) {
                    sb.append(s + "\n");
                }
            }
            br.close();
            return sb.toString().split("\\n");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* getters and setters for email, password, and userRole fields */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public role getUserType() {
        return userType;
    }

    public void setUserType(role userType) {
        this.userType = userType;
    }
}
