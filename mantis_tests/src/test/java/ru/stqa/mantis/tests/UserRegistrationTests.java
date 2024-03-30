package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.stream.Stream;


public class UserRegistrationTests extends TestBase {
    public static Stream<String> randomUser(){
        return Stream.of(CommonFunctions.randomString(4));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canRegisterUser(String user) {
        var password = "password";
        var email = String.format("%s@localhost", user);
        System.out.println(String.format("Username is '%s'. Email is '%s'. Password is '%s'", user, email, password));

        // create user (email) on mail server (JamesHelper)
        app.jamesCli().addUser(email, "password");

        // open creation form and send (browser)
        app.http().signup(user, email);

        // recieve (wait for) email (MailHelper)
        var messages =  app.mail().receive(email, password, Duration.ofSeconds(60));

        // retrieve link out of email
        var text = messages.get(0).content();
        var url = CommonFunctions.getUrl(text);

        // go to browser , go by link and register user (browser)
        app.http().registerUser(url, user, password);

        // check that user can log in (HttpSessionHelper)
        app.http().login(user, password);
        Assertions.assertTrue(app.http().isLoggedIn());
        // plan
        // create user (email) on mail server (JamesHelper) +
        // open creation form and send (browser)+-
        // recieve (wait for) email (MailHelper) +
        // retrieve link out of emial +
        // go to browser , go by link and register user (browser)+
        // check that user can log in (HttpSessionHelper)
    }



}
