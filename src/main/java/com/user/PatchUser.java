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
import java.util.Optional;

/**
 * Used to fetch a user from the db, based in id.
 */
public class PatchUser {

    @FunctionName("patchUser")
    public String patchUser(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.OPTIONS},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "user") HttpRequestMessage<Optional<String>> request,
                @BindingName("email") String email,
                @BindingName("firstname") String firstname,
                @BindingName("lastname") String lastname,
                @BindingName("profilePic") String profilePic,
                @BindingName("isAdmin") Boolean isAdmin
                //@BindingName("userPassword") int userPassword
                ){
                    String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                    String username = "tidsbanken";
                    String password = "Experisgbg1337";
                Connection conn = null;
                String message = "";
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(Url, username, password);
                    if(conn != null) {
                        System.out.println("Connection Successful!");
                    }
                    PreparedStatement preparedStatement = conn.prepareStatement(
                        "UPDATE Users "+ 
                        "SET firstname = ?, lastname = ?, profile_pic = ?, is_admin = ? "+
                        "WHERE email = ?;");
                        preparedStatement.setString(1, firstname);
                        preparedStatement.setString(2, lastname);
                        preparedStatement.setString(3, profilePic);
                        preparedStatement.setBoolean(4, isAdmin);
                        preparedStatement.setString(5, email);
                    //preparedStatement.setInt(6, userPassword);
                    preparedStatement.executeQuery();
                    message = "User successfully updated!";
                    
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
                
                return message;
            }                       
}