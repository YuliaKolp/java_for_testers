package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.stream.Stream;
import static ru.stqa.mantis.tests.TestBase.app;

public class UserCreationTests extends TestBase {

    DeveloperMailUser user;
    public static Stream<String> randomUser(){
        return Stream.of(CommonFunctions.randomString(4));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void CanCreateUserRest(String user) {
        var password = "password";
        var email = String.format("%s@localhost", user);
        // create user (email) on mail server (JamesHelper)
        app.jamesApi().addUser(email, password);

        // open creation form and send (browser)
        System.out.println(String.format("Email is '%s'. Password is '%s'", email, password));
        app.http().signup(user, email);

        // recieve (wait for) email (MailHelper)
        var messages =  app.mail().receive(email, password, Duration.ofSeconds(90));

        // retrieve link out of email
        var text = messages.get(0).content();
        var url = CommonFunctions.getUrl(text);

        // go to browser , go by link and register user (browser)
        app.http().registerUser(url, user, password);

        // check that user can log in (HttpSessionHelper)
        app.http().login(user, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void CanCreateUserDevMail() {
        var password = "password";
        // create user (email) on mail server (JamesHelper)
        user = app.developerMail().addUser();
        var email = String.format("%s@developer.mail.com", user.name());

        // open creation form and send (browser)
        System.out.println(String.format("Email is '%s'. Password is '%s'", email, password));
        /*app.http().signup(user, email);

        // recieve (wait for) email (MailHelper)
        var messages =  app.mail().receive(email, password, Duration.ofSeconds(90));

        // retrieve link out of email
        var text = messages.get(0).content();
        var url = CommonFunctions.getUrl(text);

        // go to browser , go by link and register user (browser)
        app.http().registerUser(url, user, password);

        // check that user can log in (HttpSessionHelper)
        app.http().login(user, password);
        Assertions.assertTrue(app.http().isLoggedIn());*/
    }

    @AfterEach
    void deleteMailUser(){
        app.developerMail().deleteUser(user);
    }
}
