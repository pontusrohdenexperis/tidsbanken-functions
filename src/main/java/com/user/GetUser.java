package com.user;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
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
                @BindingName("id") int id){
            return "this finally works!";
    }
}
