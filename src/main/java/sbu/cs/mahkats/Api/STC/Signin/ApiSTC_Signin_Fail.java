package sbu.cs.mahkats.Api.STC.Signin;

import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.Content;
import sbu.cs.mahkats.Api.Send;

/**
 * Api maker
 * server to client
 * for sign in
 * status Failed because of invalid_username or invalid_password error
 */

public class ApiSTC_Signin_Fail extends Api implements Send {
    private String status = "fail";
    private String action = "res_signin";
    private ContentSTC_Signin_Fail content;

    public String getStatus() { return status; }

    public String getAction() {
        return action;
    }

    public Content getContent() { return content; }

    public String getError() { return content.getError(); }

    public void setAction(String action) { this.action = action; }

    public void setContent(ContentSTC_Signin_Fail content) { this.content = content; }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Api toJson(String jsonString) { return super.toJson(jsonString); }

    @Override
    public void send(String ... args) {
        String error = args[0];
        ContentSTC_Signin_Fail content = new ContentSTC_Signin_Fail();
        content.setError(error);
        setContent(content);
    }

}
