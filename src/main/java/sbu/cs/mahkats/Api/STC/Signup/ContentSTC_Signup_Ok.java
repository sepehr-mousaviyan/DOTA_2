package sbu.cs.mahkats.Api.STC.Signup;

import sbu.cs.mahkats.Api.Content;

/**
 * Api content part maker
 * server to client
 * for signup
 * status is ok
 */

public class ContentSTC_Signup_Ok extends Content {
    private String signup = "ok";

    public String getSignup() {
        return signup;
    }

    public void setSignup(String signup) {
        this.signup = signup;
    }

}
