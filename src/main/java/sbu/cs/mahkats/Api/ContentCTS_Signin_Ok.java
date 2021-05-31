package sbu.cs.mahkats.Api;

/**
 * Api content part maker
 * client to server
 * for sign in
 */

public class ContentCTS_Signin_Ok extends Content {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
