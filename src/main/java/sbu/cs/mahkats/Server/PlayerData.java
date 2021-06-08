package sbu.cs.mahkats.Server;

public class PlayerData {
    private final String username;
    private final String password;

    public PlayerData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean equals(PlayerData obj) {
        return this.username.equals(obj.getUsername());
    }
}
