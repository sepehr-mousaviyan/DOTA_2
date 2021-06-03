package sbu.cs.mahkats.Api;

public class JsonData<T>{
    String status;
    private T content;

    public String getStatus() {return status;}
    public T getContent() {
        return content;
    }
}
