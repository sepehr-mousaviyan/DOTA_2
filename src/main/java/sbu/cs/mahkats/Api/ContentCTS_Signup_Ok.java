package sbu.cs.mahkats.Api;

public class ContentCTS_Signup_Ok extends Content {
    private String username;
    private String password;
    private String email;
    private String bio;

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

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
