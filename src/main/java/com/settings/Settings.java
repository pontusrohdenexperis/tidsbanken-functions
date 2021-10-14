package com.settings;

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

public class Settings {
    @FunctionName("Settings")
    public HttpResponseMessage request(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.POST, HttpMethod.GET},
            authLevel = AuthorizationLevel.FUNCTION)
            HttpRequestMessage<Optional<String>> request,
            @BindingName("username") String username,
            @BindingName("password") String password,
        final ExecutionContext context) {
            context.getLogger().info("Java HTTP trigger processed a LOGIN request.");
            
            if(request.getHttpMethod() == HttpMethod.POST) {
                return request.createResponseBuilder(HttpStatus.OK)
                .body("Request added!")
                .build();
            }
            else if (request.getHttpMethod() == HttpMethod.GET){
                return request.createResponseBuilder(HttpStatus.OK)
                .body("Requests:......")
                .build();
            }
            else {
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("You've done gone messed up now son.")
                    .build();
            }
        }
    
}
/*
 The application should have some way of storing, updating and reading application
settings that are enforced throughout the application. The following application settings
should be considered:
1. Specify the maximum period of time any single vacation may be (in days).
Candidates may include any other configurable settings they deem appropriate. The
settings should only be exposed to Administrators.
 */