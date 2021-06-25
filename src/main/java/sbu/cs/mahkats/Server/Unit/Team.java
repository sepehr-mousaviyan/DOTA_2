package sbu.cs.mahkats.Server.Unit;

public enum Team {
    GREEN,
    RED;
    public String getTeam() {
        switch (this) {
            case GREEN:
                return "GREEN";
            case RED:
                return "RED";
            default:
                return "";
        }
    }
}
