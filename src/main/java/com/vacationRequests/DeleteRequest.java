package com.vacationRequests;



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

    public class DeleteRequest {
        @FunctionName("deleteRequest")
        public String deleteRequest(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.DELETE},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "request/{id}") 
                    HttpRequestMessage<Optional<String>> request,
                    @BindingName("id") int id
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
                        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM VacationRequest WHERE id = ?");
                        preparedStatement.setInt(1, id);
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

