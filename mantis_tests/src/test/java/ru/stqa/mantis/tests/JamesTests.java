package ru.stqa.mantis.tests;

//import org.apache.commons.exec.CommandLine;


import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

public class JamesTests extends TestBase {
    @Test
    void canCreateUser(){
        app.jamesCli().addUser(
                String.format("%s@localhost", CommonFunctions.randomString(8)), "password");
                //"user1@localhost", "password");
    }
}
