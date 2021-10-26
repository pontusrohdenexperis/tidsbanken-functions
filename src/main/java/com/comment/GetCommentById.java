package com.comment;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import models.Comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 * Used to fetch a user from the db, based in id.
 */
public class GetCommentById {
    @FunctionName("getCommentById")
    public Comment getCommentById(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "request/{requestId}/comment/{commentId}") 
                HttpRequestMessage<Optional<String>> request,
                @BindingName("requestId") int requestId,
                @BindingName("commentId") int commentId
                ){
                    String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                    String username = "tidsbanken";
                    String password = "Experisgbg1337";
                Connection conn = null;
                Comment comment = null;
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(Url, username, password);
                    if(conn != null) {
                        System.out.println("Connection Successful!");
                    }
                    PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Comment WHERE request_id = ? AND id = ?");
                    preparedStatement.setInt(1, requestId);
                    preparedStatement.setInt(2, commentId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    while(resultSet.next()){
                        comment = new Comment(
                            resultSet.getInt("id"),
                            resultSet.getString("message"),
                            resultSet.getTimestamp("timestamp"),
                            resultSet.getString("user_email"),
                            resultSet.getInt("request_id")
                        );
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
                return comment;
               
                }                       
}
