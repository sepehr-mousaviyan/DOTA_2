package sbu.cs.mahkats.Api;


public class JsonData<T>{
    String status;
    String action;
    private T content;

    public String getStatus() {return status;}
    public String getAction() {return action;}
    public T getContent() {
        return content;
    }
}
