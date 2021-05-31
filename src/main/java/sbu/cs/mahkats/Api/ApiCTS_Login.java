package sbu.cs.mahkats.Api;

public class ApiCTS_Login extends Json implements Send{
    private String action = "login";
    private ContentCTS_Login content;

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

    public void setContents(ContentCTS_Login content) { this.content = content; }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Json toJson(String jsonString) { return super.toJson(jsonString); }

    @Override
    public void send(String ... args) {
        String username = args[0];
        String password = args[1];
        ContentCTS_Login content_1 = new ContentCTS_Login();
        content_1.setUsername(username);
        content_1.setPassword(password);
        setContents(content_1);
    }
}
