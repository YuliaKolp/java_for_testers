package ru.stqa.mantis.tests;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.manager.HelperBase;

import java.io.IOException;
import java.time.Duration;
import java.util.regex.Pattern;


public class UserRegistrationTests extends TestBase {
    @Test
    void canRegisterUser(/*String username*/) {
        var password = "password";
        //var username = "dmni";
        var username = CommonFunctions.randomString(4);
        var email = String.format("%s@localhost", username);
        System.out.println(String.format("Username is '%s'. Email is '%s'. Password is '%s'", username, email, password));

        // create user (email) on mail server (JamesHelper)
        app.jamesCli().addUser(email, "password");

        // open creation form and send (browser)
        app.http().signup(username, email);

        // recieve (wait for) email (MailHelper)
        var messages =  app.mail().receive(email, password, Duration.ofSeconds(60));

        // retrieve link out of email
        var text = messages.get(0).content();
        var url = CommonFunctions.getUrl(text);

        // go to browser , go by link and register user (browser)
        app.http().registerUser(url, username, password);

        // check that user can log in (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());

        // plan
        // create user (email) on mail server (JamesHelper) +
        // open creation form and send (browser)+-
        // recieve (wait for) email (MailHelper) +
        // retrieve link out of emial +
        // go to browser , go by link and register user (browser)+
        // check that user can log in (HttpSessionHelper)
    }

    @Test
    void CanCreateUserRest(/*String username*/) {
        var password = "password";
        var username = CommonFunctions.randomString(4);
        var email = String.format("%s@localhost", username);
        // create user (email) on mail server (JamesHelper)
        app.jamesApi().addUser(email, password);

        // open creation form and send (browser)
        System.out.println(String.format("Email is '%s'. Password is '%s'", email, password));
        app.http().signup(username, email);

        // recieve (wait for) email (MailHelper)
        var messages =  app.mail().receive(email, password, Duration.ofSeconds(90));

        // retrieve link out of email
        var text = messages.get(0).content();
        System.out.println("-------------------");
        System.out.println(text);
        var url = CommonFunctions.getUrl(text);

        // go to browser , go by link and register user (browser)
        app.http().registerUser(url, username, password);

        // check that user can log in (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }


}
