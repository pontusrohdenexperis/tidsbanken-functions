package com.ineligible;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import models.IneligiblePeriod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 * Used to fetch a user from the db, based in id.
 */
public class GetPeriodById {
    @FunctionName("getPeriodById")
    public IneligiblePeriod getPeriodById(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "ineligible/{id}") 
                HttpRequestMessage<Optional<String>> request,
                @BindingName("id") int id
                ){
                String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                String username = "tidsbanken";
                String password = "Experisgbg1337";
                Connection conn = null;
                IneligiblePeriod period = null;
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(Url, username, password);
                    if(conn != null) {
                        System.out.println("Connection Successful!");
                    }
                    PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM IneligiblePeriod WHERE id = ?");
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    while(resultSet.next()){
                        period = new IneligiblePeriod(
                            resultSet.getInt("id"),
                            resultSet.getTimestamp("period_start"),
                            resultSet.getTimestamp("period_end"),
                            resultSet.getString("created_by")
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
                return period;
            }                       
}
