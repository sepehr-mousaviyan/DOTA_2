package sbu.cs.mahkats.Api;

public class Api_tester {
    public static void main(String[] args)  {
        ApiCTS_Login json = new ApiCTS_Login();
        json.send("ali", "122223");
        System.out.println(json);
        System.out.println(json.getAction());
        System.out.println(json.getUsername());
        System.out.println(json.getPassword());

    }
}
