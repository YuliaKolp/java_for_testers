package ru.stqa.mantis.manager;

import okhttp3.*;
import org.openqa.selenium.By;
import ru.stqa.mantis.common.CommonFunctions;

import java.io.IOException;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

public class HttpSessionHelper extends HelperBase{
    OkHttpClient client;
    public HttpSessionHelper(ApplicationManager manager){
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public String getSignupToken(){
        var url = String.format("%s/signup_page.php", manager.property("web.baseUrl"));
        String token= "";
        RequestBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //System.out.println(request.toString());
            //System.out.println(response.body().string());
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            //get token
            token= CommonFunctions.getSignUpToken(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return token;
    }

    public void signup(String username, String email) {
        var token = getSignupToken();
        var url = String.format("%s/signup.php", manager.property("web.baseUrl"));
        RequestBody formBody = new FormBody.Builder()
                .add("signup_token", token)
                .add("username", username)
                .add("email", email)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(String.format("%S/login.php", manager.property("web.baseUrl")))
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            //System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {
        Request request = new Request.Builder()
                .url(manager.property("web.baseUrl"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();
            return body.contains("<span class=\"user-info\">");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getRegisterIdAndToken(String url){
        ArrayList<String> idAndToken = new ArrayList<>();

        RequestBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            var responseBody = response.body().string();
            //get id
            var id= CommonFunctions.getRegId(responseBody);
            idAndToken.add(id);

            //get token
            var token = CommonFunctions.getRegToken(responseBody);
            idAndToken.add(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return idAndToken;
    }


    public void registerUser(String url, String username, String password){
        List<String> idAndToken = getRegisterIdAndToken(url);
        var verify_user_id =  idAndToken.get(0);
        var account_update_token = idAndToken.get(1);

        var urlReg = String.format("%s/account_update.php", manager.property("web.baseUrl"));

        RequestBody formBody = new FormBody.Builder()
                .add("verify_user_id", verify_user_id)
                .add("account_update_token", account_update_token)
                .add("realname", username)
                .add("password", password)
                .add("password_confirm", password)
                .build();
        Request request = new Request.Builder()
                .url(urlReg)
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*public void startCreation(String user){
        //by AB
        var email = String.format("%s@localhost", user);
        if (!manager.session().isLoggedIn()){
            manager.session().login(manager.property("web.username"), manager.property("web.password"));
        }
        manager.driver().get(String.format("%s/manager_user_create_page.php", manager.property("web.baseUrl")));
        type(By.name("username"), user);
        type(By.name("realname"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']");
    }*/
}
