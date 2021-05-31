package sbu.cs.mahkats.Api.CTS.Signin;

import sbu.cs.mahkats.Api.Api;
import sbu.cs.mahkats.Api.Content;
import sbu.cs.mahkats.Api.Send;

/**
 * Api maker
 * client to server
 * for sign in
 */
public class ApiCTS_Signin_Ok extends Api implements Send {
    private String action = "signin";
    private ContentCTS_Signin_Ok content;

    public String getAction() {
        return action;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(ContentCTS_Signin_Ok content) {
        this.content = content;
    }

    public String getPassword() {
        return content.getPassword();
    }

    public String getUsername() {
        return content.getUsername();
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Api toJson(String jsonString) {
        return super.toJson(jsonString);
    }

    @Override
    public void send(String... args) {
        String username = args[0];
        String password = args[1];
        ContentCTS_Signin_Ok content = new ContentCTS_Signin_Ok();
        content.setUsername(username);
        content.setPassword(password);
        setContent(content);
    }
}
