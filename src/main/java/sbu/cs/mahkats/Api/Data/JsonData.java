package sbu.cs.mahkats.Api.Data;

public class JsonData<T> extends Data{
    String status;
    String action;
    private T content;

    public String getStatus() {return status;}
    public String getAction() {return action;}
    public T getContent() {
        return content;
    }
}
