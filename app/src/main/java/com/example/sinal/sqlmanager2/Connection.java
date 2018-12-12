package com.example.sinal.sqlmanager2;

public class Connection {
    private String password;
    private int port;
    private String username;

    //Accessors and modifiers
    public void setPassword(String password){
        this.password = password;
    }
    public void setPort(int port){
        this.port = port;
    }
    public String getPassword(){
        return this.password;
    }
}
