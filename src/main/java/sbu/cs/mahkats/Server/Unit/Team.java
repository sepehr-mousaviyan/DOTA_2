package sbu.cs.mahkats.Unit;

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
