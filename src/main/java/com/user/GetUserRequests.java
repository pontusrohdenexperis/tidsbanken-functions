package com.user;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import models.VacationRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Used to fetch a user from the db, based in id.
 */
public class GetUserRequests {
    @FunctionName("getUserRequests")
    public List<VacationRequest> getUser(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "user/{email}/requests") 
                HttpRequestMessage<Optional<String>> request,
                @BindingName("email") String email
                ){
                    //String email = request.getQueryParameters().getOrDefault("email", "");
                    String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                    String username = "tidsbanken";
                    String password = "Experisgbg1337";
                Connection conn = null;
                ArrayList<VacationRequest> list = new ArrayList<>();
                System.out.println("email = " + email);
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(Url, username, password);
                    if(conn != null) {
                        System.out.println("Connection Successful!");
                    }
                    PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM vacation_requests WHERE owner_email = ?");
                    preparedStatement.setString(1, email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    while(resultSet.next()){
                        list.add(new VacationRequest(
                            resultSet.getInt("id"),
                            resultSet.getDate("period_start"),
                            resultSet.getDate("period_end"),
                            resultSet.getString("title"),
                            resultSet.getInt("request_status"),
                            resultSet.getString("owner_email")
                            )
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
                return list;
               
                }                       
}