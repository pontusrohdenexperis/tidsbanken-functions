package com.comment;

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

public class DeleteComment {
        @FunctionName("deleteComment")
        public String deleteComment(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.DELETE},
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
                    String message = "";
                    try{
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        conn = DriverManager.getConnection(Url, username, password);
                        if(conn != null) {
                            System.out.println("Connection Successful!");
                        }
                        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM comments WHERE request_id = ? AND id = ?");
                        preparedStatement.setInt(1, requestId);
                        preparedStatement.setInt(2, commentId);
                        preparedStatement.executeQuery();
                        message = "Request successfully deleted";
                        
                       
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