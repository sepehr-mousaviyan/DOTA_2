package sbu.cs.mahkats.Api;

public enum Signin_Error {
    IU,
    IP;
    String getError() {
        switch (this)
        {
            case IU:
                return "invalid_username";
            case IP:
                return "invalid_password";
            default:
                return "unknown";
        }
    }
}
