package sbu.cs.mahkats.Api;

public class ApiSTC_Signin_Ok extends Json implements Send{
    private String status = "ok";
    private String action = "res_signin";
    private ContentSTC_Signin_Ok content;

    public String getStatus() { return status; }

    public String getAction() {
        return action;
    }

    public Content getContent() { return content; }

    public String getLogin() {
        return content.getSignin();
    }

    public void setAction(String action) { this.action = action; }

    public void setContent(ContentSTC_Signin_Ok content) { this.content = content; }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Json toJson(String jsonString) { return super.toJson(jsonString); }

    @Override
    public void send(String ... args) {
        ContentSTC_Signin_Ok content = new ContentSTC_Signin_Ok();
        content.setSignin("ok");
        setContent(content);
    }
}
