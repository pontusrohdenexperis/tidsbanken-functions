package com.authentication;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.microsoft.azure.functions.HttpRequestMessage;

public class AuthenticationInfo {
    private JSONObject jsonPayload;
    private String header;
    
    private boolean isValid;
    private String email;
    private boolean isAdmin;
    
    public AuthenticationInfo(HttpRequestMessage<Optional<String>> request) throws ParseException {    
        
        //Check if we have a header.
        if (request.getHeaders().get("token").equals("")) {
            isValid = false;
            return;
        }
        
        String[] chunks = request.getHeaders().get("token").split("\\.");
        
        Base64.Decoder decoder = Base64.getDecoder();
        
        header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        
        JSONParser parser = new JSONParser();
        jsonPayload = (JSONObject) parser.parse(payload);


        JSONObject realmAccess = (JSONObject)jsonPayload.get("realm_access");
        ArrayList<String> temp = (ArrayList<String>) realmAccess.get("roles");
        this.isAdmin = temp.get(0).equals("app-admin");
        this.email = (String)jsonPayload.get("email");
        this.isValid = true;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
