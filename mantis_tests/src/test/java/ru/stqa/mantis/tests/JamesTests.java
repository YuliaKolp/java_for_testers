package ru.stqa.mantis.tests;

//import org.apache.commons.exec.CommandLine;


import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;

public class JamesTests extends TestBase {
    @Test
    void canCreateUser(){
        app.jamesCli().addUser(
                //String.format("%s@localhost", CommonFunctions.randomString(8)), "password");
                "user222@localhost", "password");
    }
    @Test
    void canGetMail(){
        var email = "user222@localhost";
        var password = "password";
        var messages =  app.mail().receive(email, password, Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var url = CommonFunctions.getUrl(text);
        System.out.println(url);
    }

}
