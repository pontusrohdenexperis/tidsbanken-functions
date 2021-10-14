package com.user;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.CosmosDBInput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class GetUser{
    @FunctionName("getUser")
    public String getUser(
        @HttpTrigger(name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "user"
                ) 
                HttpRequestMessage<Optional<String>> request,
                @BindingName("id") int id,
        @CosmosDBInput(name = "database",
                databaseName = "Tidsbanken",
                collectionName = "users",
                sqlQuery = "select * from users u where u.id = {id}",
                connectionStringSetting = "CosmosDbConnectionString") 
        Optional<String> item){
            return item.orElse("Not found");
    }
}
