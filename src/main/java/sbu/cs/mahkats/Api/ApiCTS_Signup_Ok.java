package sbu.cs.mahkats.Api;

public class ApiCTS_Signup_Ok extends Json implements Send{
    private String action = "signup";
    private ContentCTS_Signup_Ok content;

    public String getAction() {
        return action;
    }

    public Content getContent() { return content; }

    public String getUsername() { return content.getUsername(); }

    public String getPassword() {
        return content.getPassword();
    }

    public String getEmail() { return content.getEmail(); }

    public String getBio() {
        return content.getBio();
    }

    public void setAction(String action) { this.action = action; }

    public void setContent(ContentCTS_Signup_Ok content) { this.content = content; }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Json toJson(String jsonString) { return super.toJson(jsonString); }

    @Override
    public void send(String ... args) {
        String username = args[0];
        String password = args[1];
        String email = args[2];
        String bio = args[3];
        ContentCTS_Signup_Ok content = new ContentCTS_Signup_Ok();
        content.setUsername(username);
        content.setPassword(password);
        content.setEmail(email);
        content.setBio(bio);
        setContent(content);
    }
}
