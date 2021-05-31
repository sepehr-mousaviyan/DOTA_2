package sbu.cs.mahkats.Api;

public class Api_tester {
    public static void main(String[] args)  {
        Api_1 json = new Api_1();
        json.send("ali", "122223");
        System.out.println(json);
        System.out.println(json.getAction());
        System.out.println(json.getUsername());
        System.out.println(json.getPassword());

    }
}
