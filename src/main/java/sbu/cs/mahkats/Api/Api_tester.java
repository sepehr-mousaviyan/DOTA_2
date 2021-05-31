package sbu.cs.mahkats.Api;

public class Api_tester {
    public static void main(String[] args)  {
        ApiSTC_Signup_Ok json = new ApiSTC_Signup_Ok();
        json.send("dsf");
        System.out.println(json);
        System.out.println(json.getAction());

    }
}
