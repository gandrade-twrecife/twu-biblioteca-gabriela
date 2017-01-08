package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private String userGabrielaName = "Gabriela Andrade";
    private String userGabrielaLogin = "gandrade";
    private String userGabrielaPassword = "asdf";
    private User userGabriela = new User(userGabrielaName, userGabrielaLogin, userGabrielaPassword);

    @Test
    public void createUserTest() {
        User gabriela = new User(userGabrielaName, userGabrielaLogin, userGabrielaPassword);

        assertEquals(userGabrielaName, gabriela.getName());
        assertEquals(userGabrielaLogin, gabriela.getLogin());
        assertEquals(userGabrielaPassword, gabriela.getPassword());
    }

    @Test
    public void authenticateWrongPasswordTest(){
        String wrongPassword = "qwer";
        assertFalse(userGabriela.authenticate(wrongPassword));
    }

    @Test
    public void authenticateValidPasswordTest() {
        assertTrue(userGabriela.authenticate(userGabrielaPassword));
    }

    @Test
    public void authenticateNullPasswordTest() {
        assertFalse(userGabriela.authenticate(null));
    }
}
