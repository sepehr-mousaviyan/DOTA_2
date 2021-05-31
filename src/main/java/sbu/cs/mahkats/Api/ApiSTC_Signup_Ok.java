package sbu.cs.mahkats.Api;

/**
 * Api maker
 * server to client
 * for signup
 * status is ok
 */

public class ApiSTC_Signup_Ok extends Api implements Send {
    private final String status = "ok";
    private String action = "res_signup";
    private ContentSTC_Signup_Ok content;

    public String getStatus() {
        return status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(ContentSTC_Signup_Ok content) {
        this.content = content;
    }

    public String getLogin() {
        return content.getSignup();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Api toJson(String jsonString) {
        return super.toJson(jsonString);
    }

    @Override
    public void send(String... args) {
        ContentSTC_Signup_Ok content = new ContentSTC_Signup_Ok();
        content.setSignup("ok");
        setContent(content);
    }
}
