/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javapoe.part1;


public class Login {
  
    //Instance vaiables//
    private String firstName;
    private String lastName;
    private String cellphoneNumber;
    private String storedUsername;
    private String storedPassword;
   

    public Login(String fname, String lname) {
        this.firstName = fname;
        this.lastName = lname;
    }
    
    // Checks the username format
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    // Checks password complexity using regex
    public boolean checkPasswordComplexity(String password) {
        // Password must be at least 8 chars, 1 capital, 1 number, 1 special char
        boolean passwordOkay = false;
        boolean hasNumber = false;
        boolean hasCap = false;
        boolean hasChar = false;
        char current;

        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                current = password.charAt(i);

                if (Character.isUpperCase(current)) {
                    hasCap = true;
                }
                if (Character.isDigit(current)) {
                    hasNumber = true;
                }
                if (!(Character.isLetterOrDigit(current))) {
                    hasChar = true;
                }
            }
            if (hasNumber && hasCap && hasChar) {
                passwordOkay = true;
            }
            return passwordOkay;
        }
        return false;
    }
    
    //This regex logic was adapted from the following sources:
    //GeeksforGeeks (2023)Phone number validation regex source code
    //The code ensures the number starts with international code and followed by a maximum of 9 characters
    public boolean checkCellPhoneNumber(String number) {
        // Regex for international format (e.g. +27716774402)
        String regex = "^\\+\\d{11,13}$";
        return number.matches(regex);
    }
    
    // Registers the user and returns status messages
    public String registerUser(String username, String password, String cellNumber, String fName, String lName) {
        StringBuilder message = new StringBuilder();
        this.firstName = fName;
        this.lastName = lName;

        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        message.append("Username successfully captured.\n");

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        message.append("Password successfully captured.\n");

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        message.append("Cellphone number successfully added.\n");
        
        // stored for later login
        this.cellphoneNumber = cellNumber; 
        this.storedUsername = username;
        this.storedPassword = password;

       
        System.out.println(message.toString());

        return "Registration successful.";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(this.storedUsername) && password.equals(this.storedPassword);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + this.firstName + " " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
