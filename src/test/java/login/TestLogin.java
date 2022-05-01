package login;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestLogin {

    @Test
    public void testLogin() {
        Scanner mockScanner = mock(Scanner.class);
        LogIn mockLogin = new LogIn();
        when(mockScanner.nextLine())
                .thenReturn("admin@admin.com")
                .thenReturn("Password1");

        mockLogin.userInput.setUserInput(mockScanner);

        assertTrue(mockLogin.logIn());
    }

    @Test
    public void testFailedLogin() {
        Scanner mockScanner = mock(Scanner.class);
        LogIn mockLogin = new LogIn();
        when(mockScanner.nextLine())
                .thenReturn("admin@admin.com")
                .thenReturn("IncorrectPassword");

        mockLogin.userInput.setUserInput(mockScanner);

        assertFalse(mockLogin.logIn());
    }

    @Test
    public void testNoAccount() {
        String emailAddress = "Tester@test.com";
        Scanner mockLoginScanner = mock(Scanner.class);
        Scanner mockRegisterScanner = mock(Scanner.class);
        LogIn mockLogin = new LogIn();
        when(mockLoginScanner.nextLine())
                .thenReturn("y");
        when(mockRegisterScanner.nextLine())
                .thenReturn("tester")
                .thenReturn("mcTestFace")
                .thenReturn("Password1");

        mockLogin.userInput.setUserInput(mockLoginScanner);
        Register.userInput.setUserInput(mockRegisterScanner);

        assertTrue(mockLogin.noAccount(emailAddress));
    }

}
