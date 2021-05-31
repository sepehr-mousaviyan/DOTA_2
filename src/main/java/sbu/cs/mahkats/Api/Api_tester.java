package sbu.cs.mahkats.Api;

public class Api_tester {
    public static void main(String[] args)  {
        ApiSTC_Signin_Ok json = new ApiSTC_Signin_Ok();
        json.send("ali", "122223");
        System.out.println(json);
        System.out.println(json.getAction());
        System.out.println(json.getStatus());


    }
}
