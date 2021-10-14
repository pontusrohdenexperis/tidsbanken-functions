package com.vacationRequests;

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

public class VacationRequests {
    @FunctionName("Request")
    public HttpResponseMessage request(@HttpTrigger(name = "req", methods = { HttpMethod.POST,
            HttpMethod.GET }, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
            @BindingName("username") String username, @BindingName("password") String password,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a LOGIN request.");

        if (request.getHttpMethod() == HttpMethod.POST) {
            return request.createResponseBuilder(HttpStatus.OK).body("Request added!").build();
        } else if (request.getHttpMethod() == HttpMethod.GET) {
            return request.createResponseBuilder(HttpStatus.OK).body("Requests:......").build();
        } else {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("You've done gone messed up now son.")
                    .build();
        }
    }

    @FunctionName("RequestById")
    public HttpResponseMessage requestById(@HttpTrigger(name = "req",
            // PUT should be PATCH
            methods = { HttpMethod.PUT, HttpMethod.GET,
                    HttpMethod.DELETE }, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
            @BindingName("username") String username, @BindingName("password") String password,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a LOGIN request.");

        if (request.getHttpMethod() == HttpMethod.PUT) {
            return request.createResponseBuilder(HttpStatus.OK).body("Request added!").build();
        } else if (request.getHttpMethod() == HttpMethod.GET) {
            return request.createResponseBuilder(HttpStatus.OK).body("Requests:......").build();
        } else if (request.getHttpMethod() == HttpMethod.DELETE) {
            return request.createResponseBuilder(HttpStatus.OK).body("Request Deleted!").build();
        } else {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("You've done gone messed up now son.")
                    .build();
        }
    }
}
