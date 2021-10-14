package com.comment;

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

public class Comment {
    @FunctionName("RequestComment")
    public HttpResponseMessage request(@HttpTrigger(name = "req", methods = { HttpMethod.POST,
            HttpMethod.GET, HttpMethod.POST }, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
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
 * GET /request/:request_id/comment Returns a list of comments in reverse
 * chronological order (most recent first). Option- ally accepts appropriate
 * query parameters. Comments for a request may only be viewed by an
 * Administrator or the request owner. If an unauthorized person attempts to
 * view comments then the server should respond with 403 Forbidden. 
 * 
 * POST
 * /request/:request_id/comment Creates a new comment for the request identified
 * by request_id. Accepts appropriate parameters in the request body as
 * application/json. Only Administrators and the request owner may create
 * comments on a particular request. If an unauthorized person attempts to
 * create a comment on a vacation request then the server should respond with
 * 403 Forbidden. 
 * 
 * GET /request/:request_id/comment/:comment_id Returns the
 * comment corresponding to the provided request_id and comment_id. Comments for
 * a request may only be viewed by an Administrator or the request owner. If an
 * unauthorized person attempts to view comments then the server should respond
 * with 403 Forbidden. 
 * 
 * PATCH /request/:request_id/comment/:comment_id Executes a
 * partial update of the comment corresponding to the provided request_id and
 * comment_id. Accepts appropriate parameters in the request body as
 * application/json. Comments may only be edited by the comment author and only
 * within the first 24 hours since they were created. Comments that have been
 * edited should show that they have been edited and provide a timestamp of when
 * they were created and last edited. 13 If an unauthorized person attempts to
 * update a comment then the server should respond with 403 Forbidden. 
 * 
 * DELETE
 * /request/:request_id/comment/:comment_id Deletes a comment. Within the first
 * 24 hours after having made the comment, the comment author may delete their
 * own comments. Administrators are subject to these same restrictions. If an
 * unauthorized person attempts to delete a comment then the server should
 * respond with 403 Forbidden.
 */