package com.login;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class Login {
    @FunctionName("Login")
    public HttpResponseMessage run(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.POST},
            authLevel = AuthorizationLevel.FUNCTION)
            HttpRequestMessage<Optional<String>> request,
            @BindingName("username") String username,
            @BindingName("password") String password,
        final ExecutionContext context) {
            context.getLogger().info("Java HTTP trigger processed a LOGIN request.");
        
        // Convert and display
        if (password == "") {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                            .body("Please enter password")
                            .build();
        }else if (username == "" ) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                            .body("Please enter username")
                            .build();
        }
        else {
            return request.createResponseBuilder(HttpStatus.OK)
                            .body("Login successfull!")
                            .build();
        }
    }
}

