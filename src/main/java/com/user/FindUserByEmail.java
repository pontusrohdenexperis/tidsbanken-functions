package com.user;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Used to check if a user exists when logging in to the site.
 * Checks by email.
 */
public class FindUserByEmail {
    @FunctionName("findUserByEmail")
    public boolean findUser(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "findUser") HttpRequestMessage<Optional<String>> request,
                @BindingName("email") String email
                ) throws SQLException{
                    String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                    String username = "tidsbanken";
                    String password = "Experisgbg1337";
                Connection conn = null;
                boolean userFound = false;
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(Url, username, password);
                    if(conn != null) {
                        System.out.println("Connection Successful!");
                    }
                    PreparedStatement preparedStatement = conn.prepareStatement("SELECT email FROM Users WHERE email = ?");
                    preparedStatement.setString(1, email);
                    ResultSet resultSet =  preparedStatement.executeQuery();
                    if(resultSet.next()) {
                        userFound = true;
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
                return userFound;
               
                }                       
}