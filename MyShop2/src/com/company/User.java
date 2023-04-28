package com.company;

public class User {
    String username;
    String password;
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    @Override
    public boolean equals(Object o){
        User u = (User) o;
        if(u.username.equals(this.username) && u.password.equals(this.password)){
            return true;
        }
        return false;
    }
}
