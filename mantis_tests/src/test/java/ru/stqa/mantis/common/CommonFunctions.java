package ru.stqa.mantis.common;

import org.junit.jupiter.api.Assertions;

import java.util.Random;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {

    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());

        return result;
    }


    public static String getUrl(String text) {
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        Assertions.assertNotEquals("", url);
        return url;
    }

    public static String getSignUpToken(String text) {
        var pattern = Pattern.compile("name=\"signup_token\" value=\"\\S*\"");
        var matcher = pattern.matcher(text);
        var token = "";
        if (matcher.find()) {
            var tokenString = text.substring(matcher.start(), matcher.end());
            token = tokenString.substring("name=\"signup_token\" value=\"".length(), tokenString.length() - 1);
        }
        Assertions.assertNotEquals("", token);
        return token;
    }

    public static String getRegToken(String text) {
        var pattern = Pattern.compile("name=\"account_update_token\" value=\"\\S*\"");
        var matcher = pattern.matcher(text);
        var token = "";
        if (matcher.find()) {
            var tokenString = text.substring(matcher.start(), matcher.end());
            token = tokenString.substring("name=\"account_update_token\" value=\"".length(), tokenString.length() - 1);
        }
        Assertions.assertNotEquals("", token);
        return token;
    }

    public static String getRegId(String text) {
        var pattern = Pattern.compile("name=\"verify_user_id\" value=\"\\S*\"");
        var matcher = pattern.matcher(text);
        var id = "";
        if (matcher.find()) {
            var tokenString = text.substring(matcher.start(), matcher.end());
            id = tokenString.substring("name=\"verify_user_id\" value=\"".length(), tokenString.length() - 1);
        }
        Assertions.assertNotEquals("", id);
        return id;
    }


}
