package sbu.cs.mahkats.Api;

/**
 * Api content part maker
 * server to client
 * for sign in
 * status is ok
 */

public class ContentSTC_Signin_Ok extends Content {
    private String signin = "ok";

    public String getSignin() {
        return signin;
    }

    public void setSignin(String signin) {
        this.signin = signin;
    }
}
