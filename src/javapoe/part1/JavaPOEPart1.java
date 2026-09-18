/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javapoe.part1;
import java.util.Scanner;

public class JavaPOEPart1 {

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Login user = new Login("",""); 
    
        String firstName;
        String lastName;
        String username;
        String password;
        String cellphone;
        
        System.out.println("========================");
        System.out.println("REGISTRATION SYSTEM");
        System.out.println("========================");
        
        //First Name
      System.out.println("Enter your name:");
      firstName = input.nextLine();  
      
      //Last Name
      System.out.println("Enter your surname:");
      lastName = input.nextLine();
        
      //username loop//
      while (true){
    
         System.out.println("Enter a username: ");
         username = input.nextLine();
         
    if (user.checkUserName(username)) {
    System.out.println("Username successfully captured");
    break;
      }else{
            
        System.out.println("Username is not correctly formatted.");
        System.out.println("Username must contain an '_'.");
        System.out.println("Username must contain an underscore and be no more than 5 characters");
            }
      }
  
    //password loop//
    while (true){
        System.out.println("Enter a password: ");
        password = input.nextLine();
        
        if
          (user.checkPasswordComplexity(password))   {   
          System.out.println("Password successfully captured.");
    break;
    }else{
        
    System.out.println("Password is not correctly formatted.");
    System.out.println("Password must be atleast 8 characters long");
    System.out.println("contain a capital letter, a number and a special character");
         }
    }    
    //CellPhone Number loop//
    while (true) {
     System.out.println("Enter your cellphone: ");
     cellphone= input.nextLine();
     
     if (user.checkCellPhoneNumber(cellphone)){
        System.out.println("CellPhoneNumber succesfully captured.");
         break;
     } else {
         
     System.out.println("Cell phone number is not correctly formatted.");
     System.out.println("Please enter a South African number in the format +27XXXXXXXX.");
     }
    }
    //Register the user and output the status message//
    String regStatus = user.registerUser(username, password, cellphone, firstName, lastName);
    System.out.println(regStatus);
    System.out.println("============================");
    System.out.println("REGISTRATION COMPLETE");
    System.out.println("============================");
    
    System.out.println("Welcome, " + firstName + "" + lastName + "!");
    System.out.println("Your account has been successfully registered.");
    
    //Login//
    System.out.println();
    
    System.out.println("============================");
    System.out.println("LOGIN");
    System.out.println("============================");
    System.out.println("Enter your username:");
    String loginUsername = input.nextLine();
    
    System.out.println("Enter your password: ");
    String loginPassword = input.nextLine();
    
   System.out.println(user.returnLoginStatus(loginUsername, loginPassword));
    
    input.close();
   
    }
       }