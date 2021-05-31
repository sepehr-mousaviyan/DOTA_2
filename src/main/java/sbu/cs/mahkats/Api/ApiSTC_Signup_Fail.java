package sbu.cs.mahkats.Api;

/**
 * Api maker
 * server to client
 * for signup
 * status Failed because of invalid_username or invalid_email error
 */

public class ApiSTC_Signup_Fail extends Api implements Send {
    private final String status = "fail";
    private String action = "res_signup";
    private ContentSTC_Signup_Fail content;

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

    public void setContent(ContentSTC_Signup_Fail content) {
        this.content = content;
    }

    public String getError() {
        return content.getError();
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
        String error = args[0];
        ContentSTC_Signup_Fail content = new ContentSTC_Signup_Fail();
        content.setError(error);
        setContent(content);
    }
}
