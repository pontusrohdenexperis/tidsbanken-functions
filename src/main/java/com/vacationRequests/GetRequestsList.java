package com.vacationRequests;


import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import models.VacationRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Used to fetch a user from the db, based in id.
 */
public class GetRequestsList {
    @FunctionName("getRequestsList")
    public ArrayList<VacationRequest> getRequestsList(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "request") HttpRequestMessage<Optional<String>> request
                ){
                    String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                    String username = "tidsbanken";
                    String password = "Experisgbg1337";
                Connection conn = null;
                ArrayList<VacationRequest> requests = new ArrayList<>() ;
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(Url, username, password);
                    if(conn != null) {
                        System.out.println("Connection Successful!");
                    }
                    PreparedStatement preparedStatement = conn.prepareStatement("SELECT TOP 50 * FROM VacationRequest");
                    
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    while(resultSet.next()){
                        requests.add(new VacationRequest(
                            resultSet.getInt("id"),
                            resultSet.getTimestamp("period_start"),
                            resultSet.getTimestamp("period_end"),
                            resultSet.getString("title"),
                            resultSet.getString("owner_email"),
                            resultSet.getInt("request_status"),
                            resultSet.getString("moderator_email")
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
                return requests;
               
                }                       
}

