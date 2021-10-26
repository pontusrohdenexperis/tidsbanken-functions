package com.comment;


import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Optional;

/**
 * Used to fetch a user from the db, based in id.
 */
public class PatchComment {
    @FunctionName("patchComment")
    public String patchComment(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.OPTIONS},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "request/{requestId}/comment/{commentId}") 
                HttpRequestMessage<Optional<String>> request,
                @BindingName("requestId") int requestId,
                @BindingName("commentId") int commentId,
                @BindingName("userEmail") String userEmail,
                @BindingName("timestamp") Timestamp timestamp,
                @BindingName("message") String message
                //@BindingName("userPassword") int userPassword
                ){
                    String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                    String username = "tidsbanken";
                    String password = "Experisgbg1337";
                Connection conn = null;
                String returnMessage = "";
                
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(Url, username, password);
                    if(conn != null) {
                        System.out.println("Connection Successful!");
                    }
                    PreparedStatement preparedStatement = conn.prepareStatement(
                        "UPDATE Comment " +
                        "SET user_email = ?, timestamp = ?, message = ? "+
                        "WHERE request_id = ? AND id = ?");
                    preparedStatement.setString(1, userEmail);
                    preparedStatement.setTimestamp(2, timestamp);
                    preparedStatement.setString(3, message);
                    preparedStatement.setInt(4, requestId);
                    preparedStatement.setInt(5, commentId);
                    preparedStatement.executeQuery();
                    message = "Request successfully updated!";
                    
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
                
                return returnMessage;
            }                       
}