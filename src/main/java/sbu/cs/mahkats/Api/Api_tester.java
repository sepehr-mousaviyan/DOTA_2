package sbu.cs.mahkats.Api;

public class Api_tester {
    public static void main(String[] args)  {
        ApiCTS_Signin json = new ApiCTS_Signin();
        json.send("ali", "122223");
        System.out.println(json);
        System.out.println(json.getAction());
        System.out.println(json.getUsername());
        System.out.println(json.getPassword());

    }
}
