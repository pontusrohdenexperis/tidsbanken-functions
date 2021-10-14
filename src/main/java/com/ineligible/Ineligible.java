package com.ineligible;

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

public class Ineligible {
    @FunctionName("Ineligible")
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

}
/*
GET /ineligible
Returns a list of periods that are ineligible for vacation requests in chronological order
(soonest first). Optionally accepts appropriate query parameters.
POST /ineligible
Creates a new ineligible period. Accepts appropriate parameters in the ineligible body
as application/json. Admin only.
GET /ineligible/:ip_id
Returns the ineligible period corresponding to the provided ip_id.
PATCH /ineligible/:ip_id
Executes a partial update of the ineligible period corresponding to the provided ip_-
id. Accepts appropriate parameters in the ineligible body as application/json. Admin
only.
DELETE /ineligible/:ip_id
Deletes an ineligible period. Admin only.
 */