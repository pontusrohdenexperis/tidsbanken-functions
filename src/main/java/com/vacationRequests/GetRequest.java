package com.vacationRequests;

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
import java.util.Optional;

/**
 * Used to fetch a user from the db, based in id.
 */
public class GetRequest {
    @FunctionName("getRequest")
    public VacationRequest getRequest(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "request/{id}") HttpRequestMessage<Optional<String>> request,
                @BindingName("id") String id
                ){
                    String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                    String username = "tidsbanken";
                    String password = "Experisgbg1337";
                Connection conn = null;
                VacationRequest vacationRequest = null;
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(Url, username, password);
                    if(conn != null) {
                        System.out.println("Connection Successful!");
                    }
                    /**
                     * if(user) {
                     *  get approved requests and my requests}
                     */

                    PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM vacation_requests WHERE id = ? AND is_deleted = 0");
                    preparedStatement.setString(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    while(resultSet.next()){
                        vacationRequest = new VacationRequest(
                            resultSet.getInt("id"),
                            resultSet.getDate("period_start"),
                            resultSet.getDate("period_end"),
                            resultSet.getString("title"),
                            resultSet.getInt("status_id"),
                            resultSet.getString("owner_email")
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
                return vacationRequest;
               
                }                       
}