package com.user;

import com.authentication.AuthenticationInfo;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import org.json.simple.parser.ParseException;

import models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 * Used to fetch a user from the db, based in id.
 */
public class GetUser {
    @FunctionName("getUser")
    public User getUser(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "user/{email}") HttpRequestMessage<Optional<String>> request,
                @BindingName("email") String email
                ) throws ParseException{
                    //String email = request.getQueryParameters().getOrDefault("email", "");
                    String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                    String username = "tidsbanken";
                    String password = "Experisgbg1337";
                Connection conn = null;
                User user = null;
                AuthenticationInfo auth = new AuthenticationInfo(request);
                
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(Url, username, password);
                    if(conn != null) {
                        System.out.println("Connection Successful!");
                    }
                    if(auth.isAdmin() || auth.getEmail().equals(email)) {
                        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Users WHERE email = ?");
                        preparedStatement.setString(1, email);
                        ResultSet resultSet = preparedStatement.executeQuery();

                            while(resultSet.next()){
                                user = new User(
                                    resultSet.getString("email"),
                                    resultSet.getString("firstname"),
                                    resultSet.getString("lastname"),
                                    resultSet.getString("profile_pic"),
                                    resultSet.getBoolean("is_admin")
                                );
                            }
                    }else {
                        PreparedStatement preparedStatement = conn.prepareStatement("SELECT firstname, lastname, profile_pic FROM Users WHERE email = ?");
                        preparedStatement.setString(1, email);
                        ResultSet resultSet = preparedStatement.executeQuery();

                            while(resultSet.next()){
                                user = new User(
                                    resultSet.getString("firstname"),
                                    resultSet.getString("lastname"),
                                    resultSet.getString("profile_pic")
                                );
                            }
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                    System.out.println("Error Trace in getConnection() : " + e.getMessage());
                }finally{
                    try {
                        conn.close();
                    } catch (Exception e) {
                        System.out.println("Something went wrong when closing the connection");    
                        System.out.println(e.toString());    
                    }
                }
                return user;
               
                }                       
}