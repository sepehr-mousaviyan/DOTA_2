package sbu.cs.mahkats.Api;

public class Api_1 extends Json implements Send{
    private String action = "login";
    private Content_1 content;

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

    public void setContents(Content_1 content) { this.content = content; }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Json toJson(String jsonString) { return super.toJson(jsonString); }

    @Override
    public void send(String ... args) {
        String username = args[0];
        String password = args[1];
        Content_1 content_1 = new Content_1();
        content_1.setUsername(username);
        content_1.setPassword(password);
        setContents(content_1);
    }
}
