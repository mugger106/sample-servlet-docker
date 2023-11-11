package com;

public class LoginInput {
    private String loginId;
    private String password;

    String getLoginId(){
        return this.loginId;
    }

    void setLoginId(String loginId){
        this.loginId = loginId;
    }

    String getPassword(){
        return this.password;
    }

    void setPassword(String password){
        this.password = password;
    }
}
