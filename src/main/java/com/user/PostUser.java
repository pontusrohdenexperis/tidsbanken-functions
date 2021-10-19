package com.user;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.CosmosDBOutput;

import java.util.Optional;

public class PostUser {
    @FunctionName("postUser")
    public String postUser(
        @HttpTrigger(name = "req", 
                methods = {HttpMethod.POST }, 
                authLevel = AuthorizationLevel.FUNCTION,
                route = "user"
                )
                HttpRequestMessage<Optional<String>> request,
                //@BindingName("id") int id, 
                //@BindingName("firstname") String firstname,
                //@BindingName("lastname") String lastname, 
        @CosmosDBOutput(name = "database",
                databaseName = "Tidsbanken",
                collectionName = "users",
                connectionStringSetting = "CosmosDbConnectionString") 
        Optional<String> item){
            //return "{ \"id\": \"" + id + "\", \"firstname\": \"" + firstname + "\" }";
            return "{hello}";
        }
    }
    
/*        @FunctionName("addItem")

 public String cosmosDbAddItem(
    @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
     final String message,
    @CosmosDBOutput(name = "database", databaseName = "ToDoList", collectionName = "Items", 
    connectionStringSetting = "AzureCosmosDBConnection")
 ) {
     return "{ \"id\": \"" + System.currentTimeMillis() + "\", \"description\": \"" + message + "\" }";
 }
}
*/