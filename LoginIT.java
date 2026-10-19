/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javapoe.part1;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginIT {

    @Test
    public void testCheckUserName() {
        Login user = new Login();

        assertTrue(user.checkUserName("ab_c"));
        assertFalse(user.checkUserName("abcdef"));
        assertFalse(user.checkUserName("abcde"));
    }

    @Test
    public void testCheckPasswordComplexity() {
        Login user = new Login();

        assertTrue(user.checkPasswordComplexity("Abcd123!"));
        assertFalse(user.checkPasswordComplexity("password"));
        assertFalse(user.checkPasswordComplexity("Abc123T"));
    }

    @Test
    public void testCheckCellPhoneNumber() {
        Login user = new Login();

        assertTrue(user.checkCellPhoneNumber("+27834557896"));
        assertFalse(user.checkCellPhoneNumber("12345"));
    }

    @Test
    public void testLoginSuccessful() {
        Login user = new Login();

        user.registerUser(
            "ab_c",
            "Abcd123!",
            "+27834557896",
            "Test",
            "User"
        );

        assertTrue(user.loginUser("ab_c", "Abcd123!"));
    }

    @Test
    public void testLoginUnsuccessful() {
        Login user = new Login();

        user.registerUser(
            "ab_c",
            "Abcd123!",
            "+27834557896",
            "Test",
            "User"
        );

        assertFalse(user.loginUser("wrong", "wrong"));
    }
}