package sbu.cs.mahkats.Api;

import com.google.gson.JsonObject;
import org.javatuples.Pair;

public class UserData extends Data {
    private String username;
    private String password;
    private String email;
    private Long token;
    private String error;

    /**
    * for sign up
     */
    public UserData(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * for login
     */
    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * for ok response login/sign up
     */
    public UserData(Long token) {
        this.token = token;
    }

    /**
     * for response error signup
     */
    public UserData(String error) {
        this.error = error;
    }

    /**
     * for response error login
     */
    public UserData(String error, Long token) {
        this.error = error;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getToken(){return token;}

    public String getError() {
        return error;
    }

    public JsonObject makeJson(){
        if(token == null) {
            if(email != null) {
                return new Api().toJson(new Pair<>("username", username),
                        new Pair<>("password", password),
                        new Pair<>("email", email));
            }
            return new Api().toJson(new Pair<>("username", username),
                    new Pair<>("password", password));

        }
        return new Api().toJson(new Pair<>("token", token));
    }

    public JsonObject makeErrorJson(){
        if(token != null) {
            return new Api().toJson(new Pair<>("error", error), new Pair<>("token", token));
        }
        return new Api().toJson(new Pair<>("error", error));
    }

    public boolean equals(UserData obj) {
        return obj.getToken().equals(this.token);
    }

    @Override
    public String toString() {
        if(error != null){
            return this.makeJson().toString();
        }
        return this.makeErrorJson().toString();
    }
}
