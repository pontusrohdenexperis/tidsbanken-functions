package com.ineligible;


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
public class PostPeriod {
    @FunctionName("postPeriod")
    public String postPeriod(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "ineligible") HttpRequestMessage<Optional<String>> request,
                @BindingName("periodStart") Timestamp periodStart,
                @BindingName("periodEnd") Timestamp periodEnd,
                @BindingName("createdBy") String createdBy
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
                        "INSERT INTO IneligiblePeriod (period_end, period_start, created_by)"+
                        "VALUES(?,?,?)");
                        preparedStatement.setTimestamp(1, periodEnd);
                        preparedStatement.setTimestamp(2, periodStart);
                        preparedStatement.setString(3, createdBy);
                    preparedStatement.executeQuery();
                    returnMessage = "Request successfully added";
                    
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
