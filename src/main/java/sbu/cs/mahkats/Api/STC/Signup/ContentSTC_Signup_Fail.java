package sbu.cs.mahkats.Api.STC.Signup;

import sbu.cs.mahkats.Api.Content;

/**
 * Api content part maker
 * server to client
 * for signup
 * status Failed because of invalid_username or invalid_email error
 */

public class ContentSTC_Signup_Fail extends Content {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
