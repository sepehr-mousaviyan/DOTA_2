package sbu.cs.mahkats.Api;

public class ApiCTS_Signin extends Json implements Send{
    private String action = "login";
    private ContentCTS_Signin content;

    public String getAction() {
        return action;
    }

    public Content getContents() { return content; }

    public String getUsername() { return content.getUsername(); }

    public String getPassword() {
        return content.getPassword();
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setContents(ContentCTS_Signin content) { this.content = content; }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Json toJson(String jsonString) { return super.toJson(jsonString); }

    @Override
    public void send(String ... args) {
        String username = args[0];
        String password = args[1];
        ContentCTS_Signin content_1 = new ContentCTS_Signin();
        content_1.setUsername(username);
        content_1.setPassword(password);
        setContents(content_1);
    }
}
