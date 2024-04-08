package ru.stqa.mantis.model;

public record UserData(String realname, String username, String email, String password) {
    public UserData(){
        this("", "", "","");
    }
    public UserData withRealName(String realname){
        return new UserData(realname, this.username, this.email, this.password);
    }
    public UserData withUserName(String username){
        return new UserData(this.realname, username, this.email, this.password);
    }
    public UserData withEmail(String email){
        return new UserData(this.realname, this.username, email, this.password);
    }
    public UserData withPassword(String password){
        return new UserData(this.realname, this.username, this.email, password);
    }
}
