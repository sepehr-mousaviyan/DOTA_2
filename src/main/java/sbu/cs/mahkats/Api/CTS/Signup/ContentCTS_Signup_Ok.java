package sbu.cs.mahkats.Api.CTS.Signup;

import sbu.cs.mahkats.Api.Content;

/**
 * Api content part maker
 * client to server
 * for signup
 */

public class ContentCTS_Signup_Ok extends Content {
    private String username;
    private String password;
    private String email;
    private String bio;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
