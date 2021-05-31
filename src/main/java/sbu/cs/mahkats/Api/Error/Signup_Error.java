package sbu.cs.mahkats.Api.Error;

/**
 * possible signup errors enum
 */

public enum Signup_Error {
    IU,
    IE;

    String getError() {
        switch (this) {
            case IU:
                return "invalid_username";
            case IE:
                return "invalid_email";
            default:
                return "unknown";
        }
    }
}
