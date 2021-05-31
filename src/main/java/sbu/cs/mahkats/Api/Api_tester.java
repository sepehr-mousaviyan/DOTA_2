package sbu.cs.mahkats.Api;

public class Api_tester {
    public static void main(String[] args)  {
        ApiSTC_Signin_Fail json = new ApiSTC_Signin_Fail();
        json.send(Signin_Error.IU.getError());
        System.out.println(json);
        System.out.println(json.getAction());
        System.out.println(json.getStatus());
        System.out.println(json.getError());


    }
}
