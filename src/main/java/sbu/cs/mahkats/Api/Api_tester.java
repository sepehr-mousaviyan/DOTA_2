package sbu.cs.mahkats.Api;

public class Api_tester {
    public static void main(String[] args)  {
        ApiCTS_Signup_Ok json = new ApiCTS_Signup_Ok();
        json.send("sepehr", "are1123","dsfsdf@gdg.com", "man manam");
        System.out.println(json);
        System.out.println(json.getAction());

    }
}
