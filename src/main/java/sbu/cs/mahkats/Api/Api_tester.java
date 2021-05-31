package sbu.cs.mahkats.Api;

public class Api_tester {
    public static void main(String[] args)  {
        ApiSTC_Signup_Fail json = new ApiSTC_Signup_Fail();
        json.send(Signup_Error.IU.getError());
        System.out.println(json);
        System.out.println(json.getAction());

    }
}
