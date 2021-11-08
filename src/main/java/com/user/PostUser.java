package com.user;

import com.authentication.AuthenticationInfo;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import org.json.simple.parser.ParseException;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Optional;

/**
 * Used to fetch a user from the db, based in id.
 */
public class PostUser {
    @FunctionName("postUser")
    public String postUser(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "user") HttpRequestMessage<Optional<String>> request,
                @BindingName("email") String email,
                @BindingName("firstname") String firstname,
                @BindingName("lastname") String lastname,
                @BindingName("profilePic") String profilePic,
                @BindingName("isAdmin") Boolean isAdmin
                //@BindingName("userPassword") int userPassword
                ) throws ParseException{
                
                    AuthenticationInfo auth = new AuthenticationInfo(request);
                    if(auth.isValid() && auth.isAdmin()){

                        String Url = "jdbc:sqlserver://tidsbankenserver.database.windows.net:1433;DatabaseName=tidsbankenpostgres;";
                        String username = "tidsbanken";
                        String password = "Experisgbg1337";
                        Connection conn = null;
                        String message = "";
                        byte[] varBinary = profilePic.getBytes(StandardCharsets.UTF_8);
                        try{
                            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            conn = DriverManager.getConnection(Url, username, password);
                            if(conn != null) {
                                System.out.println("Connection Successful!");
                            }
                            PreparedStatement preparedStatement = conn.prepareStatement(
                                "INSERT INTO Users (email, firstname, lastname, profile_pic, is_admin)"+
                                "VALUES(?,?,?,?,?)");
                                preparedStatement.setString(1, email);
                                preparedStatement.setString(2, firstname);
                                preparedStatement.setString(3, lastname);
                                preparedStatement.setBytes(4, varBinary);
                                preparedStatement.setBoolean(5, isAdmin);
                                //preparedStatement.setInt(6, userPassword);
                                preparedStatement.executeQuery();
                                message = "User successfully added";
                                
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
                    }else{
                        return "Unauthorized!";
                }
            }                       
}
        
    