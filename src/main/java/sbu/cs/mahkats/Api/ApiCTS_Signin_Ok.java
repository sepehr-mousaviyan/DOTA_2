package sbu.cs.mahkats.Api;

public class ApiCTS_Signin_Ok extends Json implements Send{
    private String action = "signin";
    private ContentCTS_Signin content;

    public String getAction() {
        return action;
    }

    public Content getContent() { return content; }

    public String getUsername() { return content.getUsername(); }

    public String getPassword() {
        return content.getPassword();
    }

    public void setAction(String action) { this.action = action; }

    public void setContent(ContentCTS_Signin content) { this.content = content; }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Json toJson(String jsonString) { return super.toJson(jsonString); }

    @Override
    public void send(String ... args) {
        String username = args[0];
        String password = args[1];
        ContentCTS_Signin contentCTS_signin = new ContentCTS_Signin();
        contentCTS_signin.setUsername(username);
        contentCTS_signin.setPassword(password);
        setContent(contentCTS_signin);
    }
}
