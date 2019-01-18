/**
 * @author: Sinan
 */
package com.example.sinal.sqlmanager2;
import android.util.Log;

import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;


public class Connection {
    private String password;
    private String serverAddress;
    private int port;
    private String username;
    private String database;
    private String connection;
    private java.sql.Connection sqlconnection;
    //getter / Setter
    public void setPassword(String password){
        this.password = password;
    }
    public void setPort(int port){
        this.port = port;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setConnection(String connection){
        this.connection = connection;
    }
    public void setDatabase(String database){
        this.database = database;
    }
    public void setServerAddress(String serverAddress){
        this.serverAddress = serverAddress;
    }
    public String getPassword(){
        return this.password;
    }
    public int getPort(){
        return this.port;
    }
    public String getUsername(){
        return this.username;
    }
    public String getDatabase(){
        return  this.database;
    }
    public String getServerAddress(){
        return  this.serverAddress;
    }
    public String getConnection(){
        return  this.connection;
    }




    //Constructor
    public Connection(String username, int port, String password, String serverAddress, String database){
        this.setPassword(password);
        this.setPort(port);
        this.setUsername(username);
        this.setConnection("");
        this.setServerAddress(serverAddress);
        this.setDatabase(database);
    }

    //Methods
    public String connectionTest() {
        this.setConnection("jdbc:mysql://" + this.getServerAddress() + ":" + this.getPort() + "/" + this.getDatabase() + "?autoReconnect=true&useSSL=false");
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                return e.getMessage();
            }
            this.sqlconnection = DriverManager.getConnection(this.getConnection(), this.getUsername(), this.getPassword());
            return "Success!!!!";
        } catch (SQLNonTransientConnectionException ex) {
            return "nonTransientConnection " + ex.getMessage() + " " +ex.getCause();
        } catch (SQLSyntaxErrorException ex) {
            return ex.getMessage();
        } catch (SQLException ex) {
            Log.e("Error ", ex.toString());
            System.out.println(ex.toString());
            return "error: " + ex.getMessage();
        }
    }

    public boolean connectionStatus(){
        if(this.sqlconnection != null){
            return true;
        }else{
            return false;
        }
    }
}
