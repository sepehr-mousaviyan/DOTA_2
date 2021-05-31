package sbu.cs.mahkats.Api.STC.Signin;

import sbu.cs.mahkats.Api.Content;

/**
 * Api content part maker
 * server to client
 * for sign in
 * status Failed because of invalid_username or invalid_password error
 */

public class ContentSTC_Signin_Fail extends Content {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) { this.error = error; }
}
