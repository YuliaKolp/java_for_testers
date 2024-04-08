package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;

public class UserCreationTestsDevMail extends TestBase {
    DeveloperMailUser user;

    @Test
    void CanCreateUserDevMail() {
        var password = "password";
        // create user (email) on mail server (JamesHelper)
        var email = String.format("%s@developermail.com", user.name());
        user = app.developerMail().addUser();


        // open creation form and send (browser)
        System.out.println(String.format("Email is '%s'. Password is '%s'", email, password));
        app.http().signup(user.name(), email);

        // recieve (wait for) email (MailHelper)
        var message =  app.developerMail().receive(user, Duration.ofSeconds(60));

        // retrieve link out of email
        var url = CommonFunctions.getUrl(message);


        // go to browser , go by link and register user (browser)
        app.http().registerUser(url, user.name(), password);

        // check that user can log in (HttpSessionHelper)
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

   @AfterEach
    void deleteMailUser(){
        System.out.println("===============Delete");
        app.developerMail().deleteUser(user);
    }
}
