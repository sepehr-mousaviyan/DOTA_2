package sbu.cs.mahkats.Api;

import com.google.gson.JsonObject;
import org.javatuples.Pair;

public class UserData extends Data {
    private String username;
    private String password;
    private String email;
    private Long id;
    private String error = "";

    public UserData(String username, String password, String email, Long id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public UserData(String error, Long id) {
        this.error = error;
        this.id = id;
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

    public Long getId(){return id;}

    public String getError() {
        return error;
    }

    public JsonObject makeJson(){
        return new Api().toJson(new Pair<>("username", username) ,
                new Pair<>("password", password),
                new Pair<>("email", email));
    }

    public JsonObject makeErrorJson(){
        return new Api().toJson(new Pair<>("error", error) , new Pair<>("id", id));
    }

    public boolean equals(UserData obj) {
        return obj.getId().equals(this.id);
    }

    @Override
    public String toString() {
        if(error != null){
            return this.makeJson().toString();
        }
        return this.makeErrorJson().toString();
    }
}
