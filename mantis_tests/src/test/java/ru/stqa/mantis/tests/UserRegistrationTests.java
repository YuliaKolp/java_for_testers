package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase{
    @Test

    void canRegisterUser(String username){
        var email = String.format("%s@localhost", username);
        // create user (email) on mail server (JamesHelper)
        // open creation form and send (browser)
        // recieve (wait for) email (MailHelper)
        // retrieve link out of emial
        // go to browser , go by link and register user (browser)
        // check that user can log in (HttpSessionHelper)

    }
}
